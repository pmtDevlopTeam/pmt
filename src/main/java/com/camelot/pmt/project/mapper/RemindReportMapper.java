package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.RemindReport;

public interface RemindReportMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(RemindReport record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(RemindReport record);

    /**
     *
     * @mbggenerated
     */
    RemindReport selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RemindReport record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(RemindReport record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RemindReport record);
}