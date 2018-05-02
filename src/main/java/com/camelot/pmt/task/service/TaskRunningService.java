package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskFile;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
    PageInfo<Task> queryTaskRunning(int page, int rows,Task task);

    /**
     * 修改任务关闭接口
     *
     * @author myp
     * @since 2018-04-13
     */
    Boolean updateRunningToClose(Long id);

    /**
     * 修改任务完成接口
     *
     * @author myp
     * @since 2018-04-19
     */
    Boolean updateRunningToAlready(Task ptask);


    /**
     * 根据项目id查询完成的任务总条数
     *
     * @author myp
     * @since 2018-05-02
     */
    Long queryTaskCountById(Long proid);
}
