package com.camelot.pmt.project.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.project.model.ProjectBudget;

/**
 * 
 * @author lixk
 * @Description: 项目预算接口类
 * @date 2018年4月18日 上午9:38:32
 */
public interface ProjectBudgetService {

    /**
     * 查询项目预算
     * 
     * @param Long
     *            proId
     * @return ExecuteResult<Map<String, Object>>
     */
    ExecuteResult<Map<String, Object>> queryBudget(Long proId);

    /**
     * 添加项目预算
     * 
     * @param ProjectBudget
     *            projectBudget
     * @return ExecuteResult<String>
     */
    boolean addBudget(ProjectBudget projectBudget);

    /**
     * 修改项目预算
     * 
     * @param ProjectBudget
     *            projectBudget
     * @return ExecuteResult<String>
     */
    boolean updateBudget(ProjectBudget projectBudget);

    /**
     * 根据项目id查询项目预算
     * 
     * @param Long
     *            projectId
     * @return ExecuteResult<ProjectBudget>
     */
    ProjectBudget queryBudgeByProjectId(Long projectId);

}
