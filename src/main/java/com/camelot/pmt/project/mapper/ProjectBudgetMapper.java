package com.camelot.pmt.project.mapper;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.project.model.ProjectBudget;

/**
 * 
 * @author lixk
 * @Description: 项目预算Mapper数据层接口
 * @date 2018年4月18日 上午9:50:52
 */
public interface ProjectBudgetMapper {
    /**
     * 根据预算id 删除一个预算
     * 
     * @param Long
     *            id
     * @return int 1：成功；非1：失败
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据一个预算 增加一个预算
     * 
     * @param ProjectBudget
     *            record
     * @return int 1：成功；非1：失败
     */
    int insert(ProjectBudget record);

    /**
     * 根据一个预算 增加一个预算
     * 
     * @param ProjectBudget
     *            record
     * @return int 1：成功；非1：失败
     */
    int insertSelective(ProjectBudget record);

    /**
     * 根据预算id 查询一个预算
     *
     * @param Long
     *            id
     * @return ProjectBudget
     */
    ProjectBudget selectByPrimaryKey(Long id);

    /**
     * 根据预算id 修改一个预算
     * 
     * @param ProjectBudget
     *            record
     * @return int 1：成功；非1：失败
     */
    int updateByPrimaryKeySelective(ProjectBudget record);

    /**
     * 根据预算id 修改一个预算
     * 
     * @param ProjectBudget
     *            record
     * @return int 1：成功；非1：失败
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
     * 查询项目实际耗费总工时->获取所有已完成任务的实际总工时
     * 
     * @param proId
     * @return
     */
    Long queryTotalActualHours(Long proId);

    /**
     * 创建项目时，插入预算表
     * 
     * @param projectBudget
     * @return
     */
    int addProjectBudget(ProjectBudget projectBudget);
}