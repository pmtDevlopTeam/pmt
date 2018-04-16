package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author muyuanpei
 * @date 2018/4/10 15:20
 */

@Service
public class TaskRunningServiceImpl {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;

}
