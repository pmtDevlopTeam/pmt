package com.camelot.pmt.testmanage.bugmanage.service;

import java.util.Map;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.github.pagehelper.PageInfo;

/**
 *
 * BugManage 表数据服务层接口
 *
 */
public interface BugManageService {
	
	/**
	    * bug列表
	    * @param userModel
	    * @return
	    */
   ExecuteResult<PageInfo> selectCondition(Map<String,Object> map);
	
	 /**
	  * 添加 bug
	  * @param bugManage
	  * @return
	  */
   ExecuteResult<String> save(BugManage bugManage);
   
   /**
    * 根据id查询bug
    * @param id
    * @return
    */
   ExecuteResult<BugManage> getBugById(Long id);
   
   /**
    * 修改bug
    * @param userModel
    * @return
    */
   ExecuteResult<String> edit(BugManage bugManage);
   
   /**
    * 撤销bug
    * @param userModel
    * @return
    */
   ExecuteResult<String> updateBugStatusRevoke(BugManage bugManage);
  
   /**
    * 关闭bug
    * @param userModel
    * @return
    */
   ExecuteResult<String> updateBugStatusClose(BugManage bugManage);
   
   /**
    * 确认bug
    * @param userModel
    * @return
    */
   ExecuteResult<String> updateBugStatusYes(BugManage bugManage);
   
   /**
    * 指派bug
    * @param userModel
    * @return
    */
   ExecuteResult<String> updateBugAssign(BugManage bugManage);
  
   /**
    * 解决bug
    * @param userModel
    * @return
    */
   ExecuteResult<String> updateBugSolve(BugManage bugManage);

}