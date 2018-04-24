package com.camelot.pmt.platform.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.camelot.pmt.platform.mapper.OrgMapper;
import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.platform.model.Org;
import com.camelot.pmt.platform.model.OrgToUser;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.OrgService;
import com.camelot.pmt.util.BuildTree;
import com.camelot.pmt.util.Tree;
import com.camelot.pmt.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class OrgServiceImpl implements OrgService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询部门列表
     * 
     * @return
     */
    @Override
    public List<Tree<Org>> queryAllOrgs() {
        List<Tree<Org>> list = null;
        List<Tree<Org>> trees = new ArrayList<Tree<Org>>();
        List<Org> queryAllOrg = orgMapper.queryAllOrg();
        if (queryAllOrg != null) {
            for (Org org : queryAllOrg) {
                Tree<Org> tree = new Tree<Org>();
                tree.setId(org.getOrgId());
                tree.setParentId(org.getParentId());
                tree.setText(org.getOrgname());
                Map<String, Object> attributes = new HashMap<>(16);
                attributes.put("state", org.getState());
                attributes.put("sortNum", org.getSortNum());
                attributes.put("orgCode", org.getOrgCode());
                attributes.put("parentOrgname", org.getParentOrgname());
                attributes.put("creatUserId", org.getCreatUserId());
                tree.setAttributes(attributes);
                trees.add(tree);
            }
            // 默认顶级菜单为０，根据数据库实际情况调整
            list = BuildTree.buildList(trees, "0");
            return list;
        }
        return list;
    }

    /**
     * 新增部门
     * 
     * @param org
     * @return
     */
    @Override
    public String addOrg(Org org) {
        String result = "";
        String code = createCode(org);
        org.setOrgCode(code);
        int count = orgMapper.checkOrgCodeIsExist(org.getOrgCode());
        if (count > 0) {
            result = "部门编号已存在请重新添加";
            return result;
        }
        int num = orgMapper.checkOrgNameIsExist(org.getOrgname());
        if (num > 0) {
            result = "部门名称已存在请重新添加";
            return result;
        }
        org.setOrgId(UUIDUtil.getUUID());
        int nums = orgMapper.addOrg(org);
        if (nums > 0) {
            result = "添加部门成功!";
            return result;
        } else {
            result = "添加部门失败!";
            return result;
        }
    }

    /**
     * 生成code
     * 
     * @param org
     * @return
     */
    private String createCode(Org org) {
        int max = 0;
        int maxNum = 0;
        String code = "01";
        if ("0".equals(org.getParentId())) {
            List<Org> list = orgMapper.queryOrgSubByParentId(org.getParentId());
            if (!CollectionUtils.isEmpty(list)) {
                for (Org orgItem : list) {
                    int a = Integer.parseInt(orgItem.getOrgCode());
                    if (a > max) {
                        max = a;
                    }
                }
                if (max > 0 && max < 9) {
                    code = "0" + String.valueOf(++max);
                } else {
                    code = String.valueOf(++max);
                }
            }
        } else {
            List<Org> list = orgMapper.queryOrgSubByParentId(org.getParentId());
            if (CollectionUtils.isEmpty(list)) {
                Org orgObj = orgMapper.queryOrgByOrgId(org.getParentId());
                code = orgObj.getOrgCode() + ".01";
            } else {
                for (Org orgItem : list) {
                    int b = Integer.parseInt(orgItem.getOrgCode().substring(orgItem.getOrgCode().length() - 2));
                    if (b > maxNum) {
                        maxNum = b;
                    }
                    if (maxNum > 0 && maxNum < 9) {
                        maxNum++;
                        code = orgItem.getOrgCode().substring(0, orgItem.getOrgCode().length() - 2) + "0"
                                + String.valueOf(maxNum);
                    } else {
                        maxNum++;
                        code = orgItem.getOrgCode().substring(0, orgItem.getOrgCode().length() - 2)
                                + String.valueOf(maxNum);
                    }
                }

            }

        }
        return code;
    }

    /**
     * 修改部门
     * 
     * @param org
     * @return
     */
    @Override
    public String updateOrgByOrgId(Org org) {
        Org orgBefore = orgMapper.queryOrgByOrgId(org.getOrgId());
        if (org.getParentId().equals(orgBefore.getParentId())) {
            int num = orgMapper.updateOrgByOrgId(org);
            if (num > 0) {
                return updateState(org);
            }
        } else {
            org.setOrgCode(createCode(org));
            orgMapper.updateOrgByOrgId(org);
            updateCode(org);
        }
        return updateState(org);
    }

    /**
     * 子部递归门生成部门编号
     * 
     * @param org
     */
    private void updateCode(Org org) {
        List<Org> OrgList = orgMapper.queryOrgSubByParentId(org.getOrgId());
        if (!CollectionUtils.isEmpty(OrgList)) {
            int intCode = 1;
            for (Org org2Item : OrgList) {
                String code = "01";
                if (intCode < 10) {
                    code = org.getOrgCode() + ".0" + intCode;
                } else {
                    code = org.getOrgCode() + "." + String.valueOf(intCode);
                }
                org2Item.setOrgCode(code);
                intCode++;
                orgMapper.updateOrgByOrgId(org2Item);
                updateCode(org2Item);
            }
        }
    }

    /**
     * 父级部门的状态被修改，自己部门的状态递归出来一并修改
     * 
     * @param org
     */
    private String updateState(Org org) {
        List<Org> OrgList = orgMapper.queryOrgSubByParentId(org.getOrgId());
        if (CollectionUtils.isEmpty(OrgList)) {
            return "部门的状态修改成功";
        } else {
            for (Org orgItem : OrgList) {
                orgItem.setState(org.getState());
                orgMapper.updateOrgByOrgId(orgItem);
                updateState(orgItem);
            }
            return "部门的状态修改成功";
        }

    }

    /**
     * 删除部门
     * 
     * @param orgId
     * @return
     */
    @Override
    public String deleteOrgByOrgId(Org org) {
        String result = "";
        int count = orgMapper.deleteOrgByOrgId(org.getOrgId());
        if (count > 0) {
            result = "删除部门成功";
        } else {
            result = "删除部门失败";
        }
        return result;
    }

    /**
     * 查询单个部门
     * 
     * @return
     */
    @Override
    public Org queryOrgByOrgId(String orgId) {
        Org orgObject = orgMapper.queryOrgByOrgId(orgId);
        if (orgObject != null) {
            return orgObject;
        }
        return orgObject;
    }

    /**
     * 分页查询部门列表
     * 
     * @param org
     * @return
     */
    @Override
    public PageInfo queryOrgsByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Org> list = orgMapper.queryOrgsByPage();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        PageInfo pageResult = new PageInfo(list);
        pageResult.setList(list);
        return pageResult;
    }

    /**
     * 查询多个子部门 递归查询本节点的id及孩子节点的id
     * 
     * @param OrgId
     * @return
     */
    @Override
    public List<Tree<Org>> queryOrgAndSubByOrgId(String OrgId) {
        List<Tree<Org>> trees = new ArrayList<Tree<Org>>();
        List<Tree<Org>> list = null;
        List<Org> orgListItem = new ArrayList<Org>();
        findChildCategory(orgListItem, OrgId);
        if (!CollectionUtils.isEmpty(orgListItem)) {
            for (Org org : orgListItem) {
                Tree<Org> tree = new Tree<Org>();
                tree.setId(org.getOrgId());
                tree.setParentId(org.getParentId());
                tree.setText(org.getOrgname());
                Map<String, Object> attributes = new HashMap<>(16);
                attributes.put("state", org.getState());
                attributes.put("sortNum", org.getSortNum());
                attributes.put("orgCode", org.getOrgCode());
                attributes.put("parentOrgname", org.getParentOrgname());
                attributes.put("creatUserId", org.getCreatUserId());
                tree.setAttributes(attributes);
                trees.add(tree);
            }
            // 默认顶级菜单为０，根据数据库实际情况调整
            list = BuildTree.buildList(trees, OrgId);
        }
        return list;
    }

    // 递归算法,算出子节点
    private List<Org> findChildCategory(List<Org> orgListItem, String orgId) {
        Org org = orgMapper.queryOrgByOrgId(orgId);
        if (org != null) {
            orgListItem.add(org);
        }
        // 查找子节点,递归算法一定要有一个退出的条件
        List<Org> OrgList = orgMapper.queryOrgSubByParentId(orgId);
        if (OrgList != null) {
            for (Org org2Item : OrgList) {
                findChildCategory(orgListItem, org2Item.getOrgId());
            }
        }
        return orgListItem;
    }

    /**
     * 删除多个子部门机构 递归删除
     */
    @Override
    public String deleteOrgSubByOrgId(String orgId) {
        orgMapper.deleteOrgByOrgId(orgId);
        List<Org> OrgList = orgMapper.queryOrgSubByParentId(orgId);
        for (Org org : OrgList) {
            orgMapper.deleteOrgByOrgParentId(org.getParentId());
            deleteOrgSubByOrgId(org.getOrgId());
        }
        return "刪除多个子部门成功";
    }

    @Override
    public String addOrgToUser(Org org) {
        int count = 0;
        List<String> userIds = Arrays.asList(org.getUserIds());
        for (String ids : userIds) {
            OrgToUser orgAndUser = orgMapper.queryOrgAndUserByOrgIdAndUserId(ids, org.getOrgId());
            if (orgAndUser != null) {
                orgMapper.deleteOrgByUserIdAndOrgId(ids, org.getOrgId());
            }
            User user = userMapper.queryUserByUserId(ids);
            Org orgObj = orgMapper.queryOrgByOrgId(org.getOrgId());
            if (user == null) {
                return "传入的用户参数不正确";
            } else if (orgObj == null) {
                return "传入的部门参数不正确";
            } else {
                Org o = new Org();
                o.setUserId(ids);
                o.setOrgId(org.getOrgId());
                long date = new Date().getTime();
                o.setCreateTime(new Date(date));
                o.setModifyTime(new Date(date));
                o.setCreatUserId(org.getCreatUserId());
                o.setModifyUserId(org.getModifyUserId());
                count = orgMapper.updateOrgToUser(o);
            }
        }
        if (count > 0) {
            return "部门和用户绑定成功";
        } else {
            return "部门和用户绑定失败";
        }
    }

    @Override
    public List<User> queryUsersByOrgId(String orgId) {

        List<User> usersList = new ArrayList<User>();
        List<OrgToUser> OrgAndUserList = orgMapper.queryOrgAndUserByOrgId(orgId);
        if (CollectionUtils.isEmpty(OrgAndUserList)) {
            return usersList;
        }
        for (OrgToUser orgAndUser : OrgAndUserList) {
            User user = userMapper.queryUserByUserId(orgAndUser.getUserId());
            if (user != null) {
                usersList.add(user);
            }
        }
        return usersList;
    }

    @Override
    public PageInfo queryOrgInfo(Org org, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Org> orgsList = orgMapper.queryOrgByParameters(org);
        if (CollectionUtils.isEmpty(orgsList)) {
            return null;
        }
        PageInfo pageResult = new PageInfo(orgsList);
        pageResult.setList(orgsList);
        return pageResult;
    }

    @Override
    public String updateOrgByOrgIdAndState(Org org) {
        orgMapper.updateOrgByOrgIdAndState(org);
        return updateState(org);
    }

    @Override
    public String updateOrgToUser(Org org) {
        int count = 0;
        List<String> userIds = Arrays.asList(org.getUserIds());
        orgMapper.deleteOrgToUserByOrgId(org.getOrgId());
        for (String ids : userIds) {
            User user = userMapper.queryUserByUserId(ids);
            Org orgObj = orgMapper.queryOrgByOrgId(org.getOrgId());
            if (user == null) {
                return "传入的用户参数不正确";
            } else if (orgObj == null) {
                return "传入的部门参数不正确";
            } else {
                Org o = new Org();
                o.setUserId(ids);
                o.setOrgId(org.getOrgId());
                long date = new Date().getTime();
                o.setModifyTime(new Date(date));
                o.setCreateTime(new Date(date));
                o.setCreatUserId(org.getCreatUserId());
                o.setModifyUserId(org.getModifyUserId());
                count = orgMapper.updateOrgToUser(o);
            }
        }
        if (count > 0) {
            return "部门和用户修改成功";
        } else {
            return "部门和用户修改失败";
        }
    }
}
