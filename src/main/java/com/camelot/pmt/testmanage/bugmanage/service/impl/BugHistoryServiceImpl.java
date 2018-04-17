package com.camelot.pmt.testmanage.bugmanage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugHistoryMapper;
import com.camelot.pmt.testmanage.bugmanage.model.BugHistory;
import com.camelot.pmt.testmanage.bugmanage.service.BugHistoryService;

@Service
public class BugHistoryServiceImpl implements BugHistoryService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BugHistoryServiceImpl.class);

	@Autowired
	private BugHistoryMapper bugHistoryMapper;
	
	@Override
	public ExecuteResult<List<BugHistory>> selectBugHistoryAll(Long bugId) {
		  ExecuteResult<List<BugHistory>> result = new ExecuteResult<List<BugHistory>>();
	        try {
	            List<BugHistory> list = bugHistoryMapper.selectBugHistoryAll(bugId);
	            if (list.size() <= 0) {
	                return result;
	            }
	            result.setResult(list);
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}

}
