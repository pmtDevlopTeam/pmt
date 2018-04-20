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
public class BugHistoryServiceImpl implements BugHistoryService {

    @Autowired
    private BugHistoryMapper bugHistoryMapper;

    @Override
    public List<BugHistory> queryBugHistoryAll(Long bugId) {
        List<BugHistory> list = bugHistoryMapper.queryBugHistoryAll(bugId);
        return list;
    }

}
