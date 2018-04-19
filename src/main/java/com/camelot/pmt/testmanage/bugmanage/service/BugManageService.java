package com.camelot.pmt.testmanage.bugmanage.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;
import com.github.pagehelper.PageInfo;

/**
 *
 * BugManage 表数据服务层接口
 *
 */
public interface BugManageService {
	
	/**
     * 添加 bug
     *
     * @param Menu menu
     * @return ExecuteResult<Menu>
     */
    boolean addBugManage(BugManage bugManage);
	
   /**
    * 修改bug
    * @param
    * @return
    */
   boolean updateBugManage(BugManage bugManage);
   
   /**
    * 撤销bug
    * @param
    * @return
    */
   boolean updateBugStatusRevoke(BugManage bugManage);
  
   /**
    * 关闭bug
    * @param
    * @return
    */
   boolean updateBugStatusClose(BugManage bugManage);
   
   /**
    * 确认bug
    * @param
    * @return
    */
   boolean updateBugStatusYes(BugManage bugManage);
   
   /**
    * 指派bug
    * @param
    * @return
    */
   boolean updateBugAssign(BugManage bugManage);
  
   /**
    * 解决bug
    * @param
    * @return
    */
   boolean updateBugSolve(BugManage bugManage);
   
   /**
    * 根据id查询bug
    * @param id
    * @return
    */
   BugManage queryBugById(Long id);
	/**
     * bug列表
     * @param
     * @return
     */
    List<SelectBugManage> queryBugManageCondition(Map<String,Object> map);
   
    /**
     * bug 通过项目id ,指派给,状态 条件查询
     * @param map
     * @return
     */
    Boolean queryByPUS(Map<String,Object> map);
	
   
  
   
   
   
 

}