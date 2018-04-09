package com.camelot.pmt.platform.role.service;

import com.camelot.pmt.platform.role.mapper.RoleMapper;
import com.camelot.pmt.platform.role.model.Role;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.UUIDTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    final String  STATUS = "0";

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询角色集合
     * @return ExecuteResult<List<Role>>
     */
    @Override
    public ExecuteResult<List<Role>> queryRoleArray() {
        ExecuteResult<List<Role>> result = new ExecuteResult<List<Role>>();
        try{
            List<Role> list =  roleMapper.queryRoleArray();
            if(CollectionUtils.isEmpty(list)){
                return result;
            }
            result.setResult(list);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 添加角色
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
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @Override
    public ExecuteResult<Role> editRole(Role role) {
        ExecuteResult<Role> result = new ExecuteResult<Role>();
        try {
            roleMapper.editRole(role);
            result.setResult(role);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 删除角色
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
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * 转换实体 添加后台数据
     * @param r
     * @return
     * @throws Exception
     */
    public Role  setRoleModel(Role r) throws Exception{
        Role role = new Role();
            long date = new Date().getTime();
            role.setRoleName(r.getRoleName());
            role.setCreateTime(new Date(date));
            role.setModifyTime(new Date(date));
            if(r.getParentId()!= null) {
                role.setParentId(r.getParentId());
            } else {
                role.setParentId(STATUS);
            }
            role.setRoleId(UUIDTool.getUUID());
            role.setState(STATUS);
        return role;
    }
}
