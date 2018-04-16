package com.camelot.pmt.project.service;

import java.util.List;

import com.camelot.pmt.project.model.ProjectUser;
import com.camelot.pmt.project.model.ProjectUserShow;

public interface ProjectUserService {

    /**
     * 确认成员进入项目
     * 
     * @param pu
     * @return
     */
    void confirmUser(ProjectUser pu);

    /**
     * 项目添加成员
     * 
     * @param pu
     */
    void insertSelective(ProjectUser pu);

    /**
     * 查询项目成员
     * 
     * @param projectId
     * @return
     */
    List<ProjectUserShow> searchUserByProjectId(Long projectId);

}
