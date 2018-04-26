/**
 * @author: jh 
 * @date: 2018年4月12日 下午5:16:34 
 */
package com.camelot.pmt.project.service;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.project.model.RemindModel;

/**
 * @author lixk
 * @Description: 项目模块-项目预警接口
 * @date 2018年4月17日 下午5:48:37
 */
public interface ProjectRemindService {

    boolean addProjectRemind(RemindModel remindModel, User user);

    RemindModel queryProjectRemindByProjectId(Long projectId);

}
