package com.camelot.pmt.caserepertory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.caserepertory.mapper.CaseRepertoryMapper;
import com.camelot.pmt.caserepertory.service.CaseRepertoryService;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugHistoryMapper;
@Service
public class CaseRepertoryServiceImpl implements CaseRepertoryService {
	@Autowired
	private CaseRepertoryMapper caseRepertoryMapper;
}
