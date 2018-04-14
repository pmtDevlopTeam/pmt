package com.camelot.pmt.project.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.mapper.ProjectBudgetMapper;
import com.camelot.pmt.project.mapper.ProjectMainMapper;
import com.camelot.pmt.project.mapper.ProjectOperateMapper;
import com.camelot.pmt.project.mapper.WarningMapper;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.model.ProjectMain;
import com.camelot.pmt.project.model.ProjectOperate;
import com.camelot.pmt.project.model.Warning;
import com.camelot.pmt.project.service.ProjectMainService;

/**
 * 
 * @author qiaodj
 * @date 2018年4月14日
 */
@Service
public class ProjectMainServiceImpl implements ProjectMainService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectMainServiceImpl.class);
    @Autowired
    private ProjectMainMapper projectMainMapper;
    @Autowired
    private ProjectBudgetMapper projectBudgetMapper;
    @Autowired
    private ProjectOperateMapper projectOperateMapper;
    @Autowired
    private WarningMapper warningMapper;

    /**
     * 保存立项表的方法
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExecuteResult<String> save(ProjectMain projectMain, ProjectOperate projectOperate,
            ProjectBudget projectBudget, Warning warning) {

        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 保存projectMain
            projectMain.setCreateTime(new Date());
            projectMain.setModifyTime(new Date());
            projectMainMapper.insert(projectMain);
            // 保存projectOperate
            projectOperate.setProjectId(projectMain.getId());
            projectOperate.setCreateTime(new Date());
            projectOperateMapper.insert(projectOperate);
            // 保存projectBudget
            projectBudget.setProjectId(projectMain.getId());
            projectBudget.setCreateTime(new Date());
            projectBudget.setModifyTime(new Date());
            projectBudgetMapper.insert(projectBudget);
            // 保存projectBudget
            warning.setProId(projectMain.getId());
            warning.setCreateTime(new Date());
            warning.setModifyTime(new Date());
            warningMapper.insert(warning);
            result.setResult("添加数据成功!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
