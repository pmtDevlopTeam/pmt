package com.camelot.pmt.testmanage.bugmanage.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManageCount;

/**
 *
 * BugManage 表数据服务层接口
 *
 */
public interface BugManageService {

    /**
     * 添加 bug
     *
     * @param Menu
     *            menu
     * @return ExecuteResult<Menu>
     */
    boolean addBugManage(BugManage bugManage,User user);

    /**
     * 修改bug
     * 
     * @param
     * @return
     */
    boolean updateBugManage(BugManage bugManage,User user);

    /**
     * 撤销bug
     * 
     * @param
     * @return
     */
    boolean updateBugStatusRevoke(BugManage bugManage,User user);

    /**
     * 关闭bug
     * 
     * @param
     * @return
     */
    boolean updateBugStatusClose(BugManage bugManage,User user);

    /**
     * 确认bug
     * 
     * @param
     * @return
     */
    boolean updateBugStatusYes(BugManage bugManage,User user);

    /**
     * 指派bug
     * 
     * @param
     * @return
     */
    boolean updateBugAssign(BugManage bugManage,User user);

    /**
     * 解决bug
     * 
     * @param
     * @return
     */
    boolean updateBugSolve(BugManage bugManage,User user);

    /**
     * 根据id查询bug
     * 
     * @param id
     * @return
     */
    BugManage queryBugById(Long id);

    /**
     * bug列表
     * 
     * @param
     * @return
     */
    List<SelectBugManage> queryBugManageCondition(Map<String, Object> map);

    /**
     * bug 通过项目id ,指派给,状态 条件查询
     * 
     * @param map
     * @return
     */
    Boolean queryByPUS(Map<String, Object> map);

    /**
     * 查询最后一条bug记录
     * 
     * @param id
     * @return
     */
    SelectBugManage queryBugLimit();
    
    /**
     * 当日生产的bug
     * @return
     */
    Integer queryCreateTB();
    
    /**
     * 当日已解决bug
     */
    Integer querySolveTB();
    
    /**
     * bug统计
     */
    Integer queryBugTJ(Map<String,Object> map);
	    
    /**
     * 根据项目统计出任务bug
     * @param projectId
     * @return
     */
    List<SelectBugManageCount> queryCountBugTask(Long projectId);
    /**
     * 根据任务统计出负责人bug
     * @param taskId
     * @return
     */
    List<SelectBugManageCount> queryCountBugDesignated(Long taskId);

}