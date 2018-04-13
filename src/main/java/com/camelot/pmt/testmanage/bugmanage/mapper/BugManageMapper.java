package com.camelot.pmt.testmanage.bugmanage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.bugmanage.model.BugManage;

@Mapper
public interface BugManageMapper {
	 int deleteByPrimaryKey(Long id);

     int insertSelective(BugManage record);

     BugManage selectByPrimaryKey(Long id);

     int updateByPrimaryKeySelective(BugManage record);
     
     int updateBugStatusRevoke(Long id);
     
     int updateBugStatusClose(BugManage record);
     
     int updateBugStatusYes(BugManage record);
     
     int updateBugAssign(BugManage record);
     
     int updateBugSolve(BugManage record);
}