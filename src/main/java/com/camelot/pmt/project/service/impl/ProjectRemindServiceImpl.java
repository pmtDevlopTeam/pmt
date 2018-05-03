package com.camelot.pmt.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.project.mapper.ProjectRemindMapper;
import com.camelot.pmt.project.mapper.RemindContentChildMapper;
import com.camelot.pmt.project.mapper.RemindContentMapper;
import com.camelot.pmt.project.model.ProjectRemind;
import com.camelot.pmt.project.model.RemindContent;
import com.camelot.pmt.project.model.RemindContentChild;
import com.camelot.pmt.project.model.RemindModel;
import com.camelot.pmt.project.service.ProjectRemindService;

/**
 * @author lixk
 * @Description: 项目模块-项目预警接口类
 * @date 2018年4月17日 下午5:48:37
 */
@Service
public class ProjectRemindServiceImpl implements ProjectRemindService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectRemindServiceImpl.class);
    @Autowired
    ProjectRemindMapper projectRemindMapper;
    @Autowired
    RemindContentMapper remindContentMapper;
    @Autowired
    RemindContentChildMapper remindContentChildMapper;

    /**
     * 添加项目提醒
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProjectRemind(RemindModel remindModel, User user) {
        Date nowDate = new Date();
        int count = 0;
        try {
            ProjectRemind projectRemind = remindModel.getProjectRemind();// 项目提醒主体
            List<RemindContent> remindContentList = remindModel.getRemindContentList();// 项目提醒内容
            deleteProjectRemind(projectRemind);// 删除项目提醒相关表
            String userId = user.getUserId();
            projectRemind.setCreateUserId(userId);
            projectRemind.setCreateTime(nowDate);
            count = projectRemindMapper.insert(projectRemind);
            if (count <= 0) {
                return false;
            }
            Long reportId = projectRemind.getId();
            if ((null != remindContentList) && (remindContentList.size() > 0)) {
                for (RemindContent remindContent : remindContentList) {
                    if (null != remindContent) {
                        remindContent.setRemindId(reportId);
                        remindContent.setCreateUserId(userId);
                        remindContent.setCreateTime(nowDate);
                        count = remindContentMapper.insert(remindContent);
                        if (count <= 0) {
                            return false;
                        }
                        Long contentId = remindContent.getId();
                        List<RemindContentChild> remindContentChildList = remindContent.getRemindContentChildList();
                        if (remindContentChildList.size() > 0) {
                            for (RemindContentChild remindContentChild : remindContentChildList) {
                                if (null != remindContentChild) {
                                    remindContentChild.setContentId(contentId);
                                    remindContentChild.setCreateUserId(userId);
                                    remindContentChild.setCreateTime(nowDate);
                                    count = remindContentChildMapper.insert(remindContentChild);
                                }
                            }
                        }
                    }
                }
            }
            if (count <= 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除项目提醒模块
     */
    private int deleteProjectRemind(ProjectRemind projectRemind) {
        int count = 0;
        ProjectRemind remind = projectRemindMapper.queryByProjectRemind(projectRemind);
        // 查询所有文本中remind_id
        if (null != remind) {
            Long remindId = remind.getId();
            List<RemindContent> remindContentList = remindContentMapper.queryByRemindId(remindId);
            List<RemindContentChild> remindContentChildList = new ArrayList<RemindContentChild>();
            if ((null != remindContentList) && (remindContentList.size() > 0)) {
                for (RemindContent remindContent : remindContentList) {
                    List<RemindContentChild> tempList = remindContentChildMapper
                            .queryByContentId(remindContent.getId());
                    remindContentChildList.addAll(tempList);
                }
            }
            count += projectRemindMapper.deleteByPrimaryKey(remindId);
            count += remindContentMapper.deleteByRemindIdList(remindContentList);
            count += remindContentChildMapper.deleteByContentId(remindContentChildList);
        }

        return count;
    }

    /**
     * 根据项目id&&成员角色id查询项目提醒信息
     */
    @Override
    public RemindModel queryProjectRemindByProjectId(Long projectId, String projectRoleId) {
        RemindModel remindModel = new RemindModel();
        ProjectRemind remind = new ProjectRemind();
        remind.setProjectId(projectId);
        remind.setProjectRoleId(projectRoleId);
        try {
            ProjectRemind projectRemind = projectRemindMapper.queryByProjectRemind(remind);
            if (null == projectRemind) {
                return remindModel;
            }
            remindModel.setProjectRemind(projectRemind);// 项目提醒主表内容
            // 根据项目id查询所有的内容表
            List<RemindContent> remindContentList = remindContentMapper.queryByRemindId(projectRemind.getId());
            if ((null == remindContentList) || (remindContentList.size() <= 0)) {
                return remindModel;
            }
            for (RemindContent remindContent : remindContentList) {
                remindContent.getId();
                List<RemindContentChild> remindContentChildList = remindContentChildMapper
                        .queryByContentId(remindContent.getId());
                remindContent.setRemindContentChildList(remindContentChildList);
            }
            remindModel.setRemindContentList(remindContentList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return remindModel;
    }

    /**
     * 根据项目id 角色id 提醒状态查询数据
     */
    @Override
    public List<ProjectRemind> queryByProjectIdAndRemindStatus(Long projectId, String projectRoleId,
            String remindStatus) {

        try {
            List<ProjectRemind> list = projectRemindMapper.queryByProjectIdAndRemindStatus(projectId, projectRoleId,
                    remindStatus);
            if (list.size() > 0) {
                return list;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public ProjectRemind queryProjectRemindByProIdAndUserRoleId(ProjectRemind remind){
        return projectRemindMapper.queryByProjectRemind(remind);
    }
}
