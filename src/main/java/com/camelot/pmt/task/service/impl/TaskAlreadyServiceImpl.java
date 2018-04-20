package com.camelot.pmt.task.service.impl;


import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskAlreadyService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public ExecuteResult<String> updateTaskAlreadyToRunning(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            if (id == null) {
                result.setResult("该任务不存在!");
                return result;
            }
            // 遍历此任务下是否有引用--->查询所有任务父id为id的记录
            List<Task> taskList = taskMapper.queryByPId(id);
            List<Long> list = new ArrayList<Long>();
            if (taskList.size() > 0) {
                for (Task task : taskList) {
                    List<Task> tempList = taskMapper.queryByPId(task.getId());
                    if(tempList.size()>0){
                        System.out.println(tempList.size());
                        for (Task task2 : tempList) {
                            List<Task> tempList2 = taskMapper.queryByPId(task2.getId());
                            if(tempList2.size()>0){
                                for (Task task3 : tempList2) {
                                    list.add(task3.getId());
                                }
                                list.add(task2.getId());
                            }else{
                                //说明没有子任务
                                list.add(task2.getId());
                            }
                        }
                        list.add(task.getId());
                    }else{
                        //说明没有子任务
                        list.add(task.getId());
                    }
                }
                list.add(id);
            }else{
                //说明没有子任务
                list.add(id);
            }
            taskMapper.updateTaskAlreadyToRunning(list);
            result.setResult("任务关闭成功");
        }catch (Exception e){
            throw new RuntimeException(e);

        }
        return result;
    }

    /**
     * 根据任务ID 提测
     *
     * @param Long id
     * @return ExecuteResult<String>
     */

    @Override
    public ExecuteResult<String> updateTaskToTest(Long id) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(id==null){
                result.setResult("该任务不存在!");
                return result;
            }
            //根据ID去查需求ID
            Long demandId = taskMapper.queryTaskByTaskId(id);
            //根据需求ID查出当前需求下的测试人员ID
            String beassignUserId = taskMapper.queryTaskToTestByDemandId(demandId);
            //进行任务的状态更改(根据id去更改任务的状态)
            int count = taskMapper.updateTaskToTest(id,beassignUserId);
            if(count == 0){
                result.setResult("提测失败!");
                return result;
            }
        }
        catch(Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        result.setResult("提测成功!");
        return result;
    }

    /**
     * 查询我的已办任务列表
     *
     * @param Long id
     * @return ExecuteResult<PageInfo<Task>>
     */
    public ExecuteResult<PageInfo<Task>> queryTaskAlready(Integer page , Integer rows, String id) {
        ExecuteResult<PageInfo<Task>> result = new ExecuteResult<PageInfo<Task>>();
        //利用PageHelper进行分页
        PageHelper.startPage(page, rows);
        //根据用户id查询全部的已完成的任务
        List<Task> list = taskMapper.listTaskAlready(id);
        System.out.println(list.size());
        //分页之后的结果集
        PageInfo<Task> clist = new PageInfo<Task>(list);
        //返回结果集
        result.setResult(clist);
        return result;
    }

    /**
     * 查询未完成任务的个数
     *
     * @param Long projectId, String userId
     * @return int
     */
    @Override
    public int queryUnfinishedTask(Long projectId, String userId) {

        return taskMapper.queryUnfinishedTask(projectId, userId);
    }

    /**
     * 根据需求ID 查询任务列表
     *
     * @param long demandId
     * @return ExecuteResult<List<Task>>
     */

    @Override
    public ExecuteResult<List<Task>> queryTaskByDemandId(Long demandId) {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            List<Task> taskList = taskMapper.queryTaskByDemandId(demandId);
            result.setResult(taskList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }




}
