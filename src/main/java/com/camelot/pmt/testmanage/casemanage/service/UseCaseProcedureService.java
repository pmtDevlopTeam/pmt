package com.camelot.pmt.testmanage.casemanage.service;

import com.camelot.pmt.common.ExecuteResult;

public interface UseCaseProcedureService {
	
	ExecuteResult<String> deleteByPrimaryKey(Long id);
}
