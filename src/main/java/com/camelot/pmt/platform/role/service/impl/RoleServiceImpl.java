package com.camelot.pmt.platform.role.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.camelot.pmt.platform.common.util.BuildTree;
import com.camelot.pmt.platform.common.util.Tree;
import com.camelot.pmt.platform.role.mapper.RoleMapper;
import com.camelot.pmt.platform.role.model.Role;
import com.camelot.pmt.platform.role.service.RoleService;
import com.camelot.pmt.platform.utils.ExecuteResult;

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
    public ExecuteResult<List<Tree<Role>>> queryRoleArray() {
        ExecuteResult<List<Tree<Role>>> result = new ExecuteResult<List<Tree<Role>>>();
        List<Tree<Role>> trees = new ArrayList<Tree<Role>>();
        try {
            List<Role> list = roleMapper.queryRoleArray();
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
    public ExecuteResult<Role> addRole(Role role) {
        ExecuteResult result = new ExecuteResult();
        try {
            role = setRoleModel(role);
            roleMapper.addRole(role);
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
    public ExecuteResult<Role> editRole(Role role) {
        ExecuteResult<Role> result = new ExecuteResult<Role>();
        try {
            long date = new Date().getTime();
            role.setModifyTime(new Date(date));
            roleMapper.editRole(role);
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
    public ExecuteResult<Role> deleteRole(Role role) {
        ExecuteResult<Role> result = new ExecuteResult<Role>();
        try {
            roleMapper.deleteRole(role);
            roleMapper.deleteRoleMenu(role);
            result.setResult(role);
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
        role.setState(STATUS);
        return role;
    }
}
