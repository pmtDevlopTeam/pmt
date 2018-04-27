package com.camelot.pmt.warning.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.warning.mapper.BugWarningMapper;
import com.camelot.pmt.warning.model.BugWarning;
import com.camelot.pmt.warning.model.BugWarningVo;
import com.camelot.pmt.warning.service.BugWarningService;

@Service
public class BugWarningServiceImpl implements BugWarningService {
	
	@Autowired
	BugWarningMapper bugWarningMapper;
	
	/**
	 * 添加bug
	 */
	@Transactional
	public boolean addBugWarning(BugWarning bugWarning) {
		bugWarning.setCreateTime(new Date());
		int addCount = bugWarningMapper.insertSelective(bugWarning);
		return addCount==1?true:false;
	}

	@Override
	public Integer queryBugCount(Long projectId) {
		return bugWarningMapper.queryBugCount(projectId);
	}
	
	/**
	 * 
	 *参数projectId为项目id
	 * 参数roleId为角色id
	 */
	public Map<String,List<String>> queryWarningByprojectIdAndRoleId(BugWarningVo bugWarningVo){
		//角色判断 暂时空 项目经理
		if("".equals(bugWarningVo.getRoleId())){
			List<Map<String,Object>> list1=bugWarningMapper.queryjlList(bugWarningVo);
			List <String>listEmail = new ArrayList<String>();
			List <String>listUserName = new ArrayList<String>();
			Map<String,List<String>> map =new HashMap<String,List<String>>();
			if(list1.size()>1){
				StringBuffer buffer= new StringBuffer();
				List<Map<String,Object>> list=bugWarningMapper.queryWarningByprojectId(bugWarningVo);
				for(Map<String,Object> bl:list){
					buffer.append(bl.get("user_name").toString()+"有长期未解决的bug");
				}
				
				for(Map<String,Object> b2:list1){
					String email=b2.get("user_mail")==null?"":b2.get("user_mail").toString();
					listEmail.add(email);
					listUserName.add(buffer.toString());
				}
			}
			map.put("email", listEmail);
			map.put("userName", listUserName);
			
			return map;
		}else{
			List<Map<String,Object>> list=bugWarningMapper.queryWarningByprojectIdAndRoleId(bugWarningVo);
			Map<String,List<String>> map =new HashMap<String,List<String>>();
			List <String>listEmail = new ArrayList<String>();
			List <String>listUserName = new ArrayList<String>();
			for(Map<String,Object> bl:list){
				String email=bl.get("user_mail").toString();
				String username=bl.get("user_name").toString()+"有长期未解决的bug";
				listEmail.add(email);
				listUserName.add(username);
			}
			map.put("email", listEmail);
			map.put("userName", listUserName);
			
			return map;
		}
	}
}
