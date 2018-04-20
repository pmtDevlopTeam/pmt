package com.camelot.pmt.task.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.mapper.TaskFileMapper;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskOverdueService;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: TaskServiceImpl
 * @Description: TODO
 * @author zhangao
 * @date 2018年4月9日
 *
 */
@Service
public class TaskOverdueServiceImpl implements TaskOverdueService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper logMapper;

    @Autowired
    private TaskFileMapper fileMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskOverdueServiceImpl.class);

    /**
     * 查询所有逾期任务+分页
     */
    @Override
    public ExecuteResult<PageInfo<Map<String, Object>>> queryOverdueTask(Task task, Integer page, Integer rows) {
        ExecuteResult<PageInfo<Map<String, Object>>> result = new ExecuteResult<PageInfo<Map<String, Object>>>();
        try {
            // 分页初始化
            PageHelper.startPage(page, rows);
            List<Map<String, Object>> list = taskMapper.queryOverdueTask(task);

            // 如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
                PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
                result.setResult(pageInfo);
                return result;
            }
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
            result.setResult(pageInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询延期任务详情
     */
    @Override
    public ExecuteResult<Map<String, Object>> queryOverdueTaskDetailByTaskId(String taskId) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<Map<String, Object>>();
        try {
            if (!taskId.equals("") && !taskId.equals("0")) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                // 查询详细
                Map<String, Object> queryOverdueTaskDetailByTaskId = taskMapper.queryOverdueTaskDetailByTaskId(taskId);
                // 根据taskId查询历史记录
                List<TaskLog> logList = logMapper.queryTaskLogList(Long.valueOf(taskId));
                // 查询结果放入返回对象中
                map.put("queryOverdueTaskDetailByTaskId", queryOverdueTaskDetailByTaskId);
                map.put("logList", logList);

                result.setResult(map);
                return result;
            }
            result.addErrorMessage("查询失败！");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 添加延期描述,预计开始时间
     */
    @Override
    public ExecuteResult<String> insertOverduMessage(Task task) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            if (task.getId() == 0 || task.getId() == null) {
                result.setResult("该任务不存在!");
                return result;
            }
            // 进行任务的更改(根据id去更改,修改延期描述,修改)
            int count = taskMapper.insertOverduMessage(task);
            if (count == 0) {
                result.setResult("更新任务失败!");
                return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        result.setResult("更新任务成功!");
        return result;
    }

    /**
     * 根据任务Id修改状态
     */
    @Override
    public ExecuteResult<String> updateTaskOverdueStatus(String taskId) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            if (StringUtils.isEmpty(taskId)) {
                result.setResult("该任务不存在!");
                return result;
            }
            // 进行任务的状态更改(根据id去更改任务的状态)
            int count = taskMapper.updateTaskOverdueStatus(taskId);
            TaskLog taskLog = new TaskLog();
            taskLog.setTaskId(Long.valueOf(taskId));
            // User user = (User)ShiroUtils.getSessionAttribute("user");
            taskLog.setUserId("cbec73cb98be4e9e8f3e2aab25a0a7bc");
            taskLog.setOperationButton("开始");
            taskLog.setOperationDescribe("由延期状态修改成开始状态");
            taskLog.setOperationTime(new Date());
            int insert = logMapper.insert(taskLog);
            if ((count + insert) == 0) {
                result.setResult("修改任务状态失败!");
                return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        result.setResult("修改任务状态成功!");
        return result;
    }

}
