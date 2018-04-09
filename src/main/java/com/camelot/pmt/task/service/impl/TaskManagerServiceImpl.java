package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 郑李晖
 * @date 2018/4/9 16:27
 */
public class TaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> queryAllTask() {

        return null;
    }
}
