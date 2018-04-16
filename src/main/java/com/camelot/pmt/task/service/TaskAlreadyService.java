package com.camelot.pmt.task.service;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;

import java.util.Map;

public interface TaskAlreadyService {

    /**
     *
     * @Title: queryoverdueTaskAlready @Description: TODO @param @param
     * page @param @return @return ExecuteResult<DataGrid<Map<String, Object>>> @throws
     * myp
     */
    ExecuteResult<DataGrid<Map<String, Object>>> queryoverdueTaskAlready(Pager page,Long id);


}
