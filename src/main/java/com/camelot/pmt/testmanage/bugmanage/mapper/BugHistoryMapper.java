package com.camelot.pmt.testmanage.bugmanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.bugmanage.model.BugHistory;

@Mapper
public interface BugHistoryMapper {
  
    int insertSelective(BugHistory record);

    List<BugHistory> selectBugHistoryAll(Long bugId);
    
    BugHistory getBugHistoryByBugId(Long bugId);

}