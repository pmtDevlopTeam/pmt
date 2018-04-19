package com.camelot.pmt.platform.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.mapper.OrgMapper;
import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.platform.model.Org;
import com.camelot.pmt.platform.model.OrgAndUser;
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
    public ExecuteResult<List<Tree<Org>>> queryAllOrgs() {
        ExecuteResult<List<Tree<Org>>> result = new ExecuteResult<List<Tree<Org>>>();
        List<Tree<Org>> trees = new ArrayList<Tree<Org>>();

        try {
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
                    tree.setAttributes(attributes);
                    trees.add(tree);
                }
                // 默认顶级菜单为０，根据数据库实际情况调整
                List<Tree<Org>> list = BuildTree.buildList(trees, "0");
                result.setResult(list);
                return result;
            } else {
                return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 新增部门
     * 
     * @param org
     * @return
     */
    @Override
    public ExecuteResult<String> creatOrg(Org org) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            int count = orgMapper.checkOrgCodeIsExist(org.getOrgCode());
            if (count > 0) {
                result.setResult("部门编号已存在请重新添加");
                return result;
            }
            int num = orgMapper.checkOrgNameIsExist(org.getOrgname());
            if (num > 0) {
                result.setResult("部门名称已存在请重新添加");
                return result;
            }
            org.setOrgId(UUIDUtil.getUUID());
            int nums = orgMapper.createOrg(org);
            if (nums > 0) {
                result.setResult("添加用户成功!");
            } else {
                result.setResult("添加用户失败!");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;

    }

    /**
     * 修改部门
     * 
     * @param org
     * @return
     */
    @Override
    public ExecuteResult<String> modifyOrgByOrgId(Org org) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            long date = new Date().getTime();
            org.setModifyTime(new Date(date));

            int nums = orgMapper.modifyOrgByOrgId(org);
            if (nums > 0) {
                result.setResult("部门修改成功");
            } else {
                result.setResult("部门修改失败");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 删除部门
     * 
     * @param orgId
     * @return
     */
    @Override
    public ExecuteResult<String> deleteOrgByOrgId(Org org) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            int count = orgMapper.deleteOrgByOrgId(org.getOrgId());
            if (count > 0) {
                result.setResult("删除部门成功！");
            } else {
                result.setResult("删除部门失败！");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询单个部门
     * 
     * @return
     */
    @Override
    public ExecuteResult<Org> queryOrgByOrgId(String orgId) {
        ExecuteResult<Org> result = new ExecuteResult<Org>();
        try {
            Org orgObject = orgMapper.queryOrgByOrgId(orgId);
            if (orgObject != null) {
                result.setResult(orgObject);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 分页查询部门列表
     * 
     * @param org
     * @return
     */
    @Override
    public ExecuteResult<PageInfo> queryOrgsByPage(int pageNum, int pageSize) {
        ExecuteResult<PageInfo> result = new ExecuteResult<PageInfo>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Org> list = orgMapper.findOrgsByPage();
            if (CollectionUtils.isEmpty(list)) {
                return result;
            }
            PageInfo pageResult = new PageInfo(list);
            pageResult.setList(list);
            result.setResult(pageResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询多个子部门 递归查询本节点的id及孩子节点的id
     * 
     * @param OrgId
     * @return
     */
    @Override
    public ExecuteResult<List<Tree<Org>>> queryOrgAndChildrenById(String OrgId) {
        ExecuteResult<List<Tree<Org>>> result = new ExecuteResult<List<Tree<Org>>>();
        List<Tree<Org>> trees = new ArrayList<Tree<Org>>();
        List<Org> orgListItem = new ArrayList<Org>();
        try {
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
                    tree.setAttributes(attributes);
                    trees.add(tree);
                }
                // 默认顶级菜单为０，根据数据库实际情况调整
                List<Tree<Org>> list = BuildTree.buildList(trees, "0");
                result.setResult(list);
            } else {
                result.setResultMessage("查询的部门不存在");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    // 递归算法,算出子节点
    private List<Org> findChildCategory(List<Org> orgListItem, String orgId) {
        Org org = orgMapper.queryOrgByOrgId(orgId);
        if (org != null) {
            orgListItem.add(org);
        }
        // 查找子节点,递归算法一定要有一个退出的条件
        List<Org> OrgList = orgMapper.selectOrgChildrenByParentId(orgId);
        for (Org org2Item : OrgList) {
            findChildCategory(orgListItem, org2Item.getOrgId());
        }
        return orgListItem;
    }

    /**
     * 删除多个子部门机构 递归删除
     */
    @Override
    public ExecuteResult<String> deleteOrgByOrgId(String orgId) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        orgMapper.deleteOrgByOrgId(orgId);
        try {
            List<Org> OrgList = orgMapper.selectOrgChildrenByParentId(orgId);
            for (Org org : OrgList) {
                orgMapper.deleteOrgByOrgParentId(org.getParentId());
                deleteOrgByOrgId(org.getOrgId());
            }
            result.setResult("刪除多个子部门成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;

    }

    /**
     * 组织机构列表详情(关系到用户 )
     * 
     * @param OrgToUser
     * @return JSONObject
     *
     **/
    @Override
    public ExecuteResult<List<OrgToUser>> queryOrgsDetail() {
        ExecuteResult<List<OrgToUser>> result = new ExecuteResult<List<OrgToUser>>();
        try {
            List<OrgToUser> orgList = orgMapper.selectOrgsDetail();
            List<OrgToUser> orgToUserList = new ArrayList<OrgToUser>();
            for (OrgToUser orgToUser : orgList) {
                OrgToUser otu = new OrgToUser();
                otu.setOrgId(orgToUser.getOrgId());
                otu.setOrgCode(orgToUser.getOrgCode());
                otu.setOrgname(orgToUser.getOrgname());
                if ("0".equals(orgToUser.getParentId())) {
                    otu.setOrgParentName("总部门（根节点）");
                } else {
                    otu.setOrgParentName(orgToUser.getOrgParentName());
                }
                otu.setParentId(orgToUser.getParentId());
                otu.setState(orgToUser.getState());
                otu.setCreateTime(orgToUser.getCreateTime());
                otu.setUserList(orgToUser.getUserList());
                orgToUserList.add(otu);
            }
            result.setResult(orgToUserList);
            if (CollectionUtils.isEmpty(orgToUserList)) {
                result.addErrorMessage("组织机构部门不存在");
            }
            result.setResult(orgToUserList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 组织机构 根据orgId查看详情(关系到用户 即部门负责人) List<OrgToUser> orgToUserList =
     * orgMapper.selectOrgsDetailByOrgId(orgId);
     */
    @Override
    public ExecuteResult<List<OrgToUser>> queryOrgsDetailByOrgId(String orgId) {
        ExecuteResult<List<OrgToUser>> result = new ExecuteResult<List<OrgToUser>>();
        try {

            List<OrgToUser> orgList = orgMapper.selectOrgsDetailByOrgId(orgId);
            List<OrgToUser> orgToUserList = new ArrayList<OrgToUser>();
            OrgToUser otu = new OrgToUser();
            for (OrgToUser orgToUser : orgList) {
                otu.setOrgId(orgToUser.getOrgId());
                otu.setOrgCode(orgToUser.getOrgCode());
                otu.setOrgname(orgToUser.getOrgname());
                if ("0".equals(orgToUser.getParentId())) {
                    otu.setOrgParentName("总部门（根节点）");
                } else {
                    otu.setOrgParentName(orgToUser.getOrgParentName());
                }
                otu.setParentId(orgToUser.getParentId());
                otu.setState(orgToUser.getState());
                otu.setCreateTime(orgToUser.getCreateTime());
                otu.setUserList(orgToUser.getUserList());
                orgToUserList.add(otu);
            }
            result.setResult(orgToUserList);
            if (CollectionUtils.isEmpty(orgToUserList)) {
                result.addErrorMessage("组织机构部门不存在");
            }
            result.setResult(orgToUserList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> addOrgToUser(Org org) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        int count = 0;
        try {
            List<String> userIds = Arrays.asList(org.getUserIds());
            for (String ids : userIds) {
                OrgAndUser orgAndUser = orgMapper.selectOrgAndUserByOrgIdAndUserId(ids, org.getOrgId());
                if (orgAndUser != null) {
                    orgMapper.deleteOrgByUserIdAndOrgId(ids, org.getOrgId());
                }
                User user = userMapper.selectUserById(ids);
                Org orgObj = orgMapper.queryOrgByOrgId(org.getOrgId());
                if (user == null) {
                    result.setResult("传入的用户参数不正确");
                    return result;
                } else if (orgObj == null) {
                    result.setResult("传入的部门参数不正确");
                    return result;
                } else {
                    Org o = new Org();
                    o.setUserId(ids);
                    o.setOrgId(org.getOrgId());
                    long date = new Date().getTime();
                    o.setCreateTime(new Date(date));
                    o.setModifyTime(new Date(date));
                    count = orgMapper.createOrgToUser(o);
                }
            }
            if (count > 0) {
                result.setResult("部门和用户绑定成功");
            } else {

                result.setResult("部门和用户绑定失败");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ExecuteResult<List<User>> queryOrgToUserByOrgId(String orgId) {
        ExecuteResult<List<User>> result = new ExecuteResult<List<User>>();
        try {
            List<OrgAndUser> OrgAndUserList = orgMapper.selectUsersByOrgId(orgId);
            if (CollectionUtils.isEmpty(OrgAndUserList)) {
                return result;
            }
            List<User> usersList = new ArrayList<User>();
            for (OrgAndUser orgAndUser : OrgAndUserList) {
                User user = userMapper.selectUserById(orgAndUser.getUserId());
                if (user != null) {
                    usersList.add(user);
                }
            }
            result.setResult(usersList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ExecuteResult<List<Org>> queryOrgByParameters(Org org) {
        ExecuteResult<List<Org>> result = new ExecuteResult<List<Org>>();
        try {
            List<Org> orgsList = orgMapper.queryOrgByParameters(org);
            if (CollectionUtils.isEmpty(orgsList)) {
                return result;
            }
            result.setResult(orgsList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> modifyOrgByOrgIdAndState(Org org) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        int count = orgMapper.modifyOrgByOrgIdAndState(org);
        if (count > 0) {
            result.setResult("状态修改成功");
        } else {
            result.setResult("状态修改失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> modifyOrgToUser(Org org) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        int count = 0;
        try {
            List<String> userIds = Arrays.asList(org.getUserIds());
            orgMapper.deleteOrgToUserByOrgId(org.getOrgId());
            for (String ids : userIds) {
                User user = userMapper.selectUserById(ids);
                Org orgObj = orgMapper.queryOrgByOrgId(org.getOrgId());
                if (user == null) {
                    result.setResult("传入的用户参数不正确");
                    return result;
                } else if (orgObj == null) {
                    result.setResult("传入的部门参数不正确");
                    return result;
                } else {
                    Org o = new Org();
                    o.setUserId(ids);
                    o.setOrgId(org.getOrgId());
                    long date = new Date().getTime();
                    o.setModifyTime(new Date(date));
                    count = orgMapper.createOrgToUser(o);
                }
            }
            if (count > 0) {
                result.setResult("部门和用户修改成功");
            } else {

                result.setResult("部门和用户修改失败");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

}
