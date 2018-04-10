package com.camelot.pmt.testmanage.mapper;

import com.camelot.pmt.testmanage.model.BugManage;

public interface BugManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BugManage record);

    int insertSelective(BugManage record);

    BugManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BugManage record);

    int updateByPrimaryKey(BugManage record);
}