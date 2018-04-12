package com.camelot.pmt.testmanage.casemanage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedure;
@Mapper
public interface UseCaseProcedureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UseCaseProcedure record);

    int insertSelective(UseCaseProcedure record);

    UseCaseProcedure selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseCaseProcedure record);

    int updateByPrimaryKey(UseCaseProcedure record);
}