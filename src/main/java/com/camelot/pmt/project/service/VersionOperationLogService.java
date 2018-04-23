package com.camelot.pmt.project.service;

import com.camelot.pmt.project.model.VersionOperationLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Package: com.camelot.pmt.project.service
 * @ClassName: versionOperationLogService
 * @Description: 添加/查看版本操作日志
 * @author: xueyj
 * @date: 2018-04-20 15:56
 */
public interface VersionOperationLogService {
    /**
     * 添加版本关联记录信息
     * @param userId
     * @param versionOperationLog
     * @return
     */
    boolean addversionOperationLogByParms(String userId,VersionOperationLog versionOperationLog);

    /**
     * 查询版本关联记录
     * @param versionOperationLog
     * @return
     */
    List<VersionOperationLog> queryVersionOperationLogByParms(VersionOperationLog versionOperationLog);

    /**
     * @Description: 根据versionid查询是否被引用
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:16
     */
    boolean queryVersionLogByVersionId(Long versionId);

    /**
     * 分页查询版本关联记录
     * @param pageNum
     * @param pageSize
     * @param versionOperationLog
     * @return
     */
    PageInfo queryversionOperationLogByPageAndParms(int pageNum, int pageSize, VersionOperationLog versionOperationLog);
}