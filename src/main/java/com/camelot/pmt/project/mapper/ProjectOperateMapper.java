package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.ProjectOperate;

public interface ProjectOperateMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    ProjectOperate selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(ProjectOperate record);
}