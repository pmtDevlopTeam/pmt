package com.camelot.pmt.testmanage.casemanage.mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseImplement;

public interface UseCaseImplementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UseCaseImplement record);

    int insertSelective(UseCaseImplement record);

    UseCaseImplement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseCaseImplement record);

    int updateByPrimaryKey(UseCaseImplement record);
}