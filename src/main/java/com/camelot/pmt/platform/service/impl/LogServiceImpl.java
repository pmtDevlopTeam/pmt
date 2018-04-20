package com.camelot.pmt.platform.service.impl;

import com.camelot.pmt.platform.mapper.LogMapper;
import com.camelot.pmt.platform.model.Log;
import com.camelot.pmt.platform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 添加删除日志
     * 
     * @param log
     */
    @Override
    public void insertLog(Log log) {
        logMapper.insertLog(log);
    }
}
