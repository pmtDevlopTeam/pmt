package com.camelot.pmt.testmanage.casemanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseDetailMapper;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseDetail;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseDetailService;

@Service
public class UseCaseDetailImpl implements UseCaseDetailService{
	
	@Autowired
	UseCaseDetailMapper useCaseProcedureImplementMapper;
	@Override
	public List<UseCaseDetail> queryUseCaseProcedureImplementd(Long implementId) {
		
		return useCaseProcedureImplementMapper.queryUseCaseProcedureImplementd(implementId);
	}

}
