package com.camelot.pmt.warning.service;

import com.camelot.pmt.warning.model.BugWarning;

public interface BugWarningService {

	 boolean addBugWarning(BugWarning bugWarning);
	 //获取到时的bug数
	 Integer queryBugCount(Long projectId);
}
