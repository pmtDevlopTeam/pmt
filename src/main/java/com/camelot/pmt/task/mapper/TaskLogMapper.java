package com.camelot.pmt.task.mapper;

import com.camelot.pmt.task.model.TaskLog;

public interface TaskLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskLog record);

    int insertSelective(TaskLog record);

    TaskLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskLog record);

    int updateByPrimaryKey(TaskLog record);

	int insertTaskLog(TaskLog tasklog);
}