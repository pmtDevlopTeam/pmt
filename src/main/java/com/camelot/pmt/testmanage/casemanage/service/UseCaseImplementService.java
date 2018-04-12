package com.camelot.pmt.testmanage.casemanage.service;

import com.camelot.pmt.platform.user.model.UserModel;
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
    void add(UserModel userModel, UseCaseImplement useCaseImplement);

    List<UseCaseImplement> findByUseCaseId(Long useCaseId);

}
