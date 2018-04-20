package com.camelot.pmt.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageGroupService;
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
    @Autowired
    private FileManageGroupService fileManageGroupService;

    /**
     * 添加项目预算信息
     *
     * @param ProjectBudget
     *            projectBudget
     * @return ExecuteResult<String>
     */
    @Override
    public boolean addBudget(ProjectBudget projectBudget) {
        Date currentDate = new Date();
        boolean flag = false;
        try {
            projectBudget.setCreateTime(currentDate);
            projectBudget.setModifyTime(currentDate);
            flag = proBuggetMapper.insertSelective(projectBudget) == 1 ? true : false;
        } catch (Exception e) {
            flag = false;
            throw new RuntimeException(e);
        }
        return flag;
    }

    /**
     * 查询单个项目预算信息
     *
     * @param Long
     *            projectId
     * @return ExecuteResult<ProjectBudget>
     */
    @Override
    public ProjectBudget queryBudgeByProjectId(Long projectId) {
        try {
            return proBuggetMapper.selectProjectBudgetByProId(projectId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改项目预算信息
     *
     * @param ProjectBudget
     *            projectBudget
     * @return ExecuteResult<String>
     */
    @Override
    public boolean updateBudget(ProjectBudget projectBudget) {
        Date currentDate = new Date();
        boolean flag = false;
        try {
            projectBudget.setCreateTime(currentDate);
            projectBudget.setModifyTime(currentDate);
            flag = proBuggetMapper.updateByPrimaryKeySelective(projectBudget) == 1 ? true : false;
        } catch (Exception e) {
            flag = false;
            throw new RuntimeException(e);
        }
        return flag;

    }

    /**
     * 查询项目统计预算信息
     *
     * @param Long
     *            proId
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
                    result.addErrorMessage("查询项目预计工时失败--不存在此项目预计工时！");
                    return result;
                }
                // 查询任务表中所有已完成任务的实际工时
                Long totalActualHours = proBuggetMapper.queryTotalActualHours(proId);
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
     * 查询统计项目结项 param Long projectId return Map<String,Object>
     */
    @Override
    public Map<String, Object> queryProjectEndById(Long projectId) {
        List<Map<String, Object>> file = new ArrayList<Map<String, Object>>();
        Map<String, Object> fileMap = new HashMap<>();
        Map<String,Object> fileGroupMap = null;
        FileManageGroup fileManageGroup = new FileManageGroup();
        fileManageGroup.setProjectId(projectId);
        List<FileManageGroup> treeList = fileManageGroupService.queryTree(fileManageGroup);// 得到项目所有字节点
        if (treeList.size() > 0) {
            for (FileManageGroup fileManageGroup2 : treeList) {
                fileGroupMap = new HashMap<>();
                Long gid = fileManageGroup2.getId();
                List<Map<String, Object>> fileList = proBuggetMapper.queryFile(gid);
                fileGroupMap.put("fileList", fileList);
                fileGroupMap.put("fileCount", fileList.size());
                fileGroupMap.put("fileGroup", fileManageGroup2);
                file.add(fileGroupMap);
            }
        }
        // 查询任务表中所有已完成任务的实际工时
        Long totalActualHours = proBuggetMapper.queryTotalActualHours(projectId);
        fileMap.put("totalActualHours", totalActualHours);
        fileMap.put("fileList", file);
        return fileMap;
    }

}
