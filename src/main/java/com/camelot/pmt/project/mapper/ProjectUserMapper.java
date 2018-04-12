package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.ProjectUser;

public interface ProjectUserMapper {
    /**
     *
     * @mbggenerated 2018-04-12
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insert(ProjectUser record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insertSelective(ProjectUser record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    ProjectUser selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKeySelective(ProjectUser record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKey(ProjectUser record);
}