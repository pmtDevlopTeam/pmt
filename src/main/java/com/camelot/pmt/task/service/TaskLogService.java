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
     * 查询日志 @Title: queryTaskLogList @Description: TODO @param @param
     * tasklog @param @return @return ExecuteResult<String> @throws
     */
    ExecuteResult<List<TaskLog>> queryTaskLogList(Long id);
}
