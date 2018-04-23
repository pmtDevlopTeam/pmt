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
 * @Description: TODO
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
    public boolean addversionOperationLogByParms(String userId,VersionOperationLog versionOperationLog){
        Date dateTime = new Date();
        versionOperationLog.setCreateTime(dateTime);
        versionOperationLog.setModifyTime(dateTime);
        versionOperationLog.setCreateUserId(userId);
        versionOperationLog.setModifyUserId(userId);
        return versionOperationLogMapper.insertSelective(versionOperationLog)==1?true:false;
    }
    /**
     * @Description: 查询引用记录信息--判断是否允许添加信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/20 16:21
     */
    public List<VersionOperationLog> queryversionOperationLogByParms(VersionOperationLog versionOperationLog){
        return versionOperationLogMapper.queryversionOperationLogByParms(versionOperationLog);
    }
    /**
     * @Description: 分页查询引用记录信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/20 16:21
     */
    public PageInfo queryversionOperationLogByPageAndParms(int pageNum, int pageSize, VersionOperationLog versionOperationLog){
        /*
         * pageHelper使用三部曲 1.启动pageHelper分页 startPage -- start 2.填充自己的sql（查询逻辑）
         * 3.pageHelper的收尾
         */
        // 初始化分页信息
        PageHelper.startPage(pageNum, pageSize);
        // 查询产品list
        List<VersionOperationLog> versionOperationLogList = versionOperationLogMapper.queryversionOperationLogByParms(versionOperationLog);
        // pageHelper的收尾
        PageInfo pageResult = new PageInfo(versionOperationLogList);
        pageResult.setList(versionOperationLogList);
        return pageResult;
    }
}