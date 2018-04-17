package com.camelot.pmt.task.service;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.task.model.Task;

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
    JSONObject queryAllTask();

    /**
     * @param task
     *            模糊查询的条件
     * @description: 根据条件查询任务
     * @return
     */
    JSONObject queryTaskByTask(Task task);
}
