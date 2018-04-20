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

    /**
     * @author: zlh
     * @param: taskFile
     * @description: 根据附件来源和来源id查询附件元信息
     * @date: 17:03 2018/4/17
     */
    TaskFile queryByTaskFile(TaskFile taskFile);
    
    /**
     * 
    * @Title: update 
    * @Description: TODO(修改文件) 
    * @param @param id
    * @param @return    设定文件 
    * @return TaskFile    返回类型 
    * @throws
     */
    int update(TaskFile taskFile);
}