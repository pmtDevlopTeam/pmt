package com.camelot.pmt.platform.log;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.model.Log;
import com.camelot.pmt.platform.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 
 * @author gnerv
 * @Description 基础平台操作日志记录切面类
 * @date 2018年4月19日
 */
@Component
public class LogAspect<T> {

	@Autowired
	private LogService logService;


	/**
	 * 插入删除日志
	 * @param keyWord 模块关键字
	 * @param userId 用户id
	 * @param name 数据名称
	 */
	public void insertDeleteLog(String keyWord, String userId, String name){
		Log log = new Log();
		log.setLogOperationUserId(userId);
		log.setLogOperationType("删除");
		log.setLogContent(keyWord + ":" + name);
		log.setLogType("0");
		long date = new Date().getTime();
		log.setLogOperationTime(new Date(date));
		logService.insertLog(log);
	}

	/**
	 * 插入修改日志
	 * @param newObj
	 * @param object
	 * @param keyWord
	 * @param userId
	 * @throws JsonProcessingException
	 */
	public void insertUpdateLog(Object newObj, Object object, String keyWord, String userId) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String newJson = objectMapper.writeValueAsString(newObj);
			Map newMaps = (Map) JSONObject.parse(newJson);
			String json = objectMapper.writeValueAsString(object);
			Map maps = (Map) JSONObject.parse(json);
			StringBuffer stringBuffer = new StringBuffer();
			for (Object map : maps.entrySet()) {
				for (Object newMap : newMaps.entrySet()) {
					if (((Map.Entry) map).getKey() == ((Map.Entry) newMap).getKey()) {
						if (((Map.Entry) newMap).getValue() != null && !((Map.Entry) newMap).getValue().equals("null")) {
							if (((Map.Entry) map).getValue() != ((Map.Entry) newMap).getValue() && !((Map.Entry) newMap).getValue().equals(((Map.Entry) map).getValue())) {
								stringBuffer.append(((Map.Entry) map).getKey());
								stringBuffer.append("由'" + ((Map.Entry) map).getValue() + "'修改为:" + ((Map.Entry) newMap).getValue() + ".");
							}
						}
					}
				}
			}
			System.err.println(stringBuffer);
			Log log = new Log();
			log.setLogOperationUserId(userId);
			log.setLogOperationType("修改");
			log.setLogContent(keyWord + ":" + stringBuffer);
			log.setLogType("0");
			long date = new Date().getTime();
			log.setLogOperationTime(new Date(date));
			logService.insertLog(log);
			System.err.println(stringBuffer);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}


	/**
	 * 插入添加日志
	 * @param object
	 * @param keyWord
	 * @param userId
	 */
	public void insertAddLog(Object object, String keyWord, String userId) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(object);
			Map maps = (Map) JSONObject.parse(json);
			StringBuffer stringBuffer = new StringBuffer();
			for (Object map : maps.entrySet()) {
				if(((Map.Entry)map).getValue() != null && !((Map.Entry)map).getValue().equals("null")) {
					stringBuffer.append("添加:" + ((Map.Entry) map).getKey() + ":" + ((Map.Entry) map).getValue() + ".");
				}
			}
			Log log = new Log();
			log.setLogOperationUserId(userId);
			log.setLogOperationType("添加");
			log.setLogContent(keyWord + ":" + stringBuffer);
			log.setLogType("0");
			long date = new Date().getTime();
			log.setLogOperationTime(new Date(date));
			logService.insertLog(log);
			System.err.println(stringBuffer);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public void insertBindingLog(String ids, String name, String keyWord, String userId) {
		Log log = new Log();
		log.setLogOperationUserId(userId);
		log.setLogOperationType("绑定");
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(keyWord + "'" + name + "'" +"更新为:" + ids);
		log.setLogContent(keyWord + ":" + stringBuffer);
		log.setLogType("0");
		long date = new Date().getTime();
		log.setLogOperationTime(new Date(date));
		logService.insertLog(log);
	}
	
}
