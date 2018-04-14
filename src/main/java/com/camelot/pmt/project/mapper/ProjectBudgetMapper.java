package com.camelot.pmt.project.mapper;

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
}