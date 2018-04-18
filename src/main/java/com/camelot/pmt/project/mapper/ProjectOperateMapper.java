package com.camelot.pmt.project.mapper;

import java.util.List;

import com.camelot.pmt.project.model.ProjectOperate;

public interface ProjectOperateMapper {
    /**
     * 根据项目id进行查询
     * 
     * @param createUserId
     * @return
     */
    List<ProjectOperate> queryByProjectId(Long projectId);

    /**
     * 根据创建人id进行查询
     * 
     * @param createUserId
     * @return
     */
    List<ProjectOperate> queryByCreateUserId(String createUserId);

    /**
     * 插入数据
     * 
     * @param projectOperate
     * @return
     */
    int addProjectOperate(ProjectOperate projectOperate);

}