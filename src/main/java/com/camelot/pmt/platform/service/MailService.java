package com.camelot.pmt.platform.service;

/**
 *
 * @author gnerv
 * @Description 邮件服务类
 * @date 2018年4月18日
 */
public interface MailService {
	
	/**
	 * 
	 * @param to 接收人邮箱
	 * @param subject	主题
	 * @param content	内容
	 */
	void sendSimpleMail(String to, String subject, String content);
	
	/**
	 * 
	 * @param to	接收人邮箱
	 * @param subject	主题
	 * @param content	内容
	 * @param filePath	附件
	 */
	void sendSimpleMail(String to, String subject, String content, String filePath);
}
