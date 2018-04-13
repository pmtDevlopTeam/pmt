package com.camelot.pmt.platform.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.camelot.pmt.platform.model.Menu;

import java.util.List;

/**
 * <p>
 * 菜单管理Mapper数据层接口
 * </p>
 *
 * @author gnerv
 * @since 2018-04-08
 */
public interface MenuMapper {

    /**
     * 根据一个菜单 增加一个菜单
     * 
     * @param Menu menu
     * @return int	1：成功；非1：失败
     */
    int createMenu(Menu menu);

    /**
     * 根据菜单id	删除一个菜单
     * 
     * @param String menuId
     * @return int	1：成功；非1：失败
     */
    int deleteMenuByMenuId(String menuId);

    /**
     * 根据菜单id 修改一个菜单
     * 
     * @param Menu menu
     * @return int	1：成功；非1：失败
     */
    int modifyMenuByMenuId(Menu menu);

    /**
     * 根据菜单id 查询一个菜单
     * 
     * @param String menuId
     * @return Menu
     */
    Menu queryMenuByMenuId(String menuId);

    /**
     * 查询全部菜单树
     * 
     * @param
     * @return List<Menu>
     */
    List<Menu> queryAllMenu();
    
    /**
     * 菜单分页测试
     * @param page
     * @return
     */
    List<Menu> selectMenuList(Pagination page);
}
