package com.camelot.pmt.task.service;

import java.util.List;

import com.camelot.pmt.platform.role.model.Role;
import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.task.model.Task;

/**
 * 任务接口
 * 
 * @ClassName: TaskService
 * @Description: TODO
 * @author zhangao
 * @date 2018年4月9日
 *
 */
public interface TaskService {
    /**
     * 
     * @Title: queryoverdueTask @Description: TODO @param @param
     * page @param @return @return ExecuteResult<DataGrid<UserModel>> @throws
     */
    ExecuteResult<DataGrid<Task>> queryoverdueTask(Pager page);
}
