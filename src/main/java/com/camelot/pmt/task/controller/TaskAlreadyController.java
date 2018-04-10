package com.camelot.pmt.task.controller;

import com.camelot.pmt.task.service.TaskAlreadyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/task/taskAlready")
@Api(value = "我的工作台-我的已办-接口", description = "我的工作台-我的已办-接口")
public class TaskAlreadyController {

    @Autowired
    private TaskAlreadyService taskAlreadyService;







}
