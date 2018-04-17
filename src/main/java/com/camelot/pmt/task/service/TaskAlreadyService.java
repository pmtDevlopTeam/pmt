package com.camelot.pmt.task.service;

import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.task.model.Task;

import java.util.List;
import java.util.Map;

public interface TaskAlreadyService {

    /**
     *
     * @Title: queryoverdueTaskAlready @Description: TODO @param @param
     * page @param @return @return ExecuteResult<DataGrid<Map<String, Object>>> @throws
     * myp
     */
    ExecuteResult<DataGrid<Map<String, Object>>> queryoverdueTaskAlready(Pager page, Long id);

    /**
     * @Title: queryUnfinishedTask 查询未完成任务的个数
     *  @param projectId ，userId
     */
    int queryUnfinishedTask (long projectId , String userId);

    /**
     * @Title: queryTaskByDemandId 查询未完成任务的个数
     *  @param demandId
     */

    ExecuteResult<List<Task>> queryTaskByDemandId(long demandId);


}
