package com.camelot.pmt.testmanage.casemanage.service;

import java.util.List;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseDetail;

public interface UseCaseDetailService {
	
	
	List<UseCaseDetail> queryUseCaseProcedureImplementd(Long implementId);
}
