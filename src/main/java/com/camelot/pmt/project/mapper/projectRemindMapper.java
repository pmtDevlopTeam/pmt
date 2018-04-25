package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.projectRemind;

public interface projectRemindMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(projectRemind record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(projectRemind record);

    /**
     *
     * @mbggenerated
     */
    projectRemind selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(projectRemind record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(projectRemind record);
}