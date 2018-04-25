package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TaskAlreadyService {

    /**
     * TODO重做(我的任务状态转为正在进行)
     *
     * @param
     *            id
     * @return boolean
     */

    boolean updateTaskAlreadyToRunning(Long id);

    /**
     * 根据任务ID 提测
     *
     * @param
     *            id
     * @return   boolean
     */

    boolean updateTaskToTest(Long id);


    /**
     * 查询未完成任务的个数
     *
     * @param
     *            projectId, String userId
     * @return int
     */

    int queryUnfinishedTask(Long projectId, String userId);

    /**
     * 根据需求ID 查询任务列表
     *
     * @param
     *            demandId
     * @return List<Task>
     */

    List<Task> queryTaskByDemandId(Long demandId);


    /**
     * 查询我的任务
     *
     * @param  task
     *
     * @return Map<String,Object>
     */

    Map<String,Object> queryMyAllTask(Task task);

    /**
     * 查询我的已办任务列表
     *
     * @param Integer page, Integer rows, Task task
     *
     * @return PageInfo<Task>
     */

    PageInfo<Task> queryTaskAlready(Integer page, Integer rows, Task task);

}
