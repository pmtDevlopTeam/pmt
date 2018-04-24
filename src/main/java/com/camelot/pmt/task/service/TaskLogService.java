package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.TaskLog;

import java.util.List;

/**
 * 
 * @ClassName: TaskLogService
 * @Description: TODO 日志管理接口
 * @author zhangao
 * @date 2018年4月16日
 *
 */
public interface TaskLogService {
    /**
     * 添加日志 @Title: insertTaskLog @Description: TODO @param @param
     * tasklog @param @return @return ExecuteResult<String> @throws
     */
    ExecuteResult<String> insertTaskLog(TaskLog tasklog);

    /**
     * 添加日志 @Title: insertTaskLog @Description: TODO @param @param
     * tasklog @param @return @return ExecuteResult<String> @throws
     */
    ExecuteResult<String> insertTaskLog(Long taskId, String button, String peration);

    /**
     * 查询日志 @Title: queryTaskLogList @Description: TODO @param @param
     * tasklog @param @return @return ExecuteResult<String> @throws
     */
    List<TaskLog> queryTaskLogList(Long id);

    /**
     * 添加操作日志日志，需要传任务的id，操作名称 @Title: queryTaskLogList @Description:
     * TODO @param @param tasklog @return ExecuteResult<String> @throws
     */
    void addTaskLogList(Long id, String peration);
}
