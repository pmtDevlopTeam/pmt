package com.camelot.pmt.task.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zlh
 * @date 2018/4/9 16:27
 */
@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    private TaskMapper taskMapper;

    /**
     * @author: zlh
     * @param:
     * @description: 查询所有任务
     * @date: 16:54 2018/4/9
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public JSONObject queryAllTask() {
        return ApiResponse.success(taskMapper.queryAllTask());
    }

    /**
     * @param task 模糊查询的条件
     * @description: 根据条件查询任务
     * @return
     */
    @Override
    public JSONObject queryTaskByTask(Task task) {
        return null;
    }
}
