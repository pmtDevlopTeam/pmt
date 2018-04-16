package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 定时延期任务是否发送邮件接口
 * 
 * @ClassName: SchedulerServiceImpl
 * @Description: TODO
 * @author zhangao
 * @date 2018年4月11日
 *
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private UserMapper userMapper;

	@Value("${spring.mail.username}")
	private String username;

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	public void schedulerSendWaringEmail() {

		try {
			// 发送已经到达的延期任务的邮件
			sendRedayTaskOverdueEmail();
			// 发送即将到达延期任务的邮件
			sendWillTaskOverdueEmail();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}

		LOGGER.info("发送邮件成功!");

	}

	/**
	 * 发送已经到达延期任务的邮件 @Title: sendRedayTaskOverdueEmail @Description:
	 * TODO @param @return void @throws
	 */
	public void sendRedayTaskOverdueEmail() {

		// 查询已经到达延期时间当前时间大于预期的时间,并开启提示,
		//List<Task> list = taskMapper.queryIsWarningTaskList();

		//for (Task task : list) {

			// 根据指派人id查询用户信息
			//UserModel user = userMapper.selectUserById(task.getAssignUserId());
			// 创建发送邮件内容
			SimpleMailMessage message = new SimpleMailMessage();
			// 管理员发送方
			message.setFrom(username);
			// 发送给任务的指派人
			message.setTo("838101520@qq.com");
			// 发送邮箱的内容
			message.setSubject("郭星亮你的任务延期预警");
			message.setText("你有任务延期的哦");
			// 发送邮箱
			javaMailSender.send(message);
		//}
	}

	/**
	 * 发送即将到达的延期任务的邮件 @Title: sendWillTaskOverdueEmail @Description:
	 * TODO @param @return void @throws
	 */
	public void sendWillTaskOverdueEmail() {
		try {

			// 查询即将过期的延期任务并且剩余时间不能为0,剩余工时小于预警工时
			//List<Task> list = taskMapper.queryWillWarningTaskList();

			//for (Task task : list) {

				// 根据指派人id查询用户信息
				//UserModel user = userMapper.selectUserById(task.getAssignUserId());
				// 创建发送邮件内容
				SimpleMailMessage message = new SimpleMailMessage();
				// 管理员发送方
				message.setFrom(username);
				// 发送给任务的指派人
				message.setTo("838101520@qq.com");
				// 发送邮箱的内容
				message.setSubject("郭星亮你的任务延期预警");
				message.setText("你有任务延期的哦");
				// 发送邮箱
				javaMailSender.send(message);
				//LOGGER.info("给{}发送邮箱成功!", user.getId());
			//}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());

		}
	}

}
