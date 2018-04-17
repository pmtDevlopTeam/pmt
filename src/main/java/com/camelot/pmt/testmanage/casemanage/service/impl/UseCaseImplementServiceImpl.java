package com.camelot.pmt.testmanage.casemanage.service.impl;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseImplementMapper;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseMapper;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseProcedureImplementMapper;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseImplement;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedureImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.testmanage.casemanage.service.UseCaseImplementService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "useCaseImplementService")
public class UseCaseImplementServiceImpl implements UseCaseImplementService {

    @Autowired
    private UseCaseImplementMapper useCaseImplementMapper;

    @Autowired
    private UseCaseProcedureImplementMapper useCaseProcedureImplementMapper;

    @Autowired
    private UseCaseMapper useCaseMapper;

    @Override
    @Transactional
    public void add(User userModel, UseCaseImplement useCaseImplement) {

        UseCase useCase = null;

        // 设置执行人和执行时间
        if (userModel != null) {
            String id = userModel.getUserId();
            Date date = new Date();
            useCaseImplement.setExecuteUserId(id);
            useCaseImplement.setExecuteTime(date);

            useCase = new UseCase();
            useCase.setId(useCaseImplement.getUseCaseId());
            useCase.setExecuteUserId(id);
            useCase.setExecuteTime(date);

        }

        // 若执行过程有1步失败或者阻塞,则当前执行信息为失败或阻塞
        List<UseCaseProcedureImplement> list = useCaseImplement.getDetail();
        for (UseCaseProcedureImplement useCaseProcedureImplement : list) {
            String testResult = useCaseProcedureImplement.getTestResult();
            if ("阻塞".equals(testResult) || "失败".equals(testResult)) {
                useCaseImplement.setImplementResult(testResult);
                break;
            }
        }
        if (useCaseImplement.getImplementResult() == null) useCaseImplement.setImplementResult("通过");
        useCaseImplementMapper.insertSelective(useCaseImplement);

        // mybatis返回主键
        Long id = useCaseImplement.getId();

        // 设置外键
        for (UseCaseProcedureImplement useCaseProcedureImplement : list) {
            useCaseProcedureImplement.setImplementId(id);
        }

        // 批量插入
        useCaseProcedureImplementMapper.insertBatch(list);

        // 更新测试用例的执行人和执行时间
        if (useCase != null) useCaseMapper.updateByPrimaryKeySelective(useCase);
    }

    @Override
    public List<UseCaseImplement> findByUseCaseId(Long useCaseId) {
        Map<String, Object> param = new HashMap<>();
        param.put("useCaseId", useCaseId);
        return useCaseImplementMapper.find(param);
    }
}
