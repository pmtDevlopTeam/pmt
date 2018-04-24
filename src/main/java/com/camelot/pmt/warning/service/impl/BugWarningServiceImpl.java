package com.camelot.pmt.warning.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.warning.mapper.BugWarningMapper;
import com.camelot.pmt.warning.model.BugWarning;
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
}
