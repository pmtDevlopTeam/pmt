package com.camelot.pmt.testmanage.bugmanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.bugmanage.model.BugHistory;

@Mapper
public interface BugHistoryMapper {

    int addBugHistory(BugHistory record);

    List<BugHistory> queryBugHistoryAll(Long bugId);

    BugHistory queryBugHistoryByBugId(Long bugId);

}