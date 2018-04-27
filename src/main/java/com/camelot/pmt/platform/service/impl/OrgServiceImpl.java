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
import org.springframework.util.StringUtils;

import com.camelot.pmt.platform.common.Modular;
import com.camelot.pmt.platform.log.LogAspect;
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

	@Autowired
	private LogAspect logAspect;

	/**
	 * 查询部门列表
	 *
	 * @return
	 */
	@Override
	public List<Tree<Org>> queryAllOrg() {
		List<Tree<Org>> list = null;
		List<Tree<Org>> trees = new ArrayList<Tree<Org>>();
		List<Org> queryAllOrg = orgMapper.queryAllOrg();
		if (!CollectionUtils.isEmpty(queryAllOrg)) {
			for (Org org : queryAllOrg) {
				if (!StringUtils.isEmpty(org.getOrgLeader())) {
					User user = userMapper.queryUserByUserId(org.getOrgLeader());
					if ("1".equals(user.getState())) {
						org.setOrgLeadername(null);
					}
				}
				User user = userMapper.queryUserByUserId(org.getCreatUserId());
				Tree<Org> tree = new Tree<Org>();
				tree.setId(org.getOrgId());
				tree.setParentId(org.getParentId());
				tree.setText(org.getOrgname());
				Map<String, Object> attributes = new HashMap<>(16);
				attributes.put("state", org.getState());
				attributes.put("sortNum", org.getSortNum());
				attributes.put("orgCode", org.getOrgCode());
				attributes.put("parentOrgname", org.getParentOrgname());
				attributes.put("creatOrgUsername", user.getUsername());
				attributes.put("orgLeadername", org.getOrgLeadername());
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
		if (!StringUtils.isEmpty(org.getOrgLeader())) {
			OrgToUser orgToUser = orgMapper.queryOrgAndUserByOrgIdAndUserId(org.getOrgLeader(), org.getOrgId());
			if (orgToUser == null) {
				OrgToUser otu = new OrgToUser();
				otu.setOrgId(org.getOrgId());
				otu.setUserId(org.getOrgLeader());
				otu.setCreateUserId(org.getCreatUserId());
				otu.setModifyUserId(org.getModifyUserId());
				long date = new Date().getTime();
				otu.setCreateTime(new Date(date));
				otu.setModifyTime(new Date(date));
				orgMapper.updateOrgToUser(otu);
			}
			if (nums > 0) {
				result = "添加部门成功!";
				// 添加日志
				logAspect.insertAddLog(org, Modular.ORG, org.getCreatUserId());
				return result;
			} else {
				result = "添加部门失败!";
				return result;
			}
		}
		if (nums > 0) {
			// 添加日志
			logAspect.insertAddLog(org, Modular.ORG, org.getCreatUserId());
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
		int num = 0;
		int val = 0;
		Org orgBefore = orgMapper.queryOrgByOrgId(org.getOrgId());
		if (org.getParentId().equals(orgBefore.getParentId())) {
			if (StringUtils.isEmpty(org.getOrgLeader())) {
				num = orgMapper.updateOrgByOrgId(org);
				orgMapper.deleteOrgByUserIdAndOrgId(orgBefore.getOrgLeader(), org.getOrgId());
				Org orgAfter = orgMapper.queryOrgByOrgId(org.getOrgId());
				if (num > 0) {
					// 添加日志

					logAspect.insertUpdateLog(orgAfter, orgBefore, Modular.ORG, org.getModifyUserId());
					updateState(org);
					return "部门修改成功";
				} else {
					return "部门修改失败";
				}

			} else {
				OrgToUser otu = new OrgToUser();
				otu.setOrgId(org.getOrgId());
				otu.setUserId(org.getOrgLeader());
				otu.setCreateUserId(org.getCreatUserId());
				otu.setModifyUserId(org.getModifyUserId());
				long date = new Date().getTime();
				otu.setCreateTime(new Date(date));
				otu.setModifyTime(new Date(date));
				val = orgMapper.updateOrgToUser(otu);
				num = orgMapper.updateOrgByOrgId(org);
				Org orgAfter = orgMapper.queryOrgByOrgId(org.getOrgId());
				if (val > 0 && num > 0) {
					// 添加日志

					logAspect.insertUpdateLog(orgAfter, orgBefore, Modular.ORG, org.getModifyUserId());
					updateState(org);
					return "修改部门成功";
				} else {
					return "修改部门成功";
				}
			}
		} else {
			org.setOrgCode(createCode(org));
			if (StringUtils.isEmpty(org.getOrgLeader())) {
				num = orgMapper.updateOrgByOrgId(org);
				Org orgAfter = orgMapper.queryOrgByOrgId(org.getOrgId());
				orgMapper.deleteOrgByUserIdAndOrgId(orgBefore.getOrgLeader(), org.getOrgId());
				if (num > 0) {
					// 添加日志

					logAspect.insertUpdateLog(orgAfter, orgBefore, Modular.ORG, org.getModifyUserId());
					updateCode(org);
					updateState(org);
					return "部门修改成功";
				} else {
					return "部门修改失败";
				}
			} else {
				OrgToUser otu = new OrgToUser();
				otu.setOrgId(org.getOrgId());
				otu.setUserId(org.getOrgLeader());
				otu.setCreateUserId(org.getCreatUserId());
				otu.setModifyUserId(org.getModifyUserId());
				long date = new Date().getTime();
				otu.setCreateTime(new Date(date));
				otu.setModifyTime(new Date(date));
				val = orgMapper.updateOrgToUser(otu);
				num = orgMapper.updateOrgByOrgId(org);
				Org orgAfter = orgMapper.queryOrgByOrgId(org.getOrgId());
				if (val > 0 && num > 0) {
					// 添加日志

					logAspect.insertUpdateLog(orgAfter, orgBefore, Modular.ORG, org.getModifyUserId());
					updateCode(org);
					updateState(org);
					return "修改部门成功";
				} else {
					return "修改部门成功";
				}
			}
		}
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
				Org orgOld = orgMapper.queryOrgByOrgId(orgItem.getOrgId());
				orgItem.setState(org.getState());
				int num = orgMapper.updateOrgByOrgId(orgItem);
				Org orgAfter = orgMapper.queryOrgByOrgId(orgItem.getOrgId());
				if (num > 0) {
					// 添加日志

					logAspect.insertUpdateLog(orgAfter, orgOld, Modular.ORG, org.getModifyUserId());
				}
				updateState(orgItem);
			}
			return "部门的状态修改成功";
		}

	}

	private String updateStates(Org org) {
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
		Org orgObj = orgMapper.queryOrgByOrgId(org.getOrgId());
		if (!StringUtils.isEmpty(orgObj.getOrgLeader())) {
			orgMapper.deleteOrgByUserIdAndOrgId(orgObj.getOrgLeader(), orgObj.getOrgId());
		}
		int count = orgMapper.deleteOrgByOrgId(org.getOrgId());
		if (count > 0) {
			// 添加日志
			logAspect.insertDeleteLog(Modular.ORG, orgObj.getModifyUserId(), orgObj.getOrgname());
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
		if (!StringUtils.isEmpty(orgObject.getOrgLeader())) {
			User user = userMapper.queryUserByUserId(orgObject.getOrgLeader());
			if ("1".equals(user.getState())) {
				orgObject.setOrgLeadername(null);
			}
		} 
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
	public PageInfo<Org> queryOrgsByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Org> list = orgMapper.queryOrgsByPage();
		List<Org> listItem = new ArrayList<Org>();
		for (Org org : list) {
			if (StringUtils.isEmpty(org.getOrgLeader())) {
				org.setOrgLeadername("");
			} else {
				User userObj = userMapper.queryUserByUserId(org.getOrgLeader());
				if ("1".equals(userObj.getState())) {
					org.setOrgLeadername(null);
				}
			}
			User user = userMapper.queryUserByUserId(org.getCreatUserId());
			org.setCreatOrgUsername(user.getUsername());
			listItem.add(org);
		}
		if (CollectionUtils.isEmpty(listItem)) {
			return null;
		}
		PageInfo<Org> pageResult = new PageInfo<Org>(listItem);
		pageResult.setList(listItem);
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
				if (!StringUtils.isEmpty(org.getOrgLeader())) {
					User user = userMapper.queryUserByUserId(org.getOrgLeader());
					if ("1".equals(user.getState())) {
						org.setOrgLeadername(null);
					}
				} 
				User user = userMapper.queryUserByUserId(org.getCreatUserId());
				Tree<Org> tree = new Tree<Org>();
				tree.setId(org.getOrgId());
				tree.setParentId(org.getParentId());
				tree.setText(org.getOrgname());
				Map<String, Object> attributes = new HashMap<>(16);
				attributes.put("state", org.getState());
				attributes.put("sortNum", org.getSortNum());
				attributes.put("orgCode", org.getOrgCode());
				attributes.put("parentOrgname", org.getParentOrgname());
				attributes.put("creatOrgUsername", user.getUsername());
				attributes.put("orgLeadername", org.getOrgLeadername());
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
		if (!StringUtils.isEmpty(org.getOrgLeader())) {
			User user = userMapper.queryUserByUserId(org.getOrgLeader());
			if ("1".equals(user.getState())) {
				org.setOrgLeadername("");
			}
		} 
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
		int num = 0;
		List<Org> OrgList = orgMapper.queryOrgSubByParentId(orgId);
		Org org = orgMapper.queryOrgByOrgId(orgId);
		if (CollectionUtils.isEmpty(OrgList)) {
			if (!StringUtils.isEmpty(org.getOrgLeader())) {
				num = orgMapper.deleteOrgByOrgId(orgId);
				orgMapper.deleteOrgByUserIdAndOrgId(org.getOrgLeader(), orgId);
			} else {
				num = orgMapper.deleteOrgByOrgId(orgId);
			}
		} else {
			num = orgMapper.deleteOrgByOrgId(orgId);
			if (!StringUtils.isEmpty(org.getOrgLeader())) {
				orgMapper.deleteOrgByUserIdAndOrgId(org.getOrgLeader(), orgId);
			}
			for (Org orgObj : OrgList) {
				deleteOrgSubByOrgId(orgObj.getOrgId());
			}
		}
		if (num > 0) {
			// 添加日志
			logAspect.insertDeleteLog(Modular.ORG, org.getModifyUserId(), org.getOrgname());
			return "删除本部门以及子部门成功";
		} else {
			return "删除本部门以及子部门失败";
		}
	}

	@Override
	public String addOrgToUser(OrgToUser orgToUser) {
		int count = 0;
		Org orgObj = null;
		User user = null;
		String stringBuffer = "";
		List<String> userIds = Arrays.asList(orgToUser.getUserIds());
		for (String ids : userIds) {
			OrgToUser orgAndUser = orgMapper.queryOrgAndUserByOrgIdAndUserId(ids, orgToUser.getOrgId());
			if (orgAndUser != null) {
				orgMapper.deleteOrgByUserIdAndOrgId(ids, orgToUser.getOrgId());
			}
			user = userMapper.queryUserByUserId(ids);
			stringBuffer += user.getUsername() + ",";
			orgObj = orgMapper.queryOrgByOrgId(orgToUser.getOrgId());
			if (user == null) {
				return "传入的用户参数不正确";
			} else if (orgObj == null) {
				return "传入的部门参数不正确";
			} else {
				OrgToUser otu = new OrgToUser();
				otu.setUserId(ids);
				otu.setOrgId(orgToUser.getOrgId());
				long date = new Date().getTime();
				otu.setCreateTime(new Date(date));
				otu.setModifyTime(new Date(date));
				otu.setCreateUserId(orgToUser.getCreateUserId());
				otu.setModifyUserId(orgToUser.getModifyUserId());
				count = orgMapper.updateOrgToUser(otu);
			}
		}
		if (count > 0) {
			logAspect.insertBindingLog(stringBuffer, "添加新用户用户", Modular.ORGTOUSER, orgToUser.getCreateUserId());
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
	public PageInfo<Org> queryOrgInfo(Org org, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Org> orgsList = orgMapper.queryOrgByParameters(org);
		List<Org> listItem = new ArrayList<Org>();
		if (!CollectionUtils.isEmpty(orgsList)) {
			for (Org orgObj : orgsList) {
				if (!StringUtils.isEmpty(orgObj.getOrgLeader())) {
					User userObj = userMapper.queryUserByUserId(orgObj.getOrgLeader());
					if ("1".equals(userObj.getState())) {
						orgObj.setOrgLeadername(null);
					}
				} 
				User user = userMapper.queryUserByUserId(orgObj.getCreatUserId());
				orgObj.setCreatOrgUsername(user.getUsername());
				listItem.add(orgObj);
			}
		}
		PageInfo<Org> pageResult = new PageInfo<Org>(listItem);
		pageResult.setList(listItem);
		return pageResult;
	}

	@Override
	public String updateOrgByOrgIdAndState(Org org) {
		Org orgBefore = orgMapper.queryOrgByOrgId(org.getOrgId());
		int num = orgMapper.updateOrgByOrgIdAndState(org);
		Org orgAfter = orgMapper.queryOrgByOrgId(org.getOrgId());
		if (num > 0) {
			// 添加日志
			logAspect.insertUpdateLog(orgAfter, orgBefore, Modular.ORG, org.getModifyUserId());
			updateState(org);
			return "部门状态修改成功";
		} else {
			return "部门状态修改成功";
		}

	}

	@Override
	public String updateOrgToUser(OrgToUser orgToUser) {
		int count = 0;
		String stringBuffer1 = "";
		String stringBuffer2 = "";
		List<OrgToUser> orgToUserBefore = orgMapper.queryOrgAndUserByOrgId(orgToUser.getOrgId());
		for (OrgToUser orgToUser2 : orgToUserBefore) {
			User userBefore = userMapper.queryUserByUserId(orgToUser2.getUserId());
			stringBuffer1 += userBefore.getUsername() + ",";
		}
		List<String> userIds = Arrays.asList(orgToUser.getUserIds());
		orgMapper.deleteOrgToUserByOrgId(orgToUser.getOrgId());
		Org org = orgMapper.queryOrgByOrgId(orgToUser.getOrgId());
		if (!StringUtils.isEmpty(org.getOrgLeader())) {
			OrgToUser otu = new OrgToUser();
			otu.setUserId(org.getOrgLeader());
			otu.setOrgId(orgToUser.getOrgId());
			long date = new Date().getTime();
			otu.setCreateTime(org.getCreateTime());
			otu.setModifyTime(org.getModifyTime());
			otu.setCreateUserId(org.getCreatUserId());
			otu.setModifyUserId(org.getModifyUserId());
			orgMapper.updateOrgToUser(otu);
		}
		for (String ids : userIds) {
			User userAfter = userMapper.queryUserByUserId(ids);
			stringBuffer2 += userAfter.getUsername() + ",";
			Org orgObj = orgMapper.queryOrgByOrgId(orgToUser.getOrgId());
			if (userAfter == null) {
				return "传入的用户参数不正确";
			} else if (orgObj == null) {
				return "传入的部门参数不正确";
			} else {
				OrgToUser otu = new OrgToUser();
				otu.setUserId(ids);
				otu.setOrgId(orgToUser.getOrgId());
				long date = new Date().getTime();
				otu.setCreateTime(new Date(date));
				otu.setModifyTime(new Date(date));
				otu.setCreateUserId(orgToUser.getCreateUserId());
				otu.setModifyUserId(orgToUser.getModifyUserId());
				count = orgMapper.updateOrgToUser(otu);
			}
		}
		if (count > 0) {
			logAspect.insertBindingLog(stringBuffer2, stringBuffer1, Modular.ORGTOUSER, orgToUser.getCreateUserId());
			return "部门和用户修改成功";
		} else {
			return "部门和用户修改失败";
		}
	}
}
