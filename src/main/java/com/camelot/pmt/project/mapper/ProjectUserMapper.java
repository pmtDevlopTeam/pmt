package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.ProjectUser;

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
}