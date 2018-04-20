package com.camelot.pmt.project.service.impl;

import com.camelot.pmt.project.mapper.VersionCitingHistoryMapper;
import com.camelot.pmt.project.model.Version;
import com.camelot.pmt.project.model.VersionCitingHistory;
import com.camelot.pmt.project.model.VersionVo;
import com.camelot.pmt.project.service.VersionCitingHistoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Package: com.camelot.pmt.project.service.impl
 * @ClassName: VersionCitingHistoryServiceImpl
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-20 15:56
 */
@Service
public class VersionCitingHistoryServiceImpl implements VersionCitingHistoryService {
    @Autowired
    private VersionCitingHistoryMapper versionCitingHistoryMapper;

    /**
      * @Description: 添加版本操作记录信息
      * @param: 
      * @return: 
      * @author: xueyj
      * @date: 2018/4/20 16:25
      */
    public boolean addVersionCitingHistoryByParms(String userId,VersionCitingHistory versionCitingHistory){
        Date dateTime = new Date();
        versionCitingHistory.setCreateTime(dateTime);
        versionCitingHistory.setModifyTime(dateTime);
        versionCitingHistory.setCreateUserId(userId);
        versionCitingHistory.setModifyUserId(userId);
        return versionCitingHistoryMapper.insertSelective(versionCitingHistory)==1?true:false;
    }
    /**
     * @Description: 查询引用记录信息--判断是否允许添加信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/20 16:21
     */
    public List<VersionCitingHistory> queryVersionCitingHistoryByParms(VersionCitingHistory versionCitingHistory){
        return versionCitingHistoryMapper.queryVersionCitingHistoryByParms(versionCitingHistory);
    }
    /**
     * @Description: 分页查询引用记录信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/20 16:21
     */
    public PageInfo queryVersionCitingHistoryByPageAndParms(int pageNum, int pageSize, VersionCitingHistory versionCitingHistory){
        /*
         * pageHelper使用三部曲 1.启动pageHelper分页 startPage -- start 2.填充自己的sql（查询逻辑）
         * 3.pageHelper的收尾
         */
        // 初始化分页信息
        PageHelper.startPage(pageNum, pageSize);
        // 查询产品list
        List<VersionCitingHistory> versionCitingHistoryList = versionCitingHistoryMapper.queryVersionCitingHistoryByParms(versionCitingHistory);
        // pageHelper的收尾
        PageInfo pageResult = new PageInfo(versionCitingHistoryList);
        pageResult.setList(versionCitingHistoryList);
        return pageResult;
    }
}
