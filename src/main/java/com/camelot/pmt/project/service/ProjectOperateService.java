package com.camelot.pmt.project.service;

import java.util.List;

import com.camelot.pmt.project.model.ProjectOperate;

/**
 * 
 * @author qiaodj
 * @date 2018年4月14日
 */
public interface ProjectOperateService {
    /**
     * 根据创建人id进行查询
     * 
     * @param createUserId
     * @return
     */
    List<ProjectOperate> queryByCreateUserId(String createUserId);

    /**
     * 根据项目id进行查询
     * 
     * @param projectId
     * @return
     */
    List<ProjectOperate> queryByProjectId(Long projectId);

}
