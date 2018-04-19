package com.camelot.pmt.testmanage.bugmanage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;

@Mapper
public interface BugManageMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(BugManage record);

    BugManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BugManage record);

    int updateBugStatusRevoke(BugManage record);

    int updateBugStatusClose(BugManage record);

    int updateBugStatusYes(BugManage record);

    int updateBugAssign(BugManage record);

    int updateBugSolve(BugManage record);

    List<SelectBugManage> selectCondition(Map<String, Object> map);

    List<SelectBugManage> selectByPUS(Map<String, Object> map);
}