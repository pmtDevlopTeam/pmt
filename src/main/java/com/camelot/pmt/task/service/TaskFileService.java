package com.camelot.pmt.task.service;

import com.camelot.pmt.platform.common.ExecuteResult;
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
    ExecuteResult<String> insert(TaskFile taskFile);
}
