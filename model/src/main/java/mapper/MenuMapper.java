package com.camelot.pmt.platform.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.camelot.pmt.platform.model.Menu;

import java.util.List;

/**
 * 菜单管理Mapper数据层接口
 *
 * @author pmt
 * @since 2018-04-08
 */
public interface MenuMapper {

    /**
     * 根据一个菜单 增加一个菜单
     * 
     * @param Menu menu
     * @return int	1：成功；非1：失败
     */
    int addMenu(Menu menu);

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
    int updateMenuByMenuId(Menu menu);

    /**
     * 根据菜单id 查询一个菜单(post)
     *
     * @param String menuId
     * @return Menu
     */
    Menu queryMenuByMenuId(String menuId);

    /**
     * 根据菜单id 查询一个菜单(get)
     *
     * @param String id
     * @return Menu
     */
    Menu queryMenuById(String id);

    /**
     * 菜单分页查詢
     * @param menu
     * @return
     */
    List<Menu> selectMenuList(Menu menu);
}
