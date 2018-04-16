package com.camelot.pmt.task.service;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskLog;

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
    ExecuteResult<DataGrid<Map<String, Object>>> queryoverdueTaskRunning(Pager page, Long id);


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
     * @param @param taskType 状态
     * @param @param delayDescribe 描述
     * @param @param estimateStartTime 预计开始时间
     * @param @return    设定文件
     * @return ExecuteResult<String>    返回类型
     * @throws
     */
    ExecuteResult<String> updateTaskToClose(Long id,String taskType);


    /**
     *
     * @Title: updateTaskPendingToDelay
     * @Description: TODO(我的任务状态转为关闭)
     * @param @param id
     * @param @param taskType 状态
     * @param @param delayDescribe 描述
     * @param @param estimateStartTime 预计开始时间
     * @param @return    设定文件
     * @return ExecuteResult<String>    返回类型
     * @throws
     */
    ExecuteResult<String> updateTaskAlreadyToRunning(Long id, String taskType, String delayDescribe, java.util.Date estimateStartTime);
}
