package com.camelot.pmt.project.service;

/**
 * 
 * @author qiaodj
 * @date 2018年4月27日
 */
public interface RemindReportService {
    /**
     * 新增实体
     * 
     * @param projectId
     * @param remindId
     * @param dailyContent
     * @param remindType
     * @param remindFrequency
     * @return
     */
    int addRemindReport(Long projectId, String projectRoleId, String remindStatus);
}
