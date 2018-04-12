package com.camelot.pmt.testmanage.casemanage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCase;
@Mapper
public interface UseCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UseCase record);

    int insertSelective(UseCase record);

    UseCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseCase record);

    int updateByPrimaryKey(UseCase record);
}