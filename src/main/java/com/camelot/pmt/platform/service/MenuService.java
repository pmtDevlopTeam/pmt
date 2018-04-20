package com.camelot.pmt.platform.service;

import java.util.List;

import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.util.Tree;

/**
 *
 * @author gnerv
 * @Description 权限菜单服务接口类
 * @date 2018年4月18日
 */
public interface MenuService {

    /**
     * 根据一个菜单对象 增加一个菜单
     * 
     * @param menu
     * @return true 成功 false 失败
     */
    boolean addMenu(Menu menu);

    /**
     * 根据菜单id 删除一个菜单
     * 
     * @param menuId
     * @return true 成功 false 失败
     */
    boolean deleteMenuByMenuId(String menuId);

    /**
     * 根据菜单id 修改一个菜单
     * 
     * @param menu
     * @return true 成功 false 失败
     */
    boolean updateMenuByMenuId(Menu menu);

    /**
     * 根据菜单id 查询一个菜单
     * 
     * @param menuId
     * @return Menu
     */
    Menu queryMenuByMenuId(String menuId);

    /**
     * 查询全部菜单树
     * 
     * @param
     * @return List
     */
    List<Tree<Menu>> queryAllMenuList();

    /**
     * 根据菜单id 查询指定菜单的子菜单树
     * 
     * @param menuId
     * @return ExecuteResult<List<Menu>>
     */
    List<Tree<Menu>> querySubMenuListByMenuId(String menuId);

}
