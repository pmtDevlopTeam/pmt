package com.camelot.pmt.testmanage.casemanage.mapper;

import com.camelot.pmt.testmanage.casemanage.model.casemanage;

public interface casemanageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(casemanage record);

    int insertSelective(casemanage record);

    casemanage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(casemanage record);

    int updateByPrimaryKey(casemanage record);
}