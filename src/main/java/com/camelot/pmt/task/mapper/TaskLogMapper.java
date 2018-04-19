package com.camelot.pmt.task.mapper;

import com.camelot.pmt.task.model.TaskLog;

import java.util.List;

public interface TaskLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskLog record);

    int insertSelective(TaskLog record);

    TaskLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskLog record);

    int updateByPrimaryKey(TaskLog record);

    /**
     * 添加任务
     */
    int insertTaskLog(TaskLog tasklog);

    /**
     * 任务流转记录查询
     */
    List<TaskLog> queryTaskLogList(Long id);
}