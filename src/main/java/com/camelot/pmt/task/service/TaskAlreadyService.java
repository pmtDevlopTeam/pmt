package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskAlreadyService {

    /**
     * TODO重做(我的任务状态转为正在进行)
     *
     * @param Long id
     * @return ExecuteResult<String>
     */

    ExecuteResult<String> updateTaskAlreadyToRunning(Long id);

    /**
     * 根据任务ID 提测
     *
     * @param Long id
     * @return ExecuteResult<String>
     */

    ExecuteResult<String> updateTaskToTest(Long id);

    /**
     * 查询我的已办任务列表
     *
     * @param Long id
     * @return ExecuteResult<PageInfo<Task>>
     */

    ExecuteResult<PageInfo<Task>> queryTaskAlready(int page , int rows, String id);

    /**
     * 查询未完成任务的个数
     *
     * @param Long projectId, String userId
     * @return int
     */

    int queryUnfinishedTask(Long projectId, String userId);

    /**
     * 根据需求ID 查询任务列表
     *
     * @param long demandId
     * @return ExecuteResult<List<Task>>
     */

    ExecuteResult<List<Task>> queryTaskByDemandId(Long demandId);


}
