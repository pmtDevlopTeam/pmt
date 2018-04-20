package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;

import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: TaskPendingService
 * @Description: TODO(任务-我的待办service)
 * @author gxl
 * @date 2018年4月9日 下午5:30:05
 *
 */
public interface TaskPendingService {
    /**
     * 
     * @Title: save @Description: TODO(保存任务) @param @param task @param @return
     *         设定文件 @return ExecuteResult<String> 返回类型 @throws
     */
    ExecuteResult<String> save(Task task);

    /**
     * 
     * @Title: saveOrUpdate @Description: TODO(更新或保存Task对象方法) @param @param task
     *         设定文件 @return void 返回类型 @throws
     */
    ExecuteResult<String> saveOrUpdate(Task task);

    /**
     * 
     * @Title: delete @Description:
     *         TODO(根据taskId删除该任务，若删除该任务下的所有子任务请调用deleteTaskTreeById（）方法) @param @param
     *         taskId @param @return 设定文件 @return ExecuteResult<String> 返回类型 @throws
     */
    ExecuteResult<String> delete(Long id);

    /**
     * 
     * @Title: update @Description: TODO(修改任务) @param @param task @param @return
     *         设定文件 @return ExecuteResult<String> 返回类型 @throws
     */
    ExecuteResult<String> update(Task task);

    /**
     * 
     * @Title: queryAllTaskList @Description: TODO(查询所有的Task任务列表) @param @return
     *         设定文件 @return ExecuteResult<List<Task>> 返回类型 @throws
     */
    ExecuteResult<List<Task>> queryAllTaskList(Task task);

    /**
     * 
     * @Title: queryMyPendingTaskList @Description:
     *         TODO(查询我的待办Task任务列表) @param @return 设定文件 @return
     *         ExecuteResult<List<Task>> 返回类型 @throws
     */
    ExecuteResult<List<Task>> queryMyPendingTaskList(Task task);

    /**
     * 
     * @Title: queryMyTaskListNodeByParentId @Description:
     *         TODO(查询taskId下的一级子节点) @param @param taskId @param @return
     *         设定文件 @return ExecuteResult<List<Task>> 返回类型 @throws
     */
    ExecuteResult<List<Task>> queryMyTaskListNodeByParentId(Long id, String taskStatus, Long beassignUserId);

    /**
     * 
     * @Title: queryTaskListNodeByParentId @Description:
     *         TODO(查询taskId下的一级子节点) @param @param taskId @param @return
     *         设定文件 @return ExecuteResult<List<Task>> 返回类型 @throws
     */
    ExecuteResult<List<Task>> queryTaskListNodeByParentId(Long id, String taskStatus);

    /**
     * 如果taskId不为空，查询当前节点的父节点和祖宗节点 @Title: recursiveTree @Description:
     * TODO(通过递归获取Task任务树) @param @param taskId @param @return 设定文件 @return Task
     * 返回类型 @throws
     */
    ExecuteResult<List<Task>> queryTopAllTaskTreeByTaskId(Long id, List<Task> list);

    /**
     * 
     * @Title: delete @Description: TODO(根据Id删除该任务及以下的所有node节点，调用递归方法) @param @param
     *         taskId taskType @param @return 设定文件 @return ExecuteResult<String>
     *         返回类型 @throws
     */
    ExecuteResult<String> deletePendingTaskTreeById(Long id, String taskStatus);

    /**
     * 
     * @Title: queryTaskTreeByTaskId @Description: TODO(通过递归获取Task任务树) @param @param
     *         taskId @param @return 设定文件 @return ExecuteResult<Task> 返回类型 @throws
     */
    ExecuteResult<Task> queryTaskTreeByTaskId(Long id, String taskStatus, Long beassignUserId);

    /**
     * 
     * @Title: queryTopTaskNameList @Description: TODO(查询我的顶级待办任务) @param @return
     *         设定文件 @return ExecuteResult<List<Task>> 返回类型 @throws
     */
    ExecuteResult<List<Task>> queryTopTaskNameList(String taskStatus, Long beassignUserId);

    /**
     * 
     * @Title: updateTaskPendingToRuning @Description:
     *         TODO(我的待办任务状态转为正在进行) @param @param id @param @param
     *         taskType @param @return 设定文件 @return ExecuteResult<String>
     *         返回类型 @throws
     */
    ExecuteResult<String> updateTaskPendingToRunning(Long id, String taskStatus);

    /**
     * 
     * @Title: updateTaskPendingToDelay @Description:
     *         TODO(我的待办任务状态转为正在进行) @param @param id @param @param taskType
     *         状态 @param @param delayDescribe 描述 @param @param estimateStartTime
     *         预计开始时间 @param @return 设定文件 @return ExecuteResult<String> 返回类型 @throws
     */
    ExecuteResult<String> updateTaskPendingToDelay(Long id, String taskStatus, String delayDescribe,
            Date estimateStartTime);

    /**
     * 
     * @Title: updateTaskToAssign @Description: TODO(更新指派人和被指派人标识号) @param @param
     *         id @param @param assignUserId @param @param
     *         beassignUserId @param @return 设定文件 @return ExecuteResult<String>
     *         返回类型 @throws
     */
    ExecuteResult<String> updateTaskToAssign(Long id, Long assignUserId, Long beassignUserId);

}
