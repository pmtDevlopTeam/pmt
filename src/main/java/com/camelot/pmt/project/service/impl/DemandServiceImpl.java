package com.camelot.pmt.project.service.impl;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.mapper.DemandMapper;
import com.camelot.pmt.project.model.DemandWithBLOBs;
import com.camelot.pmt.project.service.DemandService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemandServiceImpl implements DemandService {
 private static final Logger logger=LoggerFactory.getLogger(DemandServiceImpl.class);
    @Resource
    DemandMapper demandMapper;
    @Override
    public ExecuteResult save(DemandWithBLOBs demandWithBLOBs) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            if (demandWithBLOBs == null) {
                result.setResultMessage("需求数据不能为空");
            }
            int count = demandMapper.insert(demandWithBLOBs);
        } catch (Exception e) {

        }
        return null;
    }
}
