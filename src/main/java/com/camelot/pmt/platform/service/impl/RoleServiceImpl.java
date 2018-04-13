package com.camelot.pmt.platform.service.impl;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.mapper.RoleMapper;
import com.camelot.pmt.platform.model.Role;
import com.camelot.pmt.platform.service.RoleService;
import com.camelot.pmt.platform.util.BuildTree;
import com.camelot.pmt.platform.util.Tree;
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

    /**
     * 查询角色集合
     *
     * @return ExecuteResult<List<Role>>
     */
    @Override
    public ExecuteResult<List<Tree<Role>>> queryAllRole() {
        ExecuteResult<List<Tree<Role>>> result = new ExecuteResult<List<Tree<Role>>>();
        List<Tree<Role>> trees = new ArrayList<Tree<Role>>();
        try {
            List<Role> list = roleMapper.queryAllRole();
            if (CollectionUtils.isEmpty(list)) {
                return result;
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
            result.setResult(treeList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 添加角色
     *
     * @param role
     * @return ExecuteResult<Role>
     */
    @Override
    public ExecuteResult<Role> createRole(Role role) {
        ExecuteResult result = new ExecuteResult();
        try {
            role = setRoleModel(role);
            roleMapper.createRole(role);
            result.setResult(role);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @Override
    public ExecuteResult<Role> modifyRoleById(Role role) {
        ExecuteResult<Role> result = new ExecuteResult<Role>();
        try {
            long date = new Date().getTime();
            role.setModifyTime(new Date(date));
            roleMapper.modifyRoleById(role);
            result.setResult(role);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @Override
    public ExecuteResult<Role> deleteRoleById(Role role) {
        ExecuteResult<Role> result = new ExecuteResult<Role>();
        try {
            int status = roleMapper.deleteRoleById(role);
            if(status == 1) {
                roleMapper.deleteRoleMenuById(role);
            }else {
                return result;
            }
            result.setResult(role);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 验证角色名称是否可用
     *
     * @param role
     * @return
     */
    @Override
    public ExecuteResult getRoleNameVerification(Role role) {
        ExecuteResult result = new ExecuteResult();
        try {
            List<Role> list = roleMapper.getRoleNameVerification(role);
            if (CollectionUtils.isEmpty(list)) {
                return result;
            }
            result.setResult(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
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
