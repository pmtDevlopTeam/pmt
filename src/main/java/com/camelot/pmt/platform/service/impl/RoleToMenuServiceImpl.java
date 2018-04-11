package com.camelot.pmt.platform.service.impl;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.mapper.MenuMapper;
import com.camelot.pmt.platform.mapper.RoleToMenuMapper;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.RoleToMenu;
import com.camelot.pmt.platform.service.RoleToMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleToMenuServiceImpl implements RoleToMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleToMenuServiceImpl.class);

    @Autowired
    private RoleToMenuMapper roleToMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ExecuteResult<RoleToMenu> addRoleToMenu(RoleToMenu roleToMenu) {
        ExecuteResult<RoleToMenu> result = new ExecuteResult<RoleToMenu>();
        try {
            boolean isContains = true;
            List<String> roleId = Arrays.asList(roleToMenu.getMenuIds());
            for (int i = 0; i < roleId.size(); i++) {
                Menu menu = menuMapper.queryMenuByMenuId(roleId.get(i));
                    if (!menu.getParentId().equals("0")) {
                        isContains = Arrays.asList(roleToMenu.getMenuIds()).contains(menu.getParentId());
                        if (isContains == true) {
                            isContains = true;
                        } else {
                            isContains = false;
                            break;
                        }
                }
                if (isContains == false) {
                    break;
                }
            }
            if (isContains == false) {
                return result;
            }

            for (String ids : roleToMenu.getMenuIds()){
                roleToMenu.setMenuId(ids);
                roleToMenuMapper.addRoleToMenu(roleToMenu);
            }
            result.setResult(roleToMenu);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
