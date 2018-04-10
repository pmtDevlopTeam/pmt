package com.camelot.pmt.task.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskOverdueService;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskOverdueServiceImpl.class);

    /**
     * 查询所有逾期任务+分页
     */
    public ExecuteResult<DataGrid<Task>> queryOverdueTask(Pager page) {
        ExecuteResult<DataGrid<Task>> result = new ExecuteResult<DataGrid<Task>>();
        try {
            List<Task> list = taskMapper.queryOverdueTask(page);
            // 如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
                DataGrid<Task> dg = new DataGrid<Task>();
                result.setResult(dg);
                return result;
            }
            DataGrid<Task> dg = new DataGrid<Task>();
            dg.setRows(list);
            // 查询总条数
            Long total = taskMapper.queryCount();
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

	

}
