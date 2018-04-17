package com.camelot.pmt.platform.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.common.BaseState;
import com.camelot.pmt.platform.mapper.MenuMapper;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.MenuService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.util.BuildTree;
import com.camelot.pmt.util.Tree;
import com.camelot.pmt.util.UUIDUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限菜单服务接口类
 *
 * @author pmt
 * @since 2018-04-08
 */
@Service
public class MenuServiceImpl implements MenuService {



    @Autowired
    MenuMapper menuMapper;

    @Autowired
    ExecuteResult result;

    /**
     * 增加一个菜单
     *
     * @param Menu menu
     * @return ExecuteResult<Menu>
     */
    @Override
    public ExecuteResult<Menu> addMenu(Menu menu) {
        menu = createMenuModel(menu);
        int createMenu = menuMapper.addMenu(menu);
        if (createMenu == 1) {
            result.setResult(menu);
        } else {
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    /**
     * 删除一个菜单
     *
     * @param String menuId
     * @return ExecuteResult<Menu>
     */
    @Override
    public ExecuteResult<Menu> deleteMenuByMenuId(String menuId) {

        int deleteMenuByMenuId = menuMapper.deleteMenuByMenuId(menuId);
        if (deleteMenuByMenuId == 1) {
        } else {
            result.setSuccess(false);
            return result;
        }

        return result;
    }

    /**
     * 修改一个菜单
     *
     * @param Menu menu
     * @return ExecuteResult<Menu>
     */
    @Override
    public ExecuteResult<Menu> updateMenuByMenuId(Menu menu) {
        menu = updateMenuByMenuId(menu);
        int updateMenuByMenuId = menuMapper.updateMenuByMenuId(menu);
        if (updateMenuByMenuId == 1) {
        } else {
            result.setSuccess(false);
            return result;
        }

        return result;
    }

    /**
     * 查询一个菜单(post)
     *
     * @param String menuId
     * @return ExecuteResult<Menu>
     */
    @Override
    public ExecuteResult<Menu> queryMenuByMenuId(String menuId) {
        try {
            Menu queryMenuByMenuId = menuMapper.queryMenuByMenuId(menuId);
            if (queryMenuByMenuId != null) {
                result.setResult(queryMenuByMenuId);
            } else {
                result.setSuccess(false);
                return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询一个菜单(get)
     *
     * @param String id
     * @return ExecuteResult<Menu>
     */
    @Override
    public ExecuteResult<Menu> queryMenuById(String id) {
        Menu queryMenuByMenuId = menuMapper.queryMenuById(id);
        if (queryMenuByMenuId != null) {
            result.setResult(queryMenuByMenuId);
        } else {
            result.setSuccess(false);
            return result;
        }
        return result;
    }

    /**
     * 菜单分页查詢
     *
     * @param page
     * @param state
     * @return
     */
    @Override
    public List<Menu> selectMenuList(Menu menu，Integer pageSize,Integer currentPage) {
        PageHelper.startPage(currentPage,pageSize);
        List<Menu> menuList = menuMapper.selectMenuList(menu);
        return menuList;
    }
}
