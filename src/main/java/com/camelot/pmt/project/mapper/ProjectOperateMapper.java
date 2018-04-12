package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.ProjectOperate;

public interface ProjectOperateMapper {
    /**
     *
     * @mbggenerated 2018-04-12
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insert(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insertSelective(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    ProjectOperate selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKeySelective(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKeyWithBLOBs(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKey(ProjectOperate record);
}