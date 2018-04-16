package com.camelot.pmt.project.service;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.model.DemandWithBLOBs;

public interface DemandService {
    ExecuteResult<String> save(DemandWithBLOBs demandWithBLOBs);

    ExecuteResult<DataGrid<DemandWithBLOBs>> findAllByPage(Pager pager, DemandWithBLOBs demandWithBLOBs);
}
