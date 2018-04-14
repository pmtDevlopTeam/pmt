package com.camelot.pmt.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.mapper.ProjectBudgetMapper;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.model.ProjectUser;
import com.camelot.pmt.project.service.ProjectBudgetService;

/**
 * 
 * @ClassName: ProjectBudgetServiceImpl
 * @Description: 项目预算service层
 * @author lixiaokang
 * @date 2018年4月13日 上午10:41:18
 */
@Service
public class ProjectBudgetServiceImpl implements ProjectBudgetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectBudgetServiceImpl.class);

    @Autowired
    private ProjectBudgetMapper proBuggetMapper;

    /**
     * 添加项目预算
     */
    @Override
    public ExecuteResult<String> saveProjectBudget(ProjectBudget projectBudget) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        Date currentDate = new Date();
        try {
            if (null == projectBudget) {
                result.addErrorMessage("传入的项目预算实体有误!");
                return result;
            }
            // 对象不为空则添加新的项目实体
            Long id = 0L;
            projectBudget.setCreateTime(currentDate);
            projectBudget.setModifyTime(currentDate);
            projectBudget.setId(id);
            int resu = proBuggetMapper.insertSelective(projectBudget);
            if (resu > 0) {
                result.setResult("添加项目预算成功!");
            } else {
                result.addErrorMessage("添加项目预算失败!");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询单个项目预算：projectId
     */
    @Override
    public ExecuteResult<ProjectBudget> findProjectBudgeByProjectId(Long projectId) {
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
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改项目预算信息
     */
    @Override
    public ExecuteResult<String> modifyProjectBudget(ProjectBudget projectBudget) {
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
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询项目预算
     */
    @Override
    public ExecuteResult<Map<String, Object>> findBudgetByProId(Long proId) {
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
                map.put("budgetaryHours", proBudget.getBudgetaryHours());
                List<ProjectUser> proUserList = new ArrayList<>();// 调用项目成员dao,获取所有进入项目成员信息
                Long totalHours = 0L;// 总耗时时间
                /**
                 * 总消耗工时来源于已完成任务工时的累加和 等待与任务组对接===========================
                 */
                if ((null != proUserList) && (proUserList.size() > 0)) {
                    Date realJoinTime;// 实际进入时间
                    Date realOutTime;// 实际出项目时间
                    for (ProjectUser projectUser : proUserList) {
                        realJoinTime = projectUser.getRealJoinTime();
                        realOutTime = projectUser.getRealOutTime();
                        if (null != realOutTime) {
                            // ..........
                        } else {
                            // 以当前时间做为实际出项目时间
                        }
                    }
                }
                map.put("totalHours", totalHours);
                result.setResult(map);
                return result;
            }
            result.addErrorMessage("统计查询失败！");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

}
