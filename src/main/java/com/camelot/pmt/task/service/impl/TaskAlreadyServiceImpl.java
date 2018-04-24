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

            // 遍历此任务下是否有引用--->查询所有任务父id为id的记录
            List<Task> taskList = taskMapper.queryByPId(id);
            List<Long> list = new ArrayList<Long>();
            if (taskList.size() > 0) {
                for (Task task : taskList) {
                    List<Task> tempList = taskMapper.queryByPId(task.getId());
                    if (tempList.size() > 0) {
                        System.out.println(tempList.size());
                        for (Task task2 : tempList) {
                            List<Task> tempList2 = taskMapper.queryByPId(task2.getId());
                            if (tempList2.size() > 0) {
                                for (Task task3 : tempList2) {
                                    list.add(task3.getId());
                                }
                                list.add(task2.getId());
                            } else {
                                // 说明没有子任务
                                list.add(task2.getId());
                            }
                        }
                        list.add(task.getId());
                    } else {
                        // 说明没有子任务
                        list.add(task.getId());
                    }
                }
                list.add(id);
            } else {
                // 说明没有子任务
                list.add(id);
            }
           return taskMapper.updateTaskAlreadyToRunning(list)>=1?true:false;
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

        List PendingList = new ArrayList();
        List RunningList = new ArrayList();
        List AlreadyList = new ArrayList();
        List CloseList = new ArrayList();
        List<Task> taskList = taskMapper.queryMyAllTask(task);
        List<Task> taskAlreadyList  = taskMapper.queryMyAlreadyTask(task);
        AlreadyList.add(taskAlreadyList);
        for(int i = 0 ;i < taskList.size() ;i++ ){
            if(Constant.TaskStatus.PENDINHG.getValue().equals(taskList.get(i).getStatus())){
                PendingList.add(taskList.get(i));
            };
            if(Constant.TaskStatus.RUNING.getValue().equals(taskList.get(i).getStatus())){
                RunningList.add(taskList.get(i));
                System.out.println(RunningList);
            };
            if(Constant.TaskStatus.ALREADY.getValue().equals(taskList.get(i).getStatus())){
                AlreadyList.add(taskList.get(i));
            };
            if(Constant.TaskStatus.CLOSE.getValue().equals(taskList.get(i).getStatus())){
                CloseList.add(taskList.get(i));
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("PendingList",PendingList);
        map.put("RunningList",RunningList);
        map.put("AlreadyList",AlreadyList);
        map.put("CloseList",CloseList);
        return map;
    }

}
