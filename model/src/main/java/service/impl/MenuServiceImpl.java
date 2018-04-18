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

    /**
     * 增加一个菜单
     *
     * @param Menu menu
     * @return ExecuteResult<Menu>
     */
    @Override
    public boolean addMenu(Menu menu) {
        return menuMapper.addMenu(menu)==1?true:false;
    }

    /**
     * 删除一个菜单
     *
     * @param String menuId
     * @return ExecuteResult<Menu>
     */
    @Override
    public boolean deleteMenuByMenuId(String menuId) {
        return menuMapper.deleteMenuByMenuId(menuId)==1?true:false;
    }

    /**
     * 修改一个菜单
     *
     * @param Menu menu
     * @return ExecuteResult<Menu>
     */
    @Override
    public boolean updateMenuByMenuId(Menu menu) {
        return menuMapper.updateMenuByMenuId(menu)==1?true:false;
    }

    /**
     * 查询一个菜单(post)
     *
     * @param String menuId
     * @return ExecuteResult<Menu>
     */
    @Override
    public Menu queryMenuByMenuId(String menuId) {
        return menuMapper.queryMenuByMenuId(menuId);
    }

    /**
     * 查询一个菜单(get)
     *
     * @param String id
     * @return ExecuteResult<Menu>
     */
    @Override
    public Menu queryMenuById(String id) {
        return menuMapper.queryMenuById(id);
    }

    /**
     * 菜单分页查詢
     *
     * @param page
     * @param state
     * @return
     */
    @Override
    public List<Menu> selectMenuList(Menu menu,Integer pageSize,Integer currentPage) {
        PageHelper.startPage(currentPage,pageSize);
        List<Menu> menuList = menuMapper.selectMenuList(menu);
        return menuList;
    }
}
