package com.camelot.pmt.task.service;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskDetail;

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
     * 查询延期任务列表
     * @Title: queryoverdueTask @Description: TODO @param @param
     * page @param @return @return ExecuteResult<DataGrid<UserModel>> @throws
     */
    ExecuteResult<DataGrid<Task>> queryOverdueTask(Pager page);
    
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
	 * 根据userId查询是否有延期任务(弹框提示)
	* @Title: queryOverdueTaskUserId
	* @Description: TODO
	* @param @param userId
	* @param @return
	* @return ExecuteResult<Integer> 
	* @throws
	 */
	ExecuteResult<Integer> queryOverdueTaskUserId(String userId);
    
    
   

	
}
