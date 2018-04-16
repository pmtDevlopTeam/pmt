package com.camelot.pmt.project.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.model.ProjectBudget;

public interface ProjectBudgetService {

    ExecuteResult<Map<String, Object>> findBudgetByProId(Long proId);

    ExecuteResult<String> saveProjectBudget(ProjectBudget projectBudget);

    ExecuteResult<String> modifyProjectBudget(ProjectBudget projectBudget);

    ExecuteResult<ProjectBudget> findProjectBudgeByProjectId(Long projectId);

    ExecuteResult<List<Map<String, Object>>> findDemandTaskByDeamdId(Long demandId);

    ExecuteResult<List<Map<String, Object>>> findDemandUseCaseByDeamdId(Long demandId);

    ExecuteResult<List<Map<String, Object>>> findDemandBugByDeamdId(Long demandId);

}
