package com.camelot.pmt.platform.service.impl;

import com.camelot.pmt.platform.common.Modular;
import com.camelot.pmt.platform.log.LogAspect;
import com.camelot.pmt.platform.mapper.RoleMapper;
import com.camelot.pmt.platform.model.Role;
import com.camelot.pmt.platform.service.RoleService;
import com.camelot.pmt.util.BuildTree;
import com.camelot.pmt.util.Tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    final String STATUS = "0";

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private LogAspect logAspect;

    /**
     * 查询角色集合
     *
     * @return ExecuteResult<List<Role>>
     */
    @Override
    public List<Tree<Role>> queryAllRole() {
        List<Tree<Role>> trees = new ArrayList<Tree<Role>>();
        List<Role> list = roleMapper.queryAllRole();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        for (Role role : list) {
            Tree<Role> tree = new Tree<Role>();
            tree.setId(role.getRoleId());
            tree.setParentId(role.getParentId());
            tree.setText(role.getRoleName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("state", role.getState());
            attributes.put("createTime", role.getCreateTime());
            attributes.put("modifyTime", role.getModifyTime());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        List<Tree<Role>> treeList = BuildTree.buildList(trees, "0");
        return treeList;
    }

    /**
     * 添加角色
     *
     * @param role
     * @return boolean
     */
    @Override
    public boolean addRole(Role role) throws Exception {
        role = setRoleModel(role);
        int state = roleMapper.addRole(role);
        if (state == 1) {
            logAspect.insertAddLog(role, Modular.ROLE, role.getCreateUserId());
            return true;
        }
        return false;
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @Override
    public boolean updateRoleById(Role role) {
        List<Role> list = roleMapper.queryRoleByroleId(role.getRoleId());
        long date = new Date().getTime();
        role.setModifyTime(new Date(date));
        int state = roleMapper.updateRoleById(role);
        Role r = new Role();
        r = list.get(0);
        if (state == 1) {
            logAspect.insertUpdateLog(role, r, Modular.ROLE, role.getModifyUserId());
            return true;
        }
        return false;
    }

    /**
     * 删除角色
     *
     * @param
     * @return
     */
    @Override
    public boolean deleteRoleById(Role role) {

        List<Role> list = roleMapper.queryRoleByroleId(role.getRoleId());
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        int state = roleMapper.deleteRoleById(role);
        if (state == 1) {
            // 添加日志
            logAspect.insertDeleteLog(Modular.ROLE, role.getModifyUserId(), list.get(0).getRoleName());
            return true;
        }
        return false;
    }

    /**
     * 验证角色名称是否可用
     *
     * @param role
     * @return
     */
    @Override
    public boolean getRoleNameVerification(Role role) {
        List<Role> list = roleMapper.getRoleNameVerification(role);
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        return false;
    }

    /**
     * 转换实体 添加后台数据
     *
     * @param r
     * @return
     * @throws Exception
     */
    public Role setRoleModel(Role r) throws Exception {
        Role role = new Role();
        long date = new Date().getTime();
        role.setRoleName(r.getRoleName());
        role.setCreateTime(new Date(date));
        role.setModifyTime(new Date(date));
        role.setCreateUserId(r.getCreateUserId());
        role.setModifyUserId(r.getModifyUserId());
        if (r.getParentId() != null) {
            role.setParentId(r.getParentId());
        } else {
            role.setParentId(STATUS);
        }
        role.setRoleId(UUID.randomUUID().toString().replaceAll("-", ""));
        if (r.getState() != null) {
            role.setState(r.getState());
        } else {
            role.setState(STATUS);
        }
        return role;
    }
}
