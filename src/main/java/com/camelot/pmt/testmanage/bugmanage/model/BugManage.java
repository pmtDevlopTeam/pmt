package com.camelot.pmt.testmanage.bugmanage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugManage{
	private Long id;
	 //标题
	private String bugTitle;
	//项目ID
	private Long projectId;
	//相关需求ID
    private Long demandId;
    //相关任务ID
    private Long taskId;
    //指派人
    private String designatedId;
    //影响版本ID
    private Long versionId;
    //bug类型
    private String bugType;
    //测试终端
    private String caseTerminal;
    //测试环境
    private String caseEnvironment;
    //bug级别（优先级）
    private String bugLevel;
    //状态（0激活，1已解决，2已关闭）
    private String bugStatus;
    //创建者
    private String createUserId;
    //创建时间
    private String createTime;
    //截止日期
    private String endTime;
    //更新者
    private String modifyUserId;
    //更新时间
    private String modifyTime;
    //删除标记
    private String delFlag;
    //重现步骤
    private String stepsReproduce;
    //解决时间
    private String solveTime;
    //解决方案
    private Integer solveProgram;
    //解决人
    private String solveId;
    //关闭时间
    private String closeTime;
    //关闭人
    private String closeId;
    //严重程度
    private String seriousDegree;
    //关闭原因
    private String closeReason;
    //用例ID
    private Long caseId;
    //bug描述
    private String bugDescribe;
    //关闭状态（正常，不正常）
    private String closeStauts;
}