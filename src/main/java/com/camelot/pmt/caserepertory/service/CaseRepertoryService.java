package com.camelot.pmt.caserepertory.service;

import java.util.Map;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.github.pagehelper.PageInfo;

public interface CaseRepertoryService {

	ExecuteResult<PageInfo> selectCondition(Map<String, Object> map);

}
