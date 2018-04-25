package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.remindReport;

public interface remindReportMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(remindReport record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(remindReport record);

    /**
     *
     * @mbggenerated
     */
    remindReport selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(remindReport record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(remindReport record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(remindReport record);
}