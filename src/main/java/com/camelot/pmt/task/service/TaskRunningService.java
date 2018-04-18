package com.camelot.pmt.task.service;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskLog;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author muyuanpei
 * @date 2018/4/10    15:19
 */
public interface TaskRunningService {

    /**
     *
     * @Title: queryoverdueTaskRunning @Description: TODO @param @param
     * page @param @return @return ExecuteResult<DataGrid<Map<String, Object>>> @throws
     */
    ExecuteResult<PageInfo<Map<String, Object>>> queryTaskRunning(int page , int rows, String id);


    /**
     *
     * @Title: updateStatusFinish @Description: TODO @param @param
     * page @param @return @return ExecuteResult<DataGrid<Long>> @throws
     */
    ExecuteResult<Task> queryTaskById(Long id);

    /**
     *
     * @Title: updateStatusFinish @Description: TODO @param @param
     * page @param @return @return ExecuteResult<DataGrid<Long>> @throws
     * 添加历史记录
     * myp
     */
    ExecuteResult<Long> saveHistoryLog(TaskLog taskLog);

    /**
     *
     * @Title: updateTaskPendingToDelay
     * @Description: TODO(我的任务状态转为关闭)
     * @param @param id
     * @return ExecuteResult<String>    返回类型
     * @throws
     */
    ExecuteResult<String> updateRunningToClose(Long id);


    /**
     *
     * @Title: runningtoalready
     * @Description: TODO(我的正在进行的任务状态转为已完成)
     * @param @param id
     * @return ExecuteResult<String>    返回类型
     * @throws
     */
    ExecuteResult<String> runningtoalready(Long id);
}
