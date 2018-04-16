package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskDetail;
import com.camelot.pmt.task.service.TaskOverdueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 
 * @ClassName: TaskServiceImpl
 * @Description: TODO
 * @author zhangao
 * @date 2018年4月9日
 *
 */
@Service
public class TaskOverdueServiceImpl implements TaskOverdueService {

    @Autowired
    private TaskMapper taskMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskOverdueServiceImpl.class);

    /**
     * 查询所有逾期任务+分页   
     */
    @Override
    public ExecuteResult<DataGrid<Task>> queryOverdueTask(Pager page) {
        ExecuteResult<DataGrid<Task>> result = new ExecuteResult<DataGrid<Task>>();
        try {
            List<Task> list = taskMapper.queryOverdueTask(page);
            // 如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
                DataGrid<Task> dg = new DataGrid<Task>();
                result.setResult(dg);
                return result;
            }
            DataGrid<Task> dg = new DataGrid<Task>();
            dg.setRows(list);
            // 查询总条数
            Long total = taskMapper.queryCount();
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    /**
     * 查询延期任务详情
     */
	@Override
	public ExecuteResult<TaskDetail> queryOverdueTaskDetailByTaskId(String taskId) {
		 ExecuteResult<TaskDetail> result = new ExecuteResult<TaskDetail>();
	        try {
	            if (!taskId.equals("") && !taskId.equals("0")) {
	            	TaskDetail queryResult = taskMapper.queryOverdueTaskDetailByTaskId(taskId);
	                result.setResult(queryResult);
	                return result;
	            }
	            result.addErrorMessage("查询失败！");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}
	
	/**
	 * 添加延期描述,预计开始时间
	 */
	@Override
	public ExecuteResult<String> insertOverduMessage(Task task) {
		 ExecuteResult<String> result = new ExecuteResult<String>();
		     try{
			     if( task.getId() == 0 || task.getId() == null){
			     result.setResult("该任务不存在!");
			     return result;
			     }
			     //进行任务的更改(根据id去更改,修改延期描述,修改)
			     Integer count = taskMapper.insertOverduMessage(task);
			      if(count == 0){
			      result.setResult("更新任务失败!");
			      return result;
			      }
		     }
		     catch(Exception e){
			     LOGGER.error(e.getMessage());
			     throw new RuntimeException(e);
		     }
		     result.setResult("更新任务成功!");
		     return result;
	}
	/**
	 * 根据userId查询个人是否有延期任务(弹框显示)
	 */
	@Override
	public ExecuteResult<Integer> queryOverdueTaskUserId(String userId) {
		ExecuteResult<Integer> result = new ExecuteResult<Integer>();
        try {
            if (!userId.equals("") && !userId.equals("0")) {
            	//查看个人是否有延期任务
            	int count = taskMapper.queryOverdueTaskUserId(userId);
                result.setResult(count);
                return result;
            }
            result.addErrorMessage("查询失败！");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
	}

}
