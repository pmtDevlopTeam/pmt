package com.camelot.pmt.task.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskLogService;
import com.camelot.pmt.task.utils.RRException;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 日志管理接口
 * 
 * @ClassName: TaskLogServiceImpl
 * @Description: TODO
 * @author zhangao
 * @date 2018年4月16日
 *
 */
@Service
public class TaskLogServiceImpl implements TaskLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskLogServiceImpl.class);

    @Autowired
    private TaskLogMapper taskLogMapper;

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 添加日志记录
     */
    @Override
    public ExecuteResult<String> insertTaskLog(TaskLog tasklog) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            if (tasklog == null) {
                result.addErrorMessage("传入的日志有误!");
                return result;
            }

            int count = taskLogMapper.insertTaskLog(tasklog);
            if (count > 0) {
                result.setResult("添加日志成功!");
            } else {
                result.setResult("添加日志失败!");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 添加日志记录
     */
    @Override
    public Boolean insertTaskLog(Long taskId, String button, String peration) {
        Boolean flag = false;
        try {
            if (taskId == null) {
                return flag;
            }

            TaskLog taskLog = new TaskLog();
            User user = (User) ShiroUtils.getSessionAttribute("user");
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            taskLog.setTaskId(taskId);
            taskLog.setUserId(user.getUserId());
            taskLog.setOperationButton(button);
            taskLog.setOperationTime(date);
            taskLog.setOperationDescribe(dateFormat.format(date) + "\t" + user.getUsername() + "\t" + peration);
            int count = taskLogMapper.insertTaskLog(taskLog);

            if (count > 0) {
                flag = true;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return flag;
    }

    /**
     * 任务流转记录查询
     */
    @Override
    public PageInfo<TaskLog> queryTaskLogList(Long id,Integer page, Integer rows) {
    	PageInfo<TaskLog> pageInfo;
    	try {
        	//分页初始化
        	PageHelper.startPage(page,rows);
        	List<TaskLog> tasklog = taskLogMapper.queryTaskLogList(id);
        	pageInfo = new PageInfo<TaskLog>(tasklog);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(), e);
        }
        return pageInfo;
    }

    @Override
    public void addTaskLogList(Long id, String peration) {
        ExecuteResult<List<TaskLog>> result = new ExecuteResult<List<TaskLog>>();
        try {
            Task taskAll = taskMapper.queryTaskAllById(id);
            TaskLog taskLog = new TaskLog();
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            taskLog.setTaskId(taskAll.getId());
            taskLog.setUserId(taskAll.getBeassignUser().getUserId());
            taskLog.setOperationButton(peration);
            taskLog.setOperationTime(date);
            taskLog.setOperationDescribe(
                    dateFormat.format(date) + "\t" + taskAll.getBeassignUser().getUsername() + "\t" + peration);
            taskLogMapper.insertTaskLog(taskLog);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
