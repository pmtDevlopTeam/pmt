package com.camelot.pmt.project.mapper;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.project.model.ProjectBudget;

public interface ProjectBudgetMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(ProjectBudget record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(ProjectBudget record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    ProjectBudget selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(ProjectBudget record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(ProjectBudget record);

    /**
     * 根据项目id查询项目预算
     * 
     * @param proId
     * @return ProjectBudget
     */
    ProjectBudget selectProjectBudgetByProId(Long proId);

    /**
     * 根据需求id查询影响需求变更的任务信息
     * 
     * @param demandId
     * @return
     */
    List<Map<String, Object>> findDemandTaskByDeamdId(Long demandId);

    /**
     * 根据需求id查询影响需求变更的用例信息
     * 
     * @param demandId
     * @return
     */
    List<Map<String, Object>> findDemandUseCaseByDeamdId(Long demandId);

    /**
     * 根据需求id查询影响变更需求的bug信息
     * 
     * @param demandId
     * @return
     */
    List<Map<String, Object>> findDemandBugByDeamdId(Long demandId);

    /**
     * 查询项目实际耗费总工时->获取所有已完成任务的实际总工时
     * 
     * @param proId
     * @return
     */
    Long queryTotalActualHours(Long proId);
}