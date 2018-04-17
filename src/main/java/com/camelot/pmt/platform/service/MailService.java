package com.camelot.pmt.platform.service;

/**
 * <p>
 *  邮件服务类
 * </p>
 *
 * @author gnerv
 * @since 2018-04-08
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
	void sendSimpleMail(String to, String subject, String content,  String filePath);
}
