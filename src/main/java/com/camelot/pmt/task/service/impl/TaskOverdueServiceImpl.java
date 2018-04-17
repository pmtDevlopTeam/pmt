package com.camelot.pmt.task.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskDetail;
import com.camelot.pmt.task.service.TaskOverdueService;
import com.github.pagehelper.PageInfo;

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
    public ExecuteResult<PageInfo<Task>> queryOverdueTask(Integer page, Integer rows) {
        ExecuteResult<PageInfo<Task>> result = new ExecuteResult<PageInfo<Task>>();
        try {
        	//分页初始化
        	PageHelper.startPage(page,rows);
            List<Task> list = taskMapper.queryOverdueTask();
            // 如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {                    
               PageInfo<Task> pageInfo = new PageInfo<>();
                result.setResult(pageInfo);
                return result;
            }
            PageInfo<Task> pageInfo = new PageInfo<>(list);
            result.setResult(pageInfo);
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
	 * 根据任务Id修改状态
	 */
	@Override
	public ExecuteResult<String> updateTaskOverdueStatus(String taskId) {
		 ExecuteResult<String> result = new ExecuteResult<String>();
	     try{
		     if(StringUtils.isEmpty(taskId)){
		     result.setResult("该任务不存在!");
		     return result;
		     }
		     //进行任务的状态更改(根据id去更改任务的状态)
		      int count = taskMapper.updateTaskOverdueStatus(taskId);
		      if(count == 0){
		      result.setResult("修改任务状态失败!");
		      return result;
		      }
	     }
	     catch(Exception e){
		     LOGGER.error(e.getMessage());
		     throw new RuntimeException(e);
	     }
	     result.setResult("修改任务状态成功!");
	     return result;
	}

}
