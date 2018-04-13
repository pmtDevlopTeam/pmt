package com.camelot.pmt.testmanage.bugmanage.mapper;

import com.camelot.pmt.testmanage.bugmanage.model.bugmanage;
import com.camelot.pmt.testmanage.bugmanage.model.bugmanageKey;

public interface bugmanageMapper {
    int deleteByPrimaryKey(bugmanageKey key);

    int insert(bugmanage record);

    int insertSelective(bugmanage record);

    bugmanage selectByPrimaryKey(bugmanageKey key);

    int updateByPrimaryKeySelective(bugmanage record);

    int updateByPrimaryKey(bugmanage record);
}