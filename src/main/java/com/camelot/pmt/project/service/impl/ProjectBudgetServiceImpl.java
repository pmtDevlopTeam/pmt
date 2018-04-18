package com.camelot.pmt.project.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.camelot.pmt.common.ExecuteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.project.mapper.ProjectBudgetMapper;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.service.ProjectBudgetService;

/**
 * 
 * @author lixk
 * @Description: 项目预算服务接口类
 * @date 2018年4月18日 上午9:44:05
 */
@Service
public class ProjectBudgetServiceImpl implements ProjectBudgetService {

    @Autowired
    ProjectBudgetMapper proBuggetMapper;

    /**
     * 添加项目预算信息
     *
     * @param  ProjectBudget projectBudget
     * @return ExecuteResult<String>
     */
    @Override
    public ExecuteResult<String> addBudget(ProjectBudget projectBudget) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        Date currentDate = new Date();
        try {
            if (null == projectBudget) {
                result.addErrorMessage("传入的项目预算实体有误!");
                return result;
            }
            // 对象不为空则添加新的项目实体
            projectBudget.setCreateTime(currentDate);
            projectBudget.setModifyTime(currentDate);
            int resu = proBuggetMapper.insertSelective(projectBudget);
            if (resu > 0) {
                result.setResult("添加项目预算成功!");
            } else {
                result.addErrorMessage("添加项目预算失败!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询单个项目预算信息
     *
     * @param  Long projectId
     * @return ExecuteResult<ProjectBudget>
     */
    @Override
    public ExecuteResult<ProjectBudget> queryBudgeByProjectId(Long projectId) {
        ExecuteResult<ProjectBudget> result = new ExecuteResult<ProjectBudget>();
        try {
            if (("".equals(projectId)) || (0 == projectId)) {
                result.addErrorMessage("查询失败！");
                return result;
            }
            ProjectBudget projectBudget = proBuggetMapper.selectProjectBudgetByProId(projectId);
            result.setResult(projectBudget);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改项目预算信息
     *
     * @param  ProjectBudget projectBudget
     * @return ExecuteResult<String>
     */
    @Override
    public ExecuteResult<String> updateBudget(ProjectBudget projectBudget) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        Date currentDate = new Date();
        try {
            if (projectBudget == null) {
                result.addErrorMessage("传入的用户实体有误!");
                return result;
            }
            // 对象不为空则添加新的项目实体
            projectBudget.setModifyTime(currentDate);
            int resu = proBuggetMapper.updateByPrimaryKeySelective(projectBudget);
            if (resu > 0) {
                result.setResult("修改项目预算信息成功!");
            } else {
                result.addErrorMessage("修改项目预算信息失败!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询项目统计预算信息
     *
     * @param  Long proId
     * @return ExecuteResult<Map<String, Object>>
     */
    @Override
    public ExecuteResult<Map<String, Object>> queryBudget(Long proId) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<>();
        Map<String, Object> map = new HashMap<>();
        try {
            // 获取项目总预计时间
            if (!"".equals(proId)) {
                ProjectBudget proBudget = proBuggetMapper.selectProjectBudgetByProId(proId);
                if (null == proBudget) {
                    result.setResultMessage("查询项目预计工时失败--不存在此项目预计工时！");
                    return result;
                }
                // 查询任务表中所有已完成任务的实际工时
                Long totalActualHours = proBuggetMapper.queryTotalActualHours(proId);
                /**
                 * 总消耗工时来源于已完成任务工时的累加和 等待与任务组对接===========================
                 */
                map.put("budgetaryHours", proBudget.getBudgetaryHours());// 项目预计时间
                map.put("otherbudget", proBudget.getOther());// 其他预算
                map.put("totalHours", totalActualHours);// 项目实际消耗时间
                result.setResult(map);
                return result;
            }
            result.addErrorMessage("统计查询失败！");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询影响需求的任务信息
     *
     * @param  Long demandId
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    @Override
    public ExecuteResult<List<Map<String, Object>>> queryDemandTaskByDeamdId(Long demandId) {
        ExecuteResult<List<Map<String, Object>>> result = new ExecuteResult<>();
        if ((null == demandId) || (0 == demandId)) {
            result.addErrorMessage("传入的需求id有误");
            return result;
        }
        List<Map<String, Object>> taskList = proBuggetMapper.findDemandTaskByDeamdId(demandId);
        result.setResult(taskList);
        return result;
    }

    /**
     * 查询影响变更需求影响的用例信息
     *
     * @param  Long demandId
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    @Override
    public ExecuteResult<List<Map<String, Object>>> queryDemandUseCaseByDeamdId(Long demandId) {
        ExecuteResult<List<Map<String, Object>>> result = new ExecuteResult<>();
        if ((null == demandId) || (0 == demandId)) {
            result.addErrorMessage("传入的需求id有误");
            return result;
        }
        List<Map<String, Object>> taskList = proBuggetMapper.findDemandUseCaseByDeamdId(demandId);
        result.setResult(taskList);
        return result;
    }

    /**
     * 查询影响需求变更的bug信息
     *
     * @param  Long demandId
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    @Override
    public ExecuteResult<List<Map<String, Object>>> findDemandBugByDeamdId(Long demandId) {
        ExecuteResult<List<Map<String, Object>>> result = new ExecuteResult<>();
        if ((null == demandId) || (0 == demandId)) {
            result.addErrorMessage("传入的需求id有误");
            return result;
        }
        List<Map<String, Object>> taskList = proBuggetMapper.findDemandBugByDeamdId(demandId);
        result.setResult(taskList);
        return result;
    }

}
