package com.camelot.pmt.task.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskRunningService;
import com.camelot.pmt.task.utils.Constant;
import com.camelot.pmt.task.utils.DateUtils;
import com.camelot.pmt.task.utils.RRException;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author muyuanpei
 * @date 2018/4/10
 */

@Service
public class TaskRunningServiceImpl implements TaskRunningService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRunningServiceImpl.class);

    /**
     * @Title: updateRunningToClose
     * @Description: TODO(根据用户id获取正在进行的任务列表)
     * @param int
     *            page , int rows, String id
     * @return JSONObject 返回类型
     * @auth myp
     */
    @Override
    public List<Task> queryTaskRunning(Task task) {
        // 根据用户id查询全部的正在进行的任务
        List<Task> list = taskMapper.queryTaskRunning(task);
        return list;
    }

    /**
     * @Title: updateRunningToClose
     * @Description: TODO(我的任务转为关闭)
     * @param taskId
     * @return JSONObject 返回类型
     * @auth myp
     */
    @Override
    public Boolean updateRunningToClose(Long id) {
        Boolean flag = false;
        int i = taskMapper.updateRunningToClose(id);
        if(i>0){
            addTaskLog(id, "关闭");
            flag = true;
            return flag;
        }
        return flag;
    }

    /**
     * @Title: runningtoclose
     * @Description: TODO(我的正在进行的任务转为已完成)
     * @param taskId
     * @return JSONObject 返回类型
     * @auth myp
     */
    @Override
    public Boolean updateRunningToAlready(Task ptask) {
        Boolean flag = false;
        int i = taskMapper.updateRunningToAlready(ptask.getId());
        if(i>0){
            addTaskLog(ptask.getId(), "完成");
            flag = true;
            return flag;
        }
        return flag;
    }

    // 操作成功后添加操作记录
    private void addTaskLog(Long id, String peration) {
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
    }

}
