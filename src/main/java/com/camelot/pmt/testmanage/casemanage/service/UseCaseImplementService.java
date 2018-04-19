package com.camelot.pmt.testmanage.casemanage.service;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseImplement;

import java.util.List;

/**
 * 用例执行Service
 *
 * @author Yurnero
 */
public interface UseCaseImplementService {

    /**
     * 增加用例执行和用例执行详细信息
     *
     * @param useCaseImplement 用例执行
     */
    void addUseCaseImplement(User userModel, UseCaseImplement useCaseImplement);

    /**
     * 根据测试用例ID查询测试用例执行信息
     *
     * @param useCaseId 测试用例ID
     */
    List<UseCaseImplement> queryUseCaseImplementByUseCaseId(Long useCaseId);

}
