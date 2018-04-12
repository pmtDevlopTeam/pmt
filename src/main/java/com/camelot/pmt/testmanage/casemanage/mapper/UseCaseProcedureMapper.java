package com.camelot.pmt.testmanage.casemanage.mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedure;

public interface UseCaseProcedureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UseCaseProcedure record);

    int insertSelective(UseCaseProcedure record);

    UseCaseProcedure selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseCaseProcedure record);

    int updateByPrimaryKey(UseCaseProcedure record);
}