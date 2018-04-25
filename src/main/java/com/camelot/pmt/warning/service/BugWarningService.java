package com.camelot.pmt.warning.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.warning.model.BugWarning;
import com.camelot.pmt.warning.model.BugWarningVo;

public interface BugWarningService {

	 boolean addBugWarning(BugWarning bugWarning);
	 //获取当时的bug数
	 Integer queryBugCount(Long projectId);
	 
	 Map<String,List<String>> queryWarningByprojectIdAndRoleId(BugWarningVo bugWarningVo);
}
