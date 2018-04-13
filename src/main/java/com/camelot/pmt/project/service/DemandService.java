package com.camelot.pmt.project.service;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.model.DemandWithBLOBs;

public interface DemandService {
    ExecuteResult save(DemandWithBLOBs demandWithBLOBs);
}
