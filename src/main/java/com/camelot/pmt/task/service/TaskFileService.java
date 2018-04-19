package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.TaskFile;

/**
 * @author zlh
 * @date 2018/4/17 10:20
 */
public interface TaskFileService {

    /**
     * @author: zlh
     * @param:  taskFile 参数
     * @description: 插入需求类型任务的附件元信息
     * @date: 10:21 2018/4/17
     */
    boolean insert(TaskFile taskFile);

    /**
     * @author: zlh
     * @param: taskFile
     * @description: 根据附件来源和来源id查询附件元信息
     * @date: 17:03 2018/4/17
     */
    ExecuteResult<TaskFile> queryByTaskFile(TaskFile taskFile);
}
