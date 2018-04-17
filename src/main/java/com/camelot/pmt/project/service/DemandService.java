package com.camelot.pmt.project.service;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;

import java.util.Map;

public interface DemandService {
    ExecuteResult<String> save(Demand demand);

    ExecuteResult<DataGrid<Demand>> queryByPage(Pager<?> pager, Demand demand);

    ExecuteResult<Demand> queryById(Long id);

    ExecuteResult<String> deleteById(Long id);

    ExecuteResult<String> updateByDemand(Demand demand);

    ExecuteResult<DataGrid<DemandOperate>> findAllByPage(Pager<?> pager, DemandOperate demandOperate);
}
