package com.camelot.pmt.project.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author lixk
 * @Description: 项目提醒model
 * @date 2018年4月25日 上午10:28:15
 */
public class RemindModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // 项目提醒主表
    private ProjectRemind projectRemind;

    // 项目提醒内容表
    private List<RemindContent> remindContentList;

    public ProjectRemind getProjectRemind() {
        return projectRemind;
    }

    public void setProjectRemind(ProjectRemind projectRemind) {
        this.projectRemind = projectRemind;
    }

    public List<RemindContent> getRemindContentList() {
        return remindContentList;
    }

    public void setRemindContentList(List<RemindContent> remindContentList) {
        this.remindContentList = remindContentList;
    }
}
