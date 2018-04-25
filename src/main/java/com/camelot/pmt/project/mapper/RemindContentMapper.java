package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.RemindContent;

public interface RemindContentMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(RemindContent record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(RemindContent record);

    /**
     *
     * @mbggenerated
     */
    RemindContent selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RemindContent record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RemindContent record);
}