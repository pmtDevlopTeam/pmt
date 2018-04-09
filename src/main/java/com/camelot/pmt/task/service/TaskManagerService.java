package com.camelot.pmt.task.service;

import com.camelot.pmt.task.model.Task;

import java.util.List;

/**
 * @author zlh
 * @date 2018/4/9 16:25
 */
public interface TaskManagerService {
    /**
     * @author: zlh
     * @param:
     * @description: 查询所有任务
     * @date: 16:54 2018/4/9
     */
    List<Task> queryAllTask();
}
