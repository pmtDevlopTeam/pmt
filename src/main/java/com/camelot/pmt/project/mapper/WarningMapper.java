package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.Warning;

public interface WarningMapper {
    /**
     *
     * @mbggenerated 2018-04-12
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insert(Warning record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insertSelective(Warning record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    Warning selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKeySelective(Warning record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKey(Warning record);
}