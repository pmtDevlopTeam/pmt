package com.camelot.pmt.testmanage.casemanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseProcedureMapper;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseProcedureService;

@Service
public class UseCaseProcedureServiceImpl implements UseCaseProcedureService{
	
	@Autowired
	UseCaseProcedureMapper useCaseProcedureMapper;
	
	public void deleteByPrimaryKey(Long id){
		
		useCaseProcedureMapper.deleteByPrimaryKey(id);
	}
}
