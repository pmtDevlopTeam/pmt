package com.camelot.pmt.task.mapper;

import com.camelot.pmt.task.model.Task;

import java.util.List;

public interface TaskMapper {
    int deleteByPrimaryKey(Long taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    /**
     * @author: zlh
     * @param:
     * @description: 查询所有任务
     * @date: 16:56 2018/4/9
     */
    List<Task> queryAllTask();
}