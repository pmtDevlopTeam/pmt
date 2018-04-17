package com.camelot.pmt.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.logging.LogFactory;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskLogService;
/**
 * 日志管理接口
	* @ClassName: TaskLogServiceImpl
	* @Description: TODO
	* @author zhangao
	* @date 2018年4月16日
	*
 */
@Service
public class TaskLogServiceImpl implements TaskLogService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskLogServiceImpl .class);

	@Autowired
	private TaskLogMapper taskLogMapper;
	/**
	 * 添加日志记录
	 */
	@Override
	public ExecuteResult<String> insertTaskLog(TaskLog tasklog) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (tasklog == null) {
				result.addErrorMessage("传入的日志有误!");
				return result;
			}

			int count = taskLogMapper.insertTaskLog(tasklog);
			if (count > 0) {
				result.setResult("添加日志成功!");
			} else {
				result.setResult("添加日志失败!");
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}

}
