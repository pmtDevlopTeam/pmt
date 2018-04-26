package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.ProjectRemind;

public interface ProjectRemindMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(ProjectRemind record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(ProjectRemind record);

    /**
     *
     * @mbggenerated
     */
    ProjectRemind selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProjectRemind record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ProjectRemind record);

    int deleteByProjectId(Long projectId);

    ProjectRemind queryByProjectId(Long projectId);

    ProjectRemind queryByProjectRemind(ProjectRemind projectRemind);
}