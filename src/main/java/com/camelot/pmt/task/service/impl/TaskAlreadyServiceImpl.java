package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.service.TaskAlreadyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskAlreadyServiceImpl implements TaskAlreadyService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;
}
