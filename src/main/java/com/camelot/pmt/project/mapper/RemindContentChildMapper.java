package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.RemindContentChild;

public interface RemindContentChildMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(RemindContentChild record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(RemindContentChild record);

    /**
     *
     * @mbggenerated
     */
    RemindContentChild selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RemindContentChild record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RemindContentChild record);
}