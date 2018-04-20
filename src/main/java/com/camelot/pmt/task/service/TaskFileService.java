package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.TaskFile;

/**
 * @author zlh
 * @date 2018/4/17 10:20
 */
public interface TaskFileService {

    /**
     * 插入需求类型任务的附件元信息
     *
     * @author zlh
     * @param  taskFile 参数
     * @date 10:21 2018/4/17
     * @return boolean
     */
    boolean insert(TaskFile taskFile);

    /**
     * 根据附件来源和来源id查询附件元信息
     *
     * @author zlh
     * @param taskFile 查询需要的参数
     * @date 17:03 2018/4/17
     * @return TaskFile
     */
    TaskFile queryByTaskFile(TaskFile taskFile);
}
