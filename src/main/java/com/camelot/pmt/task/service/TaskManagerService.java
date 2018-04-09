package com.camelot.pmt.task.service;

import com.camelot.pmt.task.model.Task;

import java.util.List;

/**
 * @author 郑李晖
 * @date 2018/4/9 16:25
 */
public interface TaskManagerService {
    /**
     * 查询所有任务
     * @return
     */
    List<Task> queryAllTask();
}
