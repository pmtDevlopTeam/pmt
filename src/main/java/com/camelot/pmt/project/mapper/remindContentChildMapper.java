package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.remindContentChild;

public interface remindContentChildMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(remindContentChild record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(remindContentChild record);

    /**
     *
     * @mbggenerated
     */
    remindContentChild selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(remindContentChild record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(remindContentChild record);
}