package com.camelot.pmt.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据项目id 角色id 提醒状态查询数据
     * 
     * @param projectId
     * @param projectRoleId
     * @param remindStatus
     * @return
     */
    List<ProjectRemind> queryByProjectIdAndRemindStatus(@Param("projectId") Long projectId,
            @Param("projectRoleId") String projectRoleId, @Param("remindStatus") String remindStatus);
}