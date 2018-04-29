package com.camelot.pmt.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.camelot.pmt.common.GetDateUtil;
import com.camelot.pmt.platform.service.MailService;
import com.camelot.pmt.project.mapper.ProjectRemindMapper;
import com.camelot.pmt.project.model.ProjectRemind;
import com.camelot.pmt.project.model.RemindContent;
import com.camelot.pmt.project.model.RemindContentChild;
import com.camelot.pmt.project.model.RemindModel;
import com.camelot.pmt.project.model.RemindReport;
import com.camelot.pmt.project.service.ProjectRemindService;
import com.camelot.pmt.project.service.SendProjectReminderService;

/**
 * @Package: com.camelot.pmt.project.service.impl
 * @ClassName: SendProjectReminderServiceImpl
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-27 16:49
 */
public class SendProjectReminderServiceImpl implements SendProjectReminderService {

    @Autowired
    private MailService mailService;
    @Autowired
    private ProjectRemindMapper projectRemindMapper;
    @Autowired
    private ProjectRemindService projectRemindService;

    /**
     * 判断项目是否开启提醒功能
     */
    public void querySendStatusByProId(Long projectId, String userRoleId) {
        // 根据项目id，角色id查询项目提醒主表信息
        ProjectRemind projectRemind = new ProjectRemind();
        projectRemind.setProjectId(projectId);
        projectRemind.setProjectRoleId(userRoleId);
        ProjectRemind queryProjectRemind = projectRemindMapper.queryByProjectRemind(projectRemind);
        // 获取项目提醒状态（01:开启 02:关闭）
        String remindStatus = queryProjectRemind.getRemindStatus();
        // 提醒频次：01:天/次 02:周/次 03:月/次
        String remindFrequency = queryProjectRemind.getRemindFrequency();
        // 若项目开启提醒，则根据对应规则组装数据进行提醒
        if ("01".equals(remindStatus)) {
            // 查询提醒项信息
            RemindModel remindModel = projectRemindService.queryProjectRemindByProjectId(projectId, userRoleId);
            // 内容表信息（二级数据）
            List<RemindContent> remindContentList = remindModel.getRemindContentList();
            for (RemindContent item : remindContentList) {
                List<RemindContentChild> remindContentChildList = item.getRemindContentChildList();
                for (RemindContentChild childItem : remindContentChildList) {
                    // 当前方法的code值
                    childItem.getCode();
                    // 当前方法的url路径
                    String methodUrl = childItem.getMethodUrl();
                    // 依据url路径查询数据
                    queryDataByUrl(methodUrl, projectId, userRoleId);

                }
            }
        }
    }

    public Map queryDataByUrl(String methodUrl, Long projectId, String userRoleId) {
        // TODO: 请求路径，获取数据
        return null;
    }

    /**
     * 发送提醒接口--通知方式：邮件/通知 提醒人，提醒内容，提醒方式
     */
    public void sendReminder(String sendType, String sendUser, String subject, String sengContent) {
        if ("01".equals(sendType)) {
            mailService.sendSimpleMail("17090023169@163.com", "恭喜您，奖金100万已到账", "恭喜您，奖金100万已到账");
            // mailService.sendSimpleMail(sendUser,subject,sengContent);
        }
        if ("02".equals(sendType)) {
            // TODO : 通知待实现，需求未明确
        }

    }

    /**
     * 实现步骤： 1.根据项目id，角色id查询对应规则的数据， 2.将对应的数据的项目id，角色id，角色下人员id，email等信息插入日志表，
     */
    public void sendEmail(Long projectId, String remindFrequency) {
        List<List<RemindReport>> lists = queryListByProidAndDate(projectId, remindFrequency);
        // 接收人邮箱,主题，内容
        mailService.sendSimpleMail("17090023169@163.com", "恭喜您，奖金100万已到账", "恭喜您，奖金100万已到账");
    }

    public void sendNotice(Long projectId, String remindFrequency) {
        List<List<RemindReport>> lists = queryListByProidAndDate(projectId, remindFrequency);
    }

    /**
     * 根据项目id，发送频次查询日志记录信息
     * 
     * @param projectId
     * @param remindFrequency
     */
    public List<List<RemindReport>> queryListByProidAndDate(Long projectId, String remindFrequency) {
        List<List<RemindReport>> datalists = new ArrayList<List<RemindReport>>();
        // 按发送频次获取发送数据（日报：昨日数据，周报;当前时间所在周起止时间(或上一周起止时间)，月报;当前时间所在月份的起始时间（或上一月份的起止时间））
        String[] split = remindFrequency.split(",");
        for (int i = 0; i < split.length; i++) {
            if ("01".equals(split[i])) {
                // 获取昨天起止时间
                String stringBeginDayOfYesterday = GetDateUtil.getStringBeginDayOfYesterday();
                String stringEndDayOfYesterDay = GetDateUtil.getStringEndDayOfYesterDay();
                // 获取数据信息
                // List<RemindReport> oneDayremindReports =
                // queryRemindReportListByProIdAndDate(projectId, stringBeginDayOfYesterday,
                // stringEndDayOfYesterDay);
                List<RemindReport> oneDayremindReports = null;
                datalists.add(oneDayremindReports);
            }
            if ("02".equals(split[i])) {
                // 获取上周起止时间
                // String stringBeginDayOfLastWeek = GetDateUtil.getStringBeginDayOfLastWeek();
                // String stringEndDayOfLastWeek = GetDateUtil.getStringEndDayOfLastWeek();
                // 获取本周起止时间
                String stringBeginDayOfWeek = GetDateUtil.getStringBeginDayOfWeek();
                String stringEndDayOfWeek = GetDateUtil.getStringEndDayOfWeek();
                // 获取数据信息
                // List<RemindReport> weekRemindReports =
                // queryRemindReportListByProIdAndDate(projectId, stringBeginDayOfWeek,
                // stringEndDayOfWeek);
                List<RemindReport> weekRemindReports = null;
                datalists.add(weekRemindReports);
            }
            if ("03".equals(split[i])) {
                // 获取上月月起止时间
                // String stringBeginDayOfLastMonth =
                // GetDateUtil.getStringBeginDayOfLastMonth();
                // String stringEndDayOfLastMonth = GetDateUtil.getStringEndDayOfLastMonth();
                // 获取本月起止时间
                GetDateUtil.getStringBeginDayOfWeek();
                String stringBeginDayOfMonth = GetDateUtil.getStringBeginDayOfMonth();
                String stringEndDayOfMonth = GetDateUtil.getStringEndDayOfMonth();
                // 获取数据信息
                // List<RemindReport> monthRemindReports =
                // queryRemindReportListByProIdAndDate(projectId, stringBeginDayOfMonth,
                // stringEndDayOfMonth);
                List<RemindReport> monthRemindReports = null;
                datalists.add(monthRemindReports);
            }
        }
        return datalists;
    }

    /**
     * 依据项目id，角色id，起止时间查询日志信息
     * 
     * @param projectId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<RemindReport> queryRemindReportListByProIdAndDate(Long projectId, String projectRoleId,
            String startDate, String endDate) {
        // List<RemindReport> remindReportList =
        // remindReportMapper.queryRemindReportListByProIdAndDate(projectId,projectRoleId,
        // startDate, endDate);
        List<RemindReport> remindReportList = null;
        return remindReportList;
    }
}
