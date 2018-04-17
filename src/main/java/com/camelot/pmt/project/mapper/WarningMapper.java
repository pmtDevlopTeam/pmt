package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.Warning;

public interface WarningMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(Warning record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(Warning record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    Warning selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(Warning record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(Warning record);
}