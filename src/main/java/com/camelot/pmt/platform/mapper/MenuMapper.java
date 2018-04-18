package com.camelot.pmt.platform.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.camelot.pmt.platform.model.Menu;

import java.util.List;

/**
 *
 * @author gnerv
 * @Description 菜单管理Mapper数据层接口
 * @date 2018年4月18日
 */
public interface MenuMapper {

    /**
     * 根据一个菜单 增加一个菜单
     * @param menu
     * @return 1：成功；非1：失败
     */
    int addMenu(Menu menu);

    /**
     * 根据菜单id	删除一个菜单
     * @param menuId
     * @return 1：成功；非1：失败
     */
    int deleteMenuByMenuId(String menuId);

    /**
     * 根据菜单id 修改一个菜单
     * @param menu
     * @return 1：成功；非1：失败
     */
    int updateMenuByMenuId(Menu menu);

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
    List<Menu> queryAllMenuList();
    
    /**
     * 菜单分页测试
     * @param page
     * @return
     */
    List<Menu> selectMenuList(Pagination page);
}
