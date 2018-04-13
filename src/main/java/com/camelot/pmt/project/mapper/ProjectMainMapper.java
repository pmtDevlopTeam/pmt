package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.ProjectMain;

public interface ProjectMainMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(ProjectMain record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(ProjectMain record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    ProjectMain selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(ProjectMain record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(ProjectMain record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(ProjectMain record);
}