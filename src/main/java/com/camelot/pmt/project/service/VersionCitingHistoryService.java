package com.camelot.pmt.project.service;

import com.camelot.pmt.project.model.VersionCitingHistory;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Package: com.camelot.pmt.project.service
 * @ClassName: VersionCitingHistoryService
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-20 15:56
 */
public interface VersionCitingHistoryService {
    /**
     * 添加版本关联记录信息
     * @param userId
     * @param versionCitingHistory
     * @return
     */
    boolean addVersionCitingHistoryByParms(String userId,VersionCitingHistory versionCitingHistory);

    /**
     * 查询版本关联记录
     * @param versionCitingHistory
     * @return
     */
    List<VersionCitingHistory> queryVersionCitingHistoryByParms(VersionCitingHistory versionCitingHistory);

    /**
     * 分页查询版本关联记录
     * @param pageNum
     * @param pageSize
     * @param versionCitingHistory
     * @return
     */
    PageInfo queryVersionCitingHistoryByPageAndParms(int pageNum, int pageSize, VersionCitingHistory versionCitingHistory);
}
