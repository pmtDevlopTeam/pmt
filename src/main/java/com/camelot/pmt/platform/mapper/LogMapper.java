package com.camelot.pmt.platform.mapper;

import com.camelot.pmt.platform.model.Log;

public interface LogMapper {

    /**
     * 添加删除日志
     * 
     * @param log
     */
    void insertLog(Log log);
}
