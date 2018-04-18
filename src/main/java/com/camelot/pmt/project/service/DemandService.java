package com.camelot.pmt.project.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;
import com.camelot.pmt.project.model.DemandVO;

public interface DemandService {
    boolean save(Demand demand,User user);

    List<Demand> queryByPage(Demand demand,Integer pageSize,Integer currentPage);

    DemandVO queryDemandById(Long id);

    ExecuteResult<String> deleteDemandById(Long id);

    boolean updateByDemand(Demand demand,User user);

    List<DemandOperate> queryOperateByPage(DemandOperate demandOperate,Integer pageSize,Integer currentPage);

    /**
     * 查询影响需求的任务信息
     *
     *@param   demandId
     *@return ExecuteResult<List<Map<String, Object>>>
     */
    ExecuteResult<List<Map<String, Object>>> queryDemandTaskQuoteById(Long demandId);

    /**
     * 查询影响需求的用例信息
     *
     *@param   demandId
     *@return ExecuteResult<List<Map<String, Object>>>
     */
    ExecuteResult<List<Map<String, Object>>> queryDemandUseCaseQuoteById(Long demandId);

    /**
     * 查询影响需求的BUG信息
     *
     *@param   demandId
     *@return ExecuteResult<List<Map<String, Object>>>
     */
    ExecuteResult<List<Map<String, Object>>> queryDemandBugQuoteById(Long demandId);

    boolean updateByReview(Demand demand,User user);
}
