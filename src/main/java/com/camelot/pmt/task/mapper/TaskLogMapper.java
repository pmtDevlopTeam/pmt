package com.camelot.pmt.task.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.task.model.TaskLog;

public interface TaskLogMapper {
    int deleteByPrimaryKey(Long taskLogId);

    int insert(TaskLog record);

    int insertSelective(TaskLog record);

    TaskLog selectByPrimaryKey(Long taskLogId);

    int updateByPrimaryKeySelective(TaskLog record);

    int updateByPrimaryKey(TaskLog record);
}