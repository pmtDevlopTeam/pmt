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
 * @date 2018/4/10 15:19
 */
public interface TaskRunningService {

    /**
     * 查询正在进行的任务列表接口
     *
     * @author myp
     * @since 2018-04-08
     */
    ExecuteResult<PageInfo<Task>> queryTaskRunning(int page, int rows, String id);

    /**
     * 根据id查询任务
     *
     * @author myp
     * @since 2018-04-08
     */
    ExecuteResult<Task> queryTaskById(Long id);

    /**
     * 保存历史记录接口
     *
     * @author myp
     * @since 2018-04-08
     */
    ExecuteResult<Long> saveHistoryLog(TaskLog taskLog);

    /**
     * 修改任务关闭接口
     *
     * @author myp
     * @since 2018-04-08
     */
    ExecuteResult<String> updateRunningToClose(Long id);

    /**
     * 修改任务完成接口
     *
     * @author myp
     * @since 2018-04-08
     */
    ExecuteResult<String> updateRunningToAlready(Long id);
}
