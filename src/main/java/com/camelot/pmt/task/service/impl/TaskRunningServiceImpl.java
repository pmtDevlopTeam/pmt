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
import com.camelot.pmt.task.service.TaskLogService;
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

    @Autowired
    private TaskLogService taskLogService;

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
            taskLogService.insertTaskLog(id, Constant.TaskLogOperationButton.CLOSETASK.getValue(), "修改任务状态由：“正在进行”转换为“关闭”");
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
            taskLogService.insertTaskLog(ptask.getId(), Constant.TaskLogOperationButton.COMPLETETASK.getValue(), "修改任务状态由：“正在进行”转换为“完成”");
            flag = true;
            return flag;
        }
        return flag;
    }

}
