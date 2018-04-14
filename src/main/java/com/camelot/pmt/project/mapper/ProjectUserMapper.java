package com.camelot.pmt.project.mapper;

import java.util.List;

import com.camelot.pmt.project.model.ProjectUser;
import com.camelot.pmt.project.model.ProjectUserShow;

public interface ProjectUserMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(ProjectUser record);

    /**
     * 项目添加成员
     * 
     * @mbggenerated 2018-04-13
     */
    int insertSelective(ProjectUser record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    ProjectUser selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(ProjectUser record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(ProjectUser record);

    /**
     * 确认成员进入项目
     * 
     * @param pu
     * @return
     */
    void confirmUser(ProjectUser pu);

    /**
     * 查询成员
     * 
     * @param projectId
     * @return
     */
    List<ProjectUserShow> searchUserByProjectId(Long projectId);
}