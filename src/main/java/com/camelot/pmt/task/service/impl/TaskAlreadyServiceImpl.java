package com.camelot.pmt.task.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskAlreadyService;
import com.camelot.pmt.task.utils.Constant;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskAlreadyServiceImpl implements TaskAlreadyService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskAlreadyServiceImpl.class);

    /**
     * TODO重做(我的任务状态转为正在进行)
     *
     * @param Long
     *            id
     * @return ExecuteResult<String>
     */

    @Override
    public boolean updateTaskAlreadyToRunning(Long id) {

           return taskMapper.updateTaskAlreadyToRunning(id)==1?true:false;
    }

    /**
     * 根据任务ID 提测
     *
     * @param Long
     *            id
     * @return ExecuteResult<String>
     */

    @Override
    public boolean updateTaskToTest(Long id) {

            // 根据任务ID去查需求ID
            Long demandId = taskMapper.queryTaskByTaskId(id);
            // 根据需求ID查出当前需求下的测试人员ID
            String beassignUserId = taskMapper.queryTaskToTestByDemandId(demandId);
            // 进行任务的状态更改(根据id去更改任务的状态)
            return taskMapper.updateTaskToTest(id, beassignUserId)==1?true:false;

    }


    /**
     * 查询未完成任务的个数
     *
     * @param Long
     *            projectId, String userId
     * @return int
     */
    @Override
    public int queryUnfinishedTask(Long projectId, String userId) {

        return taskMapper.queryUnfinishedTask(projectId, userId);
    }

    /**
     * 根据需求ID 查询任务列表
     *
     * @param long
     *            demandId
     * @return ExecuteResult<List<Task>>
     */

    @Override
    public List<Task> queryTaskByDemandId(Long demandId) {

        List<Task> taskList = taskMapper.queryTaskByDemandId(demandId);

        return taskList;
    }

    /**
     * 查询我的任务
     *
     * @param :String taskNum, String taskName ,Long projectId ,Long demandId,String id
     *
     * @return ExecuteResult<Map<String,Object>>
     */

    @Override
    public Map<String, Object> queryMyAllTask(Task task) {

        List pendingList = new ArrayList();
        List runningList = new ArrayList();
        List alreadyList = new ArrayList();
        List closeList = new ArrayList();
        List<Task> taskList = taskMapper.queryMyAllTask(task);
        List<Task> taskAlreadyList  = taskMapper.queryMyAlreadyTask(task);
        for(int j = 0 ;j < taskAlreadyList.size() ;j++ ){
            alreadyList.add(taskAlreadyList.get(j));
        }
        for(int i = 0 ;i < taskList.size() ;i++ ){
            if(Constant.TaskStatus.PENDINHG.getValue().equals(taskList.get(i).getStatus())){
                pendingList.add(taskList.get(i));
            };
            if(Constant.TaskStatus.RUNING.getValue().equals(taskList.get(i).getStatus())){
                runningList.add(taskList.get(i));
            };
            if(Constant.TaskStatus.ALREADY.getValue().equals(taskList.get(i).getStatus())){
                alreadyList.add(taskList.get(i));
            };
            if(Constant.TaskStatus.CLOSE.getValue().equals(taskList.get(i).getStatus())){
                closeList.add(taskList.get(i));
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("pendingList",pendingList);
        map.put("runningList",runningList);
        map.put("alreadyList",alreadyList);
        map.put("closeList",closeList);
        return map;
    }

}

