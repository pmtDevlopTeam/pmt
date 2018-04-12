package com.camelot.pmt.testmanage.casemanage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedureImplement;
@Mapper
public interface UseCaseProcedureImplementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UseCaseProcedureImplement record);

    int insertSelective(UseCaseProcedureImplement record);

    UseCaseProcedureImplement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseCaseProcedureImplement record);

    int updateByPrimaryKey(UseCaseProcedureImplement record);
}