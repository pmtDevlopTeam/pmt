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
	public List<Tree<Org>> queryAllOrgs() {
		List<Tree<Org>> list=null;
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
			int count = orgMapper.checkOrgCodeIsExist(org.getOrgCode());
			if (count > 0) {
				result="部门编号已存在请重新添加";
				return result;
			}
			int num = orgMapper.checkOrgNameIsExist(org.getOrgname());
			if (num > 0) {
				result="部门名称已存在请重新添加";
				return result;
			}
			org.setOrgId(UUIDUtil.getUUID());
			int nums = orgMapper.addOrg(org);
			if (nums > 0) {
				result="添加用户成功!";
				return result;
			} else {
				result="添加用户失败!";
				return result;
			}
	}
	/**
	 * 修改部门
	 * 
	 * @param org
	 * @return
	 */
	@Override
	public String updateOrgByOrgId(Org org) {
			long date = new Date().getTime();
			org.setModifyTime(new Date(date));
			int nums = orgMapper.updateOrgByOrgId(org);
			if (nums > 0) {
				return "部门修改成功";
			} else {
				return "部门修改失败";
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
				result="删除部门成功";
			} else {
				result="删除部门失败";
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
			if (orgObject !=null) {
					return orgObject;
				}
			return orgObject;
	}

	/**
	 * 分页查询部门列表
	 * @param org
	 * @return
	 */
	@Override
	public PageInfo queryOrgsByPage(int pageNum,int pageSize) {
			PageHelper.startPage(pageNum,pageSize);
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
	 * @param OrgId
	 * @return
	 */
	@Override
	public List<Tree<Org>> queryOrgAndSubByOrgId(String OrgId) {
		List<Tree<Org>> trees = new ArrayList<Tree<Org>>();
		List<Tree<Org>> list=null;
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
						tree.setAttributes(attributes);
						trees.add(tree);
					}
					// 默认顶级菜单为０，根据数据库实际情况调整
					 list = BuildTree.buildList(trees, "0");
					return list;
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
		for (Org org2Item : OrgList) {
			findChildCategory(orgListItem, org2Item.getOrgId());
		}
		return orgListItem;
	}
	/**
	 * 删除多个子部门机构  递归删除
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
	/** 组织机构列表详情(关系到用户  )
	 * @param OrgToUser
	 * @return JSONObject
	 * 
	 **/
	@Override
	public List<OrgToUser> queryOrgsDetail() {
			List<OrgToUser> orgList = orgMapper.selectOrgsDetail();
			List<OrgToUser> orgToUserList = new ArrayList<OrgToUser>();
			for (OrgToUser orgToUser : orgList) {
				OrgToUser otu = new OrgToUser();
				otu.setOrgId(orgToUser.getOrgId());
				otu.setOrgCode(orgToUser.getOrgCode());
				otu.setOrgname(orgToUser.getOrgname());
				if ("0".equals(orgToUser.getParentId())) {
					otu.setOrgParentName("总部门（根节点）");
				}else{
					otu.setOrgParentName(orgToUser.getOrgParentName());
				}
				otu.setParentId(orgToUser.getParentId());
				otu.setState(orgToUser.getState());
				otu.setCreateTime(orgToUser.getCreateTime());
				otu.setUserList(orgToUser.getUserList());
				orgToUserList.add(otu);
			}
		return orgToUserList;
	}
	/**
	 * 组织机构   根据orgId查看详情(关系到用户  即部门负责人)
	 * List<OrgToUser> orgToUserList = orgMapper.selectOrgsDetailByOrgId(orgId);
	 */
	@Override
	public List<OrgToUser> queryOrgsDetailByOrgId(String orgId) {
			List<OrgToUser> orgList = orgMapper.selectOrgsDetailByOrgId(orgId);
			List<OrgToUser> orgToUserList = new ArrayList<OrgToUser>();
			OrgToUser otu = new OrgToUser();
			for (OrgToUser orgToUser : orgList) {
				otu.setOrgId(orgToUser.getOrgId());
				otu.setOrgCode(orgToUser.getOrgCode());
				otu.setOrgname(orgToUser.getOrgname());
				if ("0".equals(orgToUser.getParentId())) {
					otu.setOrgParentName("总部门（根节点）");
				}else{
					otu.setOrgParentName(orgToUser.getOrgParentName());
				}
				otu.setParentId(orgToUser.getParentId());
				otu.setState(orgToUser.getState());
				otu.setCreateTime(orgToUser.getCreateTime());
				otu.setUserList(orgToUser.getUserList());
				orgToUserList.add(otu);
			}
		return orgToUserList;
	}

	@Override
	public String addOrgToUser(Org org) {
		int count = 0;
			List<String> userIds = Arrays.asList(org.getUserIds());
			for (String ids : userIds) {
				OrgAndUser orgAndUser = orgMapper.queryOrgAndUserByOrgIdAndUserId(ids,org.getOrgId());
				if (orgAndUser != null) {
					orgMapper.deleteOrgByUserIdAndOrgId(ids,org.getOrgId());
				}
				User user = userMapper.queryUserByUserId(ids);
				Org orgObj = orgMapper.queryOrgByOrgId(org.getOrgId());
				if (user == null) {
					return "传入的用户参数不正确";
				}else if (orgObj == null) {
					return "传入的部门参数不正确";
				}else{
					Org o = new Org();
					o.setUserId(ids);
					o.setOrgId(org.getOrgId());
					long date = new Date().getTime();
					o.setCreateTime(new Date(date));
		            o.setModifyTime(new Date(date));
					count=orgMapper.updateOrgToUser(o);
				}
			}
			if (count >0) {
				return "部门和用户绑定成功";
			}else{
				return "部门和用户绑定失败";
			}
	}

	@Override
	public List<User> queryUsersByOrgId(String orgId) {
		
			List<User> usersList = new ArrayList<User>();
			List<OrgAndUser> OrgAndUserList = orgMapper.queryOrgAndUserByOrgId(orgId);
			if (CollectionUtils.isEmpty(OrgAndUserList)) {
                return usersList;
            }
			for (OrgAndUser orgAndUser : OrgAndUserList) {
				User user = userMapper.queryUserByUserId(orgAndUser.getUserId());
				if (user != null) {
					usersList.add(user);
				}
			}
			return usersList;
	}

	@Override
	public PageInfo queryOrgInfo(Org org,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
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
		int count = orgMapper.updateOrgByOrgIdAndState(org);
		if (count>0) {
			return "状态修改成功";
		}else{
			return "状态修改失败";
		}
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
				}else if (orgObj == null) {
					return "传入的部门参数不正确";
				}else{
					Org o = new Org();
					o.setUserId(ids);
					o.setOrgId(org.getOrgId());
					long date = new Date().getTime();
		            o.setModifyTime(new Date(date));
					count=orgMapper.updateOrgToUser(o);
				}
			}
			if (count >0) {
				return "部门和用户修改成功";
			}else{
				return "部门和用户修改失败";
			}
	}
}
