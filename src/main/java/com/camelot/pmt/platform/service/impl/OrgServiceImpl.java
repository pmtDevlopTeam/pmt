package com.camelot.pmt.platform.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.platform.mapper.OrgMapper;
import com.camelot.pmt.platform.model.Org;
import com.camelot.pmt.platform.service.OrgService;
import com.camelot.pmt.platform.util.BuildTree;
import com.camelot.pmt.platform.util.Tree;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Service
public class OrgServiceImpl implements OrgService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private OrgMapper orgMapper;

	/**
	 * 查询部门列表
	 * 
	 * @return
	 */
	@Override
	public ExecuteResult<List<Tree<Org>>> findAllOrgs() {
		ExecuteResult<List<Tree<Org>>> result = new ExecuteResult<List<Tree<Org>>>();
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

		/*
		 * try { List<Org> list = orgMapper.queryAllOrg(); if(list.size() <= 0)
		 * { return result; } result.setResult(list); } catch (Exception e) {
		 * LOGGER.error(e.getMessage()); throw new RuntimeException(e); } return
		 * result;
		 */
	}

	/**
	 * 新增部门
	 * 
	 * @param org
	 * @return
	 */
	@Override
	public ExecuteResult<String> addOrg(Org org) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (org == null) {
				result.addErrorMessage("传入的部门实体有误!");
				return result;
			}
			/*// 对象不为空则添加新的项目实体
			String orgId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			org.setOrgId(orgId);*/
			int count = orgMapper.createOrg(org);
			if (count > 0) {
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
	public ExecuteResult<String> editOrg(Org org) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			long date = new Date().getTime();
			org.setModifyTime(new Date(date));
			int count = orgMapper.modifyOrgByOrgId(org);
			if (count > 0) {
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
	public ExecuteResult<String> deleteOrg(Org org) {
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
	public ExecuteResult<Org> findOrgById(String orgId) {

		ExecuteResult<Org> result = new ExecuteResult<Org>();
		try {
			if (!orgId.equals("") && !orgId.equals("0")) {
				Org orgObject = orgMapper.queryOrgByOrgId(orgId);
				result.setResult(orgObject);
				return result;
			}
			result.addErrorMessage("查询失败！");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 分页查询部门列表
	 * @param org
	 * @return
	 */
	@Override
	public ExecuteResult<DataGrid<Org>> queryOrgsByPage(Pager page) {
		ExecuteResult<DataGrid<Org>> result = new ExecuteResult<DataGrid<Org>>();
		try {
			List<Org> list = orgMapper.findOrgsByPage(page);
			if (CollectionUtils.isEmpty(list)) {
				DataGrid<Org> dog = new DataGrid<Org>();
				result.setResult(dog);
				return result;
			}
			DataGrid<Org> dog = new DataGrid<Org>();
			dog.setRows(list);
			Long total = orgMapper.queryCount();
			dog.setTotal(total);
			result.setResult(dog);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	/**
	 * 查询多个子部门 递归查询本节点的id及孩子节点的id
	 * @param OrgId
	 * @return
	 */
	@Override
	public ExecuteResult<List<Tree<Org>>> selectOrgAndChildrenById(String OrgId) {
		ExecuteResult<List<Tree<Org>>> result = new ExecuteResult<List<Tree<Org>>>();
		List<Tree<Org>> trees = new ArrayList<Tree<Org>>();
		List<Org> orgListItem = new ArrayList<Org>();
		try {
			if (!"".equals(OrgId) && OrgId != null) {
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
				return result;
			} else {
				result.setResultMessage("传入的参数不正确");
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
	 * 删除多个子部门机构  递归删除
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
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
		
	}
	
	@Override
	public ExecuteResult<List<Org>> queryOrgsDetail() {
		List<Org> orgList = orgMapper.selectOrgsDetail();
		return null;
	}

}
