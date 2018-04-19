package com.camelot.pmt.testmanage.casemanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseProcedureMapper;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseProcedureService;

@Service
public class UseCaseProcedureServiceImpl implements UseCaseProcedureService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UseCaseProcedureServiceImpl.class);
    @Autowired
    UseCaseProcedureMapper useCaseProcedureMapper;

    public ExecuteResult<String> deleteByPrimaryKey(Long id) {

        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 判断传入的bug对象是否为空
            if (id == null) {
                result.addErrorMessage("传入参数错误");
                return result;
            }
            useCaseProcedureMapper.deleteByPrimaryKey(id);
            result.setResult("删除成功!");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
