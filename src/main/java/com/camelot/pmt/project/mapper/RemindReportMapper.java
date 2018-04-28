package com.camelot.pmt.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
     * 新增实体
     * 
     * @param remindReports
     * @return
     */
    int addRemindReport(@Param("remindReports") List<RemindReport> remindReports);

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