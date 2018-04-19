package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TaskAlreadyService {

    /**
     *
     * @Title: queryoverdueTaskAlready @Description: TODO 查询我的已办任务列表
     *         page @param @return @return ExecuteResult<DataGrid<Map<String,
     *         Object>>> @throws myp
     */
    ExecuteResult<PageInfo<Map<String, Object>>> queryTaskAlready(int page, int rows, String id);

    /**
     * @Title: queryUnfinishedTask 查询未完成任务的个数
     * @param projectId
     *            ，userId
     */
    int queryUnfinishedTask(long projectId, String userId);

    /**
     * @Title: queryTaskByDemandId 根据需求ID 查询任务列表
     * @param demandId
     */

    ExecuteResult<List<Task>> queryTaskByDemandId(long demandId);

    /**
     *
     * @Title: updateTaskPendingToDelay @Description:
     * TODO重做(我的任务状态转为正在进行) @param @param id @param @param taskStatus
     * 状态 @param @param delayDescribe 描述 @param @param estimateStartTime
     * 预计开始时间 @param @return 设定文件 @return ExecuteResult<String> 返回类型 @throws
     */
    ExecuteResult<String> updateTaskAlreadyToRunning(Long id);

}
