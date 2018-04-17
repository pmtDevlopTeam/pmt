package com.camelot.pmt.task.service;

import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskDetail;
import com.github.pagehelper.PageInfo;

/**
 * 延期任务接口
	* @ClassName: TaskOverdueService
	* @Description: TODO
	* @author jh
	* @date 2018年4月10日
	*
 */
public interface TaskOverdueService {
   
    
    /**
     * 根据taskid查询延期任务详情
    * @Title: queryOverdueTaskDetailByTaskId
    * @Description: TODO
    * @param @param taskId
    * @param @return
    * @return ExecuteResult<TaskDetail> 
    * @throws
     */
	ExecuteResult<TaskDetail> queryOverdueTaskDetailByTaskId(String taskId);

	/**
	 * 添加逾期描述,预计开始时间
	* @Title: insertOverduMeessage
	* @Description: TODO
	* @param @param task
	* @param @return
	* @return ExecuteResult<String> 
	* @throws
	 */
	ExecuteResult<String> insertOverduMessage(Task task);

	/**
	 * 根据任务id修改状态(延期-进行中)
	* @Title: updateTaskOverdueStatus
	* @Description: TODO
	* @param @param taskId
	* @param @return
	* @return ExecuteResult<String> 
	* @throws
	 */
	ExecuteResult<String> updateTaskOverdueStatus(String taskId);
	
	 /**
     * 查询延期任务列表
     * @Title: queryoverdueTask @Description: TODO @param @param
     * page @param @return @return ExecuteResult<DataGrid<UserModel>> @throws
     */
	ExecuteResult<PageInfo<Task>> queryOverdueTask(Integer page, Integer rows);
    
    
   

	
}
