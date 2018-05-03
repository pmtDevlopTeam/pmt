/**
 * @author: jh 
 * @date: 2018年4月12日 下午5:16:34 
 */
package com.camelot.pmt.project.service;

import java.util.List;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.project.model.ProjectRemind;
import com.camelot.pmt.project.model.RemindModel;

/**
 * @author lixk
 * @Description: 项目模块-项目预警接口
 * @date 2018年4月17日 下午5:48:37
 */
public interface ProjectRemindService {

    boolean addProjectRemind(RemindModel remindModel, User user);

    RemindModel queryProjectRemindByProjectId(Long projectId, String projectRoleId);

    /**
     * 根据项目id 角色id 提醒状态查询数据
     * 
     * @param projectId
     * @param projectRoleId
     * @param remindStatus
     * @return
     */
    List<ProjectRemind> queryByProjectIdAndRemindStatus(Long projectId, String projectRoleId, String remindStatus);
    ProjectRemind queryProjectRemindByProIdAndUserRoleId(ProjectRemind remind);
}
