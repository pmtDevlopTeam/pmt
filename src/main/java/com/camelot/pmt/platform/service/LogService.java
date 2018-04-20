package com.camelot.pmt.platform.service;

import com.camelot.pmt.platform.model.Log;

public interface LogService {

    /**
     * 添加删除日志
     * 
     * @param log
     */
    void insertLog(Log log);
}
