package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskPendingService;
import com.camelot.pmt.task.utils.Constant.TaskStatus;
import com.camelot.pmt.task.utils.DateUtils;
import com.camelot.pmt.task.utils.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
* @ClassName: TaskPendingServiceImpl
* @Description: TODO(任务-我的待办业务类)
* @author gxl
* @date 2018年4月9日 下午5:31:16
*
 */
@Service
public class TaskPendingServiceImpl implements TaskPendingService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskPendingServiceImpl.class);
	
	@Autowired
    private TaskMapper taskMapper;
	
	/**
	 * 
	* @Title: save 
	* @Description: TODO(保存任务) 
	* @param @param task
	* @param @return    设定文件 
	* @return ExecuteResult<String>    返回类型 
	* @throws
	 */
	@Override
	public ExecuteResult<String> save(Task task) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try{
			//对象检查是否为空
			if(task==null){
				result.addErrorMessage("传入的用户实体有误!");
				return result;
			}
			taskMapper.insertTaskNodeById(task);
			result.setResult("添加任务成功！");
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: update 
	* @Description: TODO(修改任务) 
	* @param @param task
	* @param @return    设定文件 
	* @return ExecuteResult<String>    返回类型 
	* @throws
	 */
	@Override
	public ExecuteResult<String> update(Task task) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		if(task==null||task.getId() == null){
			result.addErrorMessage("传入的任务实体或者任务Id有误!");
			return result;
		}
		taskMapper.updateTaskNodeById(task);
		result.setResult("修改任务成功！");
		return result;
	}
	
	/**
	 * 
	* @Title: saveOrUpdate 
	* @Description: TODO(更新或保存Task对象方法) 
	* @param @param task    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@Override
	public ExecuteResult<String> saveOrUpdate(Task task){
		ExecuteResult<String> result = new ExecuteResult<String>();
		try{
			//对象检查是否为空
			if(task==null){
				result.addErrorMessage("任务对象不能为空!");
				return result;
			}
			//判断id是否为null
			if(task.getId()==null){
				taskMapper.insertTaskNodeById(task);
				result.setResult("添加任务成功！");
			}else{
				Task taskSel= taskMapper.queryTaskNodeById(task.getId());
				//判断此对象库中是否存在
				if(taskSel!=null){
					taskMapper.updateTaskNodeById(task);
					result.setResult("修改任务成功！");
				}else{
					taskMapper.insertTaskNodeById(task);
					result.setResult("添加任务成功！");
				}
			}
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: delete 
	* @Description: TODO(根据taskId删除该任务，若删除该任务下的所有子任务请调用deleteTaskTreeById（）方法) 
	* @param @param taskId
	* @param @return    设定文件 
	* @return ExecuteResult<String>    返回类型 
	* @throws
	 */
	@Override
	public ExecuteResult<String> delete(Long id){
		ExecuteResult<String> result = new ExecuteResult<String>();
		try{
			if(id==null){
				result.addErrorMessage("删除任务时，taskId不能为空!");
				return result;
			}
			//查询所有的Task任务列表
			taskMapper.deleteTaskNodeById(id);
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: queryAllTaskList 
	* @Description: TODO(查询所有的Task任务列表) 
	* @param @return    设定文件 
	* @return ExecuteResult<List<Task>>    返回类型 
	* @throws
	 */
	@Override
	public ExecuteResult<List<Task>> queryAllTaskList(Task task){
		ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
		try{
			//查询所有的Task任务列表
			List<Task> allTaskList = taskMapper.queryAllTaskList(task);
			result.setResult(allTaskList);
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: queryMyTaskListNodeByParentId 
	* @Description: TODO(查询taskId下的一级子节点) 
	* @param @param taskId
	* @param @return    设定文件 
	* @return ExecuteResult<List<Task>>    返回类型 
	* @throws
	 */
	public ExecuteResult<List<Task>> queryMyTaskListNodeByParentId(Long id,String taskStatus,Long beassignUserId){
		ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
		try{
			//对象检查是否为空
			if(id==null){
				result.addErrorMessage("查询我的一级子任务时，taskId不能为空!");
				return result;
			}
			List<Task> taskList = taskMapper.queryMyTaskListNodeByParentId(id,taskStatus,beassignUserId);
			result.setResult(taskList);
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: queryTaskListNodeByParentId 
	* @Description: TODO(查询taskId下的一级子节点) 
	* @param @param taskId  taskStatus
	* @param @return    设定文件 
	* @return ExecuteResult<List<Task>>    返回类型 
	* @throws
	 */
	public ExecuteResult<List<Task>> queryTaskListNodeByParentId(Long id,String taskStatus){
		ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
		try{
			//对象检查是否为空
			if(id==null){
				result.addErrorMessage("查询一级子任务时，taskId不能为空!");
				return result;
			}
			List<Task> taskList = taskMapper.queryTaskListNodeByParentId(id,taskStatus);
			result.setResult(taskList);
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: deletePendingTaskTreeById 
	* @Description: TODO(根据Id删除待办任务及以下的所有node节点，调用递归方法，taskId不能为空) 
	* @param @param task
	* @param @return    设定文件 
	* @return ExecuteResult<String>    返回类型 
	* @throws
	 */
	@Override
	public ExecuteResult<String> deletePendingTaskTreeById(Long id,String taskStatus){

		ExecuteResult<String> result = new ExecuteResult<String>();
		
		try{
			//判断删除的任务Id是否存在
			if(id==null){
				result.addErrorMessage("删除任务时，taskId不能为空!");
				return result;
			}
			if("0".equals(taskStatus)){
				//根据taskId删除当前对象
				taskMapper.deleteTaskNodeById(id);
				//递归查询taskId下的所有子节点
				List<Task> childTaskNodes = taskMapper.queryTaskListNodeByParentId(id,taskStatus); 
				//遍历子节点
				if(childTaskNodes!=null && childTaskNodes.size()>0){
					for(Task child : childTaskNodes){
						//递归
						deletePendingTaskTreeById(child.getId(),taskStatus);
					}
				}
			}
			result.setResult("删除待办任务成功");
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	
	}
	
	/**
	 * 如果taskId不为空，查询当前节点下的所有子节点，如果taskId为空，查询整张task表
	* @Title: recursiveTree 
	* @Description: TODO(通过递归获取Task任务树) 
	* @param @param taskId
	* @param @return    设定文件 
	* @return Task    返回类型 
	* @throws
	 */
	public ExecuteResult<Task> queryTaskTreeByTaskId(Long id, String taskStatus, Long beassignUserId){
		ExecuteResult<Task> result = new ExecuteResult<Task>();
		try{
			Task taskNode = new Task();
			//判断删除的任务Id是否存在
			if(id!=null){
				//根据taskId获取节点对象
				taskNode = taskMapper.queryTaskNodeById(id);	
				//查询taskId下的所有子节点
				List<Task> childTaskNodes = taskMapper.queryMyTaskListNodeByParentId(id,taskStatus,beassignUserId); 
				//遍历子节点
				if(childTaskNodes!=null && childTaskNodes.size()>0){
					for(Task child : childTaskNodes){
						//递归
						//sql需要修改
						//<if test="taskStatus != null" > and t.task_type = #{taskStatus,jdbcType=BIGINT} </if>
						//<if test="beassignUserId != null" > and t.beassign_user_id = #{beassignUserId,jdbcType=BIGINT} </if>
						result = queryTaskTreeByTaskId(child.getId(),taskStatus,beassignUserId);
						taskNode.getChildren().add(result.getResult());
					}
				}
			}else{
				//如果taskId为空，返回整张表
				Task taskObj = new Task();
				taskObj.getBeassignUser().setUserId(beassignUserId.toString());
				taskObj.setStatus(taskStatus);
				List<Task> allTaskList = taskMapper.queryAllTaskList(taskObj);
				taskNode.setChildren(allTaskList);
			}
			result.setResult(taskNode);
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	};
	
	/**
	 * 如果taskId不为空，查询当前节点的父节点和祖宗节点
	* @Title: recursiveTree 
	* @Description: TODO(通过递归获取Task任务树) 
	* @param @param taskId
	* @param @return    设定文件 
	* @return Task    返回类型 
	* @throws
	 */
	public ExecuteResult<List<Task>> queryTopAllTaskTreeByTaskId(Long taskParentId){
		ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
		try{
			Task taskNode = new Task();
			List<Task> topAllTaskList = new ArrayList<Task>();
			//判断删除的任务Id是否存在
			if(taskParentId==null){
				result.addErrorMessage("任务id为空");
				return result;
			}
			//根据taskId获取节点对象
			taskNode = taskMapper.queryParentTaskNodeById(taskParentId);
			//遍历子节点
			if(taskNode!=null && taskNode.getTaskParentId()!=null){
				//添加task对象
				topAllTaskList.add(taskNode);
				//递归
				queryTopAllTaskTreeByTaskId(taskNode.getTaskParentId());
			}
			result.setResult(topAllTaskList);
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	};
	
	/**
	 * 
	* @Title: queryTopTaskNameList 
	* @Description: TODO(查询我的顶级待办任务) 
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws
	 */
	public ExecuteResult<List<Task>> queryTopTaskNameList(String taskStatus,Long beassignUserId){
		ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
		try{
			//根据当前用户Id和任务类型，查询我的顶级待办任务
			//select t.id,t.task_parent_id,t.task_name from task t where 1=1 
			//<if test="taskStatus != null" > and t.task_type = #{taskStatus,jdbcType=BIGINT} </if>
			//<if test="beassignUserId != null" > and t.beassign_user_id = #{beassignUserId,jdbcType=BIGINT} </if>
			List<Task> allTaskList = taskMapper.queryTopTaskNameList(taskStatus,beassignUserId);
			result.setResult(allTaskList);
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: updateTaskPendingToRuning 
	* @Description: TODO(我的待办任务转为正在进行) 
	* @param @param taskId taskStatus
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws
	 */
	@Override
	public ExecuteResult<String> updateTaskPendingToRunning(Long id,String taskStatus) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try{
			if(id==null){
				result.addErrorMessage("传入的任务Id有误!");
				return result;
			}
			//判断状态是否为待办，如果是待办更新为正在进行
			if("0".equals(taskStatus)){
				//根据id更新任务状态为正在进行
				//sql:update task set t.status = #{taskStatus,jdbcType=VARCHAR} where t.id = #{id,jdbcType=BIGINT}
				taskMapper.updateTaskPendingToRunning(id,taskStatus);
				//查询taskId下的所有子节点
				//select * from task t where t.task_parent_id = #{id}
				Task parentTaskNodes = taskMapper.queryParentTaskNodeById(id);
				//判断是否有父节点
				if(parentTaskNodes!=null){
					//递归
					return updateTaskPendingToRunning(parentTaskNodes.getId(),parentTaskNodes.getStatus());
				}
			}
			result.setResult("修改任务状态成功！");
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: updateTaskPendingToDelay
	* @Description: TODO(我的待办任务转为延期,会将该节点及节点下的所有子节点变为延期状态) 
	* @param @param taskId taskStatus
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws
	 */
	@Override
	public ExecuteResult<String> updateTaskPendingToDelay(Long id,String taskStatus,String delayDescribe,Date estimateStartTime) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try{
			if(id==null || StringUtils.isEmpty(delayDescribe) || estimateStartTime == null){
				result.addErrorMessage("传入的参数有误!");
				return result;
			}
			//判断状态是否为待办，如果是待办更新为正在进行
			if(TaskStatus.PENDINHG.getValue().equals(taskStatus)){
				//格式化日期格式为yyyy-mm-dd HH:mm:ss,根据id更新待办任务状态为延期
				//sql:update task set t.status = #{taskStatus,jdbcType=VARCHAR},t.delay_describe = #{delayDescribe,jdbcType=VARCHAR},t.estimate_start_time = #{estimateStartTime,jdbcType=TIMESTAMP} where t.id = #{id,jdbcType=BIGINT}
				if(!TaskStatus.CLOSE.getValue().equals(taskStatus)){
					taskMapper.updateTaskPendingToDelay(id,taskStatus,delayDescribe,DateUtils.format(estimateStartTime,DateUtils.DATE_TIME_PATTERN));
				}
				//查询taskId下的所有子节点
				//select * from task t where <if test="id != null" >t.task_parent_id = #{id}</if> <if test="taskStatus != null" > and t.task_type = #{taskStatus,jdbcType=BIGINT} </if>
				List<Task> childTaskNodes = taskMapper.queryTaskListNodeByParentId(id,null); 
				//遍历子节点
				if(childTaskNodes!=null && childTaskNodes.size()>0){
					for(Task child : childTaskNodes){
						//递归
						//sql需要修改
						//<if test="taskStatus != null" > and t.task_type = #{taskStatus,jdbcType=BIGINT} </if>
						//<if test="beassignUserId != null" > and t.beassign_user_id = #{beassignUserId,jdbcType=BIGINT} </if>
						//非关闭需要改为延期
						updateTaskPendingToDelay(child.getId(),taskStatus,delayDescribe,estimateStartTime);
					}
				}
			}
			result.setResult("修改任务状态成功！");
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: updateTaskToAssign 
	* @Description: TODO(更新指派人和被指派人标识号) 
	* @param @param assignUserId
	* @param @param beassignUserId
	* @param @return    设定文件 
	* @return ExecuteResult<String>    返回类型 
	* @throws
	 */
	@Override
	public ExecuteResult<String> updateTaskToAssign(Long id,Long assignUserId,Long beassignUserId){
		ExecuteResult<String> result = new ExecuteResult<String>();
		try{
			//检查是否为空
	        if (id==null || assignUserId==null || beassignUserId==null) {
	        	result.addErrorMessage("传入的taskd或assignUserId或beassignUserId有误!");
				return result;
	        }
			//sql:update task set t.assign_user_id = #{assignUserId,jdbcType=BIGINT},t.beassign_user_id= #{beassignUserId,jdbcType=BIGINT} where t.id = #{id,jdbcType=BIGINT}
			taskMapper.updateTaskToAssign(id,assignUserId,beassignUserId);
			result.setResult("修改任务状态成功！");
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
		}
		return result;
	}
}
