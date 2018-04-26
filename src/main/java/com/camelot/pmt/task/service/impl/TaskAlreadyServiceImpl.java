package com.camelot.pmt.task.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskAlreadyService;
import com.camelot.pmt.task.service.TaskLogService;
import com.camelot.pmt.task.utils.Constant;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskAlreadyServiceImpl implements TaskAlreadyService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogService taskLogService;

    /**
     * TODO重做(我的任务状态转为正在进行)
     *
     * @param id
     * @return boolean
     */

    @Override
    public boolean updateTaskAlreadyToRunning(Long id) {

        boolean flag = false;
        int i = taskMapper.updateTaskAlreadyToRunning(id,Constant.TaskStatus.RUNING.getValue());
        if (i == 1) {
            taskLogService.insertTaskLog(id, Constant.TaskLogOperationButton.REDOTASK.getValue(), "重做了任务");
            flag = true;
            return flag;
        }
        return flag;
    }

    /**
     * 根据任务ID 提测
     *
     * @param id
     * @return boolean
     */

    @Override
    public boolean updateTaskToTest(Long id) {
        Boolean flag = false;
        // 根据任务ID去查需求ID
        Long demandId = taskMapper.queryTaskByTaskId(id);
        // 根据需求ID查出当前需求下的测试人员ID
        String beassignUserId = taskMapper.queryTaskToTestByDemandId(demandId,Constant.TaskType.TESTTASK.getValue());
        // 进行任务的状态更改(根据id去更改任务的状态)
        int i = taskMapper.updateTaskToTest(id, beassignUserId,Constant.TaskType.TESTTASK.getValue(),Constant.TaskStatus.PENDINHG.getValue());
        Task task = new Task();
        task.setId(id);
        if (i == 1) {
            taskLogService.insertTaskLog(id, Constant.TaskLogOperationButton.MEASUREMENT.getValue(), "提测了任务");
            flag = true;
            return flag;
        }
        return flag;
    }

    /**
     * 查询未完成任务的个数
     *
     * @param projectId,
     *            String userId
     * @return int
     */
    @Override
    public int queryUnfinishedTask(Long projectId, String userId) {

        return taskMapper.queryUnfinishedTask(projectId, userId);
    }

    /**
     * 根据需求ID 查询任务列表
     *
     * @param demandId
     * @return List<Task>
     */

    @Override
    public List<Task> queryTaskByDemandId(Long demandId) {

        List<Task> taskList = taskMapper.queryTaskByDemandId(demandId);

        return taskList;
    }

    /**
     * 查询我的任务
     *
     * @param :
     *            task
     *
     * @return Map<String,Object>
     */

    @Override
    public Map<String, Object> queryMyAllTask(Task task) {

        List pendingList = new ArrayList();
        List runningList = new ArrayList();
        List alreadyList = new ArrayList();
        List closeList = new ArrayList();
        List<Task> taskList = taskMapper.queryMyAllTask(task);
        List<Task> taskAlreadyList = taskMapper.queryMyAlreadyTask(task);
        for (int j = 0; j < taskAlreadyList.size(); j++) {
            alreadyList.add(taskAlreadyList.get(j));
        }
        for (int i = 0; i < taskList.size(); i++) {
            if (Constant.TaskStatus.PENDINHG.getValue().equals(taskList.get(i).getStatus())) {
                pendingList.add(taskList.get(i));
            }
            ;
            if (Constant.TaskStatus.RUNING.getValue().equals(taskList.get(i).getStatus())) {
                runningList.add(taskList.get(i));
            }
            ;
            if (Constant.TaskStatus.ALREADY.getValue().equals(taskList.get(i).getStatus())) {
                alreadyList.add(taskList.get(i));
            }
            ;
            if (Constant.TaskStatus.CLOSE.getValue().equals(taskList.get(i).getStatus())) {
                closeList.add(taskList.get(i));
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("pendingList", pendingList);
        map.put("runningList", runningList);
        map.put("alreadyList", alreadyList);
        map.put("closeList", closeList);
        return map;
    }


    /**
     * 查询我的已办任务
     *
     * @param : task
     *
     * @return PageInfo<Task>
     */

    @Override
    public PageInfo<Task> queryTaskAlready(Integer page, Integer rows,Task task) {
               // 利用PageHelper进行分页
                PageHelper.startPage(page, rows);
               // 根据用户id查询全部的已完成的任务
                task.setStatus(Constant.TaskStatus.ALREADY.getValue());
                task.setTaskType(Constant.TaskType.TESTTASK.getValue());
                List<Task> list = taskMapper.listTaskAlready(task);
                // 分页之后的结果集
                PageInfo<Task> clist = new PageInfo<>(list);
               // 返回结果集
                return clist;
    }

}
