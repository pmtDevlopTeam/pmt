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
     * @description: 查询所有任务列表
     * @date: 16:54 2018/4/9
     */
    List<Task> queryAllTask();

    /**
     * @param task
     *            模糊查询的条件
     * @description: 根据条件查询任务
     * @return
     */
    List<Task> queryTaskByTask(Task task);
}
