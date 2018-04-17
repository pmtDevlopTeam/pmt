package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.task.mapper.TaskFileMapper;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.service.TaskFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zlh
 * @date 2018/4/17 10:24
 */
@Service
public class TaskFileServiceImpl implements TaskFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskFileServiceImpl.class);

    @Autowired
    private TaskFileMapper taskFileMapper;

    /**
     * @author: zlh
     * @param:  taskFile 参数
     * @description: 插入需求类型任务的附件元信息
     * @date: 10:21 2018/4/17
     */
    @Override
    public ExecuteResult<String> insert(TaskFile taskFile) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (taskFile == null) {
                result.addErrorMessage("传入信息有误");
                return result;
            }

            int insert = taskFileMapper.insert(taskFile);
            result.setResult("插入成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zlh
     * @param: taskFile
     * @description: 根据附件来源和来源id查询附件元信息
     * @date: 17:03 2018/4/17
     */
    @Override
    public ExecuteResult<TaskFile> queryByTaskFile(TaskFile taskFile) {
        ExecuteResult<TaskFile> result = new ExecuteResult<TaskFile>();
        try {
            // check参数
            if (taskFile == null) {
                result.addErrorMessage("传入信息有误");
                return result;
            }

            result.setResult(taskFileMapper.queryByTaskFile(taskFile));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
