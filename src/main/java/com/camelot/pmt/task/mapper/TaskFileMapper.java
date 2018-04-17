package com.camelot.pmt.task.mapper;

import com.camelot.pmt.task.model.TaskFile;

public interface TaskFileMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * @author: zlh
     * @param:  taskFile 参数
     * @description: 插入需求类型任务的附件元信息
     * @date: 10:21 2018/4/17
     */
    int insert(TaskFile taskFile);

    int insertSelective(TaskFile record);

    TaskFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskFile record);

    int updateByPrimaryKey(TaskFile record);
}