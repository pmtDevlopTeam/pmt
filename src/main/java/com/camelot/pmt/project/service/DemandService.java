package com.camelot.pmt.project.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;
import com.camelot.pmt.project.model.DemandVO;
import com.github.pagehelper.PageInfo;

public interface DemandService {
    boolean addDemand(Demand demand, User user);

    List<Demand> queryByPage(Demand demand, Integer pageSize, Integer currentPage);

    DemandVO queryDemandById(Long id);

    ExecuteResult<String> deleteDemandById(Long id);

    boolean updateByDemand(Demand demand, User user);

    List<DemandOperate> queryOperateByPage(DemandOperate demandOperate, Integer pageSize, Integer currentPage);

    /**
     * 查询影响需求的任务信息
     *
     * @param Long
     *            demandId ,Integer pageSize,Integer currentPage
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    PageInfo<Map<String, Object>> queryDemandTaskQuoteById(Long demandId, Integer pageSize, Integer currentPage);

    /**
     * 查询影响需求的用例信息
     *
     * @param Long
     *            demandId ,Integer pageSize,Integer currentPage
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    PageInfo<Map<String, Object>> queryDemandUseCaseQuoteById(Long demandId, Integer pageSize, Integer currentPage);

    /**
     * 查询影响需求的bug信息
     *
     * @param Long
     *            demandId ,Integer pageSize,Integer currentPage
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    PageInfo<Map<String, Object>> queryDemandBugQuoteById(Long demandId, Integer pageSize, Integer currentPage);

    boolean updateByReview(Demand demand, User user);

    /**
     * 新增级联需求
     * 
     * @param
     * @return
     */
    boolean addDemandList(DemandVO demandVO, User user);

    /**
     * 新增需求并返回主键id
     * 
     * @param
     * @return
     */
    Long addDemandAndReturnId(Demand demand, User user);
}
