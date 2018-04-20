package com.camelot.pmt.task.service;

import javax.servlet.http.HttpServletResponse;

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
     * @param taskFile
     *            参数
     * @date 10:21 2018/4/17
     * @return boolean
     */
    boolean insert(TaskFile taskFile);

    /**
     * 根据附件来源和来源id查询附件元信息
     *
     * @author zlh
     * @param taskFile
     *            查询需要的参数
     * @date 17:03 2018/4/17
     * @return TaskFile
     */
    TaskFile queryByTaskFile(TaskFile taskFile);

    /**
     * 附件下载
     *
     * @author zlh
     * @param taskFile
     *            参数
     * @date 17:03 2018/4/17
     * @return boolean
     */
    boolean download(TaskFile taskFile, HttpServletResponse response);
}
