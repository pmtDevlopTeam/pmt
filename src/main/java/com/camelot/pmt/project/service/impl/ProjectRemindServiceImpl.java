package com.camelot.pmt.project.service.impl;

import java.util.Date;
import java.util.List;

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
            ProjectRemind projectRemind = remindModel.getProjectRemind();//项目提醒主体
            List<RemindContent> remindContentList = remindModel.getRemindContentList();//项目提醒内容
            String userId = user.getUserId();
            projectRemind.setCreateUserId(userId);
            projectRemind.setCreateTime(nowDate);
            count = projectRemindMapper.insert(projectRemind);
            if(count <= 0){
                return false;
            }
            Long reportId = projectRemind.getId();
            if((null != remindContentList)&&(remindContentList.size()>0)){
                for (RemindContent remindContent : remindContentList) {
                    if(null != remindContent){
                        remindContent.setRemindId(reportId);
                        remindContent.setCreateUserId(userId);
                        remindContent.setCreateTime(nowDate);
                        count = remindContentMapper.insert(remindContent);
                        if(count <= 0){
                            return false;
                        }
                        Long contentId = remindContent.getId();
                        List<RemindContentChild> remindContentChildList = remindContent.getRemindContentChildList();
                        if(remindContentChildList.size()>0){
                            for (RemindContentChild remindContentChild : remindContentChildList) {
                                if(null != remindContentChild){
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
            if(count <= 0){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
