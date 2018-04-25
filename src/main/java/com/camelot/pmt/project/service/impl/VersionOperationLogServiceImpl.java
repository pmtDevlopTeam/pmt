package com.camelot.pmt.project.service.impl;

import com.camelot.pmt.project.mapper.VersionOperationLogMapper;
import com.camelot.pmt.project.model.VersionOperationLog;
import com.camelot.pmt.project.service.VersionOperationLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Package: com.camelot.pmt.project.service.impl
 * @ClassName: versionOperationLogServiceImpl
 * @Description: 添加/查看版本操作日志
 * @author: xueyj
 * @date: 2018-04-20 15:56
 */
@Service
public class VersionOperationLogServiceImpl implements VersionOperationLogService {
    @Autowired
    private VersionOperationLogMapper versionOperationLogMapper;

    /**
     * @Description: 添加版本操作记录信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/20 16:25
     */
    @Override
    public boolean addversionOperationLogByParms(String userId, VersionOperationLog versionOperationLog) {
        Date dateTime = new Date();
        versionOperationLog.setCreateTime(dateTime);
        versionOperationLog.setModifyTime(dateTime);
        versionOperationLog.setCreateUserId(userId);
        versionOperationLog.setModifyUserId(userId);
        return versionOperationLogMapper.insertSelective(versionOperationLog) == 1 ? true : false;
    }

    /**
     * @Description: 查询引用记录信息--判断是否允许添加信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/20 16:21
     */
    @Override
    public List<VersionOperationLog> queryVersionOperationLogByParms(VersionOperationLog versionOperationLog) {
        List<VersionOperationLog> versionOperationLogs = versionOperationLogMapper
                .queryVersionOperationLogByParms(versionOperationLog);
        return versionOperationLogs;
    }

    /**
     * @Description: 根据versionid查询是否被引用
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:16
     */
    @Override
    public boolean queryVersionLogByVersionId(Long versionId) {
        VersionOperationLog versionOperationLog = new VersionOperationLog();
        versionOperationLog.setVersionId(versionId);
        List<VersionOperationLog> versionOperationLogs = versionOperationLogMapper
                .queryVersionOperationLogByParms(versionOperationLog);
        if (versionOperationLogs.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 分页查询引用记录信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/20 16:21
     */
    @Override
    public PageInfo queryversionOperationLogByPageAndParms(int pageNum, int pageSize,
            VersionOperationLog versionOperationLog) {
        /*
         * pageHelper使用三部曲 1.启动pageHelper分页 startPage -- start 2.填充自己的sql（查询逻辑）
         * 3.pageHelper的收尾
         */
        // 初始化分页信息
        PageHelper.startPage(pageNum, pageSize);
        // 查询产品list
        List<VersionOperationLog> versionOperationLogList = versionOperationLogMapper
                .queryVersionOperationLogByParms(versionOperationLog);
        // pageHelper的收尾
        PageInfo pageResult = new PageInfo(versionOperationLogList);
        pageResult.setList(versionOperationLogList);
        return pageResult;
    }
}