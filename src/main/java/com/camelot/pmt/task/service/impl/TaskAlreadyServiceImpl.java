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
import java.util.Map;

@Service
public class TaskAlreadyServiceImpl implements TaskAlreadyService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskAlreadyServiceImpl.class);


    /**
     * @author: zsf
     * @param:
     * @description: 查询已完成任务
     * @date: 16:54 2018/4/9
     */
    public ExecuteResult<PageInfo<Map<String, Object>>> queryTaskAlready(int page , int rows, String id) {
        ExecuteResult<PageInfo<Map<String, Object>>> result = new ExecuteResult<PageInfo<Map<String, Object>>>();
        //利用PageHelper进行分页
        PageHelper.startPage(page, rows);
        //根据用户id查询全部的已完成的任务
        List<Map<String, Object>> list = taskMapper.listTaskAlready(id);
        System.out.println(list.size());
        //分页之后的结果集
        PageInfo<Map<String, Object>> clist = new PageInfo<Map<String, Object>>(list);
        //返回结果集
        result.setResult(clist);
        return result;
    }

    /**
     * @author: zsf
     * @param:
     * @description: 查询未完成任务的个数
     * @date: 10:17 2018/4/17
     */
    @Override
    public int queryUnfinishedTask(long projectId, String userId) {

        return taskMapper.queryUnfinishedTask(projectId, userId);
    }

    /**
     * @author: zsf
     * @param:
     * @description: 根据需求ID 查任务列表
     * @date: 10:17 2018/4/17
     */
    @Override
    public ExecuteResult<List<Task>> queryTaskByDemandId(long demandId) {
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


    /**
     *
     * @Title: updateTaskAlreadyToRunning
     * @Description: TODO重做(我的已完成任务转为正在进行,会将该节点及节点下的所有子节点变为正在进行(不包括关闭的任务))
     * @param @param taskId taskStatus
     * @param @return    设定文件
     * @return JSONObject    返回类型
     * @throws
     */
    @Override
    public ExecuteResult<String> updateTaskAlreadyToRunning(Long id) {
        ExecuteResult<String> result=new ExecuteResult<>();
        try{
            //遍历此任务下是否有引用--->查询所有任务父id为id的记录
            List<Task> taskList = taskMapper.queryByPId(id);
            List<Long> list = new ArrayList<Long>();
            if(taskList.size()>0){
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

}
