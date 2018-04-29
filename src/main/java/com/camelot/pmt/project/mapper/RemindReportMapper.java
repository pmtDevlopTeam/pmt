package com.camelot.pmt.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.project.model.RemindReport;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据项目Id和提醒主记录id查询出所对应的日报记录
     * 
     * @return
     */
    List<RemindReport> queryRemindReportListByProIdAndDate(Map map);
}