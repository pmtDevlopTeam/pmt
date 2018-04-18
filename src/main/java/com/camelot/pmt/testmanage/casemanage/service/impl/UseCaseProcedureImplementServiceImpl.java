package com.camelot.pmt.testmanage.casemanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedureImplement;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseProcedureImplementService;

@Service
public class UseCaseProcedureImplementServiceImpl implements UseCaseProcedureImplementService{
	
	@Autowired
	UseCaseProcedureImplementService useCaseProcedureImplementService;
	@Override
	public List<UseCaseProcedureImplement> findByImplementId(Long implementId) {
		
		return useCaseProcedureImplementService.findByImplementId(implementId);
	}

}
