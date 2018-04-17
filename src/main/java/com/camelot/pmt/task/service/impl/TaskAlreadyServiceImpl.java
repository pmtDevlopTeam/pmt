package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskAlreadyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class TaskAlreadyServiceImpl implements TaskAlreadyService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskAlreadyServiceImpl.class);


    /**
     * @author: zsf
     * @param:
     * @description: 查询已完成任务
     * @date: 16:54 2018/4/9
     */
    public ExecuteResult<DataGrid<Map<String, Object>>> queryoverdueTaskAlready(Pager page, Long id) {
        ExecuteResult<DataGrid<Map<String, Object>>> result = new ExecuteResult<DataGrid<Map<String, Object>>>();
        try {
            List<Map<String, Object>> list = taskMapper.listTaskAlready(page, id);
            // 如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
                DataGrid<Map<String, Object>> dg = new DataGrid<Map<String, Object>>();
                result.setResult(dg);
                return result;
            }
            DataGrid<Map<String, Object>> dg = new DataGrid<Map<String, Object>>();
            dg.setRows(list);
            // 查询总条数
            Long total = taskMapper.queryAlreadyCount();
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zsf
     * @param:
     * @description: 查询未完成任务的个数
     * @date: 10:17 2018/4/17
     */
    @Override
    public int queryUnfinishedTask(long projectId, String userId) {

        return taskMapper.queryUnfinishedTask(projectId, userId);
    }

    /**
     * @author: zsf
     * @param:
     * @description: 根据需求ID 查任务列表
     * @date: 10:17 2018/4/17
     */
    @Override
    public ExecuteResult<List<Task>> queryTaskByDemandId(long demandId) {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            List<Task> taskList = taskMapper.queryTaskByDemandId(demandId);
            result.setResult(taskList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

}
