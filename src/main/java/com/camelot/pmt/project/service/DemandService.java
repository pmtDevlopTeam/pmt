package com.camelot.pmt.project.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;
import com.camelot.pmt.project.model.DemandVO;

public interface DemandService {
    ExecuteResult<String> save(Demand demandWithBLOBs);

    ExecuteResult<DataGrid<Demand>> findAllByPage(Pager<?> pager, Demand demandWithBLOBs);

    DemandVO queryDemandById(Long id);

    ExecuteResult<String> deleteDemandById(Long id);

    ExecuteResult<String> updateByDemand(Demand demandWithBLOBs);

    ExecuteResult<DataGrid<DemandOperate>> findAllByPage(Pager<?> pager, DemandOperate demandOperate);

    /**
     * 查询影响需求的任务信息
     * 
     *@param  Long demandId
     *@return ExecuteResult<List<Map<String, Object>>>
     */
    ExecuteResult<List<Map<String, Object>>> queryDemandTaskQuoteById(Long demandId);

    /**
     * 查询影响需求的用例信息
     * 
     *@param  Long demandId
     *@return ExecuteResult<List<Map<String, Object>>>
     */
    ExecuteResult<List<Map<String, Object>>> queryDemandUseCaseQuoteById(Long demandId);

    /**
     * 查询影响需求的BUG信息
     * 
     *@param  Long demandId
     *@return ExecuteResult<List<Map<String, Object>>>
     */
    ExecuteResult<List<Map<String, Object>>> queryDemandBugQuoteById(Long demandId);
}
