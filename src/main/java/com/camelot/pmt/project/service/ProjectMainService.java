package com.camelot.pmt.project.service;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.model.ProjectMain;
import com.camelot.pmt.project.model.ProjectOperate;
import com.camelot.pmt.project.model.Warning;

/**
 * 
 * @author qiaodj
 * @date 2018年4月14日
 */
public interface ProjectMainService {
    /**
     * 保存立项时相关联表数据
     * 
     * @param projectMain
     * @param warning
     * @param projectBudget
     * @param projectOperate
     * @return
     */
    ExecuteResult<String> save(ProjectMain projectMain, ProjectOperate projectOperate, ProjectBudget projectBudget,
            Warning warning);
}
