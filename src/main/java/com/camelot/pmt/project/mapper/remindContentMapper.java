package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.remindContent;

public interface remindContentMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(remindContent record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(remindContent record);

    /**
     *
     * @mbggenerated
     */
    remindContent selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(remindContent record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(remindContent record);
}