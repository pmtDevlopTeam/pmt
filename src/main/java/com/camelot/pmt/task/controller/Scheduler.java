package com.camelot.pmt.task.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.task.service.SchedulerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 定时任务管理
	* @ClassName: Scheduler
	* @Description: TODO
	* @author zhangao
	* @date 2018年4月11日
	*
 */
@Component
public class Scheduler {
	
	@Autowired
	private SchedulerService schedulerService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	/**
	 * 发送已经到达日期的任务预警(每天9点)
	* @Title: schedulerSendWaringEmail
	* @Description: TODO
	* @param @return
	* @return JSONObject 
	* @throws
	 */
    @Scheduled(cron="0 0 9 * * ?") //每天9点执行一次
    public void schedulerSendWaringEmail() {      
		
		try {
			//调用是否发送邮件
			schedulerService.schedulerSendWaringEmail();
			logger.info("发送邮件成功!");
		} catch (Exception e) {
			logger.info("发送邮件失败!");
		}
    }
	
	
 
}
