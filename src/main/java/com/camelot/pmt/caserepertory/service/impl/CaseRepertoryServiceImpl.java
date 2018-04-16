package com.camelot.pmt.caserepertory.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.caserepertory.mapper.CaseRepertoryMapper;
import com.camelot.pmt.caserepertory.service.CaseRepertoryService;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugHistoryMapper;
import com.github.pagehelper.PageInfo;
@Service
public class CaseRepertoryServiceImpl implements CaseRepertoryService {
	@Autowired
	private CaseRepertoryMapper caseRepertoryMapper;

	@Override
	public ExecuteResult<PageInfo> selectCondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
