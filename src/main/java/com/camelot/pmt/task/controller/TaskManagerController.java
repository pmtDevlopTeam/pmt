package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.task.service.TaskManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlh
 * @date 2018/4/10 16:52
 */

@RestController
public class TaskManagerController {
    @Autowired
    private TaskManagerService taskManagerService;

    @PostMapping(value = "/queryAllTask")
    @ApiOperation(value = "查询所有任务列表", notes = "查询所有任务列表")
    public JSONObject queryAllTask() {
        return taskManagerService.queryAllTask();
    }
}
