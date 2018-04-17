package com.camelot.pmt.project.service;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;

public interface DemandService {
    ExecuteResult<String> save(Demand demandWithBLOBs);

    ExecuteResult<DataGrid<Demand>> findAllByPage(Pager pager, Demand demandWithBLOBs);

    ExecuteResult<Demand> findById(Long id);

    ExecuteResult<String> deleteById(Long id);

    ExecuteResult<String> updateByDemand(Demand demandWithBLOBs);

    ExecuteResult<DataGrid<DemandOperate>> findAllByPage(Pager pager, DemandOperate demandOperate);
}
