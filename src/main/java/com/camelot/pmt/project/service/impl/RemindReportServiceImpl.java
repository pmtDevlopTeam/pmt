package com.camelot.pmt.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.common.BussinessException;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.project.mapper.ProjectRemindMapper;
import com.camelot.pmt.project.mapper.RemindContentChildMapper;
import com.camelot.pmt.project.mapper.RemindContentMapper;
import com.camelot.pmt.project.mapper.RemindReportMapper;
import com.camelot.pmt.project.model.RemindReport;
import com.camelot.pmt.project.service.RemindReportService;

/**
 * 
 * @author qiaodj
 * @date 2018年4月27日
 */
@Service
public class RemindReportServiceImpl implements RemindReportService {

    private static final Logger logger = LoggerFactory.getLogger(RemindReportServiceImpl.class);
    @Autowired
    private RemindReportMapper remindReportMapper;
    @Autowired
    private RemindContentChildMapper remindContentChildMapper;
    @Autowired
    private RemindContentMapper remindContentMapper;
    @Autowired
    private ProjectRemindMapper projectRemindMapper;

    /**
     * 新增实体
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addRemindReport(Long projectId, String projectRoleId, String remindStatus) {
        // 判断是否开启提醒 01开启 02关闭
        if ("01".equals(remindStatus)) {
            try {
                // 保存RemindReport
                User user = (User) ShiroUtils.getSessionAttribute("user");
                if (user != null && user.getUserId() != null) {

                    List<RemindReport> remindReportList = new ArrayList<>();
                    projectRemindMapper.queryByProjectIdAndRemindStatus(projectId, projectRoleId, remindStatus).stream()
                            .forEach(e -> {

                                remindContentMapper.queryByProjectIdAndByRemindId(projectId, e.getId()).stream()
                                        .forEach(a -> {

                                            remindContentChildMapper
                                                    .queryByProjectIdAndByContentId(projectId, a.getId()).stream()
                                                    .forEach(b -> {
                                                        RemindReport remindReport = new RemindReport();
                                                        remindReport.setProjectId(projectId);
                                                        remindReport.setRemindId(e.getId());
                                                        remindReport.setRemindType(e.getRemindType());
                                                        // 调用生成日报内容接口
                                                        remindReport.setDailyContent(b.getMethodUrl());
                                                        remindReport.setRemindFrequency(e.getRemindFrequency());
                                                        remindReport.setCreateUserId(user.getUserId());
                                                        remindReport.setCreateTime(new Date());

                                                        remindReportList.add(remindReport);
                                                    });
                                        });

                            });
                    int remindReportNum = remindReportMapper.addRemindReport(remindReportList);
                    if (remindReportNum > 0) {
                        return 1;
                    }

                    throw new BussinessException("新增提醒报告失败");
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }

        }
        return 0;
    }

}
