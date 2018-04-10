package com.camelot.pmt.task.mapper;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.task.model.Task;

import java.util.List;

public interface TaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    /**
     * @author: zlh
     * @param:
     * @description: 查询所有任务列表
     * @date: 16:54 2018/4/9
     */
    List<Task> queryAllTask();

    /**
     * @param task
     *            模糊查询的条件
     * @description: 根据条件查询任务
     * @return
     */
    List<Task> queryTaskByTask(Task task);
}