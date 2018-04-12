package com.camelot.pmt.testmanage.casemanage.mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedureImplement;

public interface UseCaseProcedureImplementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UseCaseProcedureImplement record);

    int insertSelective(UseCaseProcedureImplement record);

    UseCaseProcedureImplement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseCaseProcedureImplement record);

    int updateByPrimaryKey(UseCaseProcedureImplement record);
}