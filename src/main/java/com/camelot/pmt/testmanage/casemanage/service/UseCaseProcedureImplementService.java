package com.camelot.pmt.testmanage.casemanage.service;

import java.util.List;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedureImplement;

public interface UseCaseProcedureImplementService {

    List<UseCaseProcedureImplement> findByImplementId(Long implementId);
}
