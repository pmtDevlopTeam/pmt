package com.camelot.pmt.testmanage.casemanage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseMapper;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseProcedureMapper;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedure;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.github.pagehelper.PageHelper;

@Service
public class UseCaseServiceImpl implements UseCaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UseCaseServiceImpl.class);

    @Autowired
    private UseCaseProcedureMapper useCaseProcedureMapper;

    @Autowired
    UseCaseMapper useCaseMapper;

    /**
     * 
     * 新增用例
     */
    @Override
    @Transactional
    public boolean addUseCase(User userModel, UseCase useCase) {

        // 设置创建人和创建时间
        if (userModel != null) {
            useCase.setCreateUserId(userModel.getUserId());
            useCase.setCreateTime(new Date());
        }

        // 插入
        useCaseMapper.insertSelective(useCase);

        // 返回主键
        Long id = useCase.getId();
        List<UseCaseProcedure> list = useCase.getProcedure();
        for (UseCaseProcedure useCaseProcedure : list) {
            useCaseProcedure.setUseCaseId(id);
        }

        // 批量插入
        return useCaseProcedureMapper.insertBatch(list) == 1 ? true : false;
    }

    /**
     * 
     * 批量新增
     */
    @Override
    public boolean addBatchUseCase(User userModel, List<UseCase> list) {

        // 设置创建人和时间
        String id = null;
        Date date = new Date();
        if (userModel != null)
            id = userModel.getUserId();
        for (UseCase useCase : list) {
            useCase.setCreateUserId(id);
            useCase.setCreateTime(date);
        }

        // 批量插入
        return useCaseMapper.insertBatch(list) == 1 ? true : false;
    }

    /**
     * 
     * 获取用例信息
     */
    public UseCase queryUseCaseByUseCaseId(Long id) {

        UseCase useCase = useCaseMapper.selectByPrimaryKey(id);
        // 获取步骤
        List<UseCaseProcedure> useCaseProcedureList = useCaseProcedureMapper.selectByUseCaseId(useCase.getId());
        if (useCaseProcedureList != null) {
            useCase.setProcedure(useCaseProcedureList);
        }
        return useCase;
    }

    public boolean updateUserCaseDelFlag(Long id) {

        return useCaseMapper.updateUserCaseDelFlag(id) == 1 ? true : false;
    }

    @Override
    @Transactional
    public boolean updateUserCase(User userModel, UseCase useCase) {
        boolean flag = true; // 设置修改人和修改时间时间
        if (userModel != null) {
            useCase.setModifyUserId(userModel.getUserId());
            useCase.setModifyTime(new Date());
        }

        // 修改
        flag = useCaseMapper.updateByPrimaryKeySelective(useCase) == 1 ? true : false;

        List<UseCaseProcedure> list = useCase.getProcedure();
        for (UseCaseProcedure useCaseProcedure : list) {
            if (useCase.getId() != null) {
                useCaseProcedure.setUseCaseId(useCase.getId());
            }
            // 批量修改
            if (useCaseProcedure.getId() == null) {
                // 为空为添加
                flag = useCaseProcedureMapper.insertSelective(useCaseProcedure) == 1 ? true : false;
                ;
            } else {
                // 为空为修改
                flag = useCaseProcedureMapper.updateByPrimaryKeySelective(useCaseProcedure) == 1 ? true : false;
                ;
            }
        }

        return flag;
    }

    /**
     * 用例分页查询
     */
    @Transactional
    public List<UseCase> queryAllUserCaseList(Integer pageSize, Integer currentPage, Map<String, Object> map) {

        PageHelper.startPage(currentPage, pageSize);
        List<UseCase> docs = useCaseMapper.queryAllUserCaseList(map);
        return docs;
    }

}
