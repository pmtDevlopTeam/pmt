package com.camelot.pmt.testmanage.casemanage.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.caserepertory.PageBean;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;

public interface UseCaseService {
	
	/**
	 * 新增测试用例
	 *
	 * @param useCase 测试用例
	 */
	void add(User userModel, UseCase useCase);
	
	/**
	 * 根据用例id获取用例
	 * @param id
	 * @return
	 */
	ExecuteResult<UseCase> getUseCaseByUseCaseId (Long id);
	
	/**
	 * 修改用例状态
	 * @param id
	 */
	ExecuteResult<String> updateUserCaseDelFlag(Long id);

	/**
	 * 批量新增
	 *
	 * @param userModel 用户信息
	 * @param list      用例集合
	 */
	void addBatch(User userModel, List<UseCase> list);
	
	/***
	 * 修改用例
	 * @param userModel
	 * @param useCase
	 */
	ExecuteResult<String> edit(User userModel, UseCase useCase);
	
	/**
	 * 用例分页查询
	 * @param pageSize
	 * @param currentPage
	 * @param map
	 * @return
	 */
	List<UseCase> queryAllUserCaseList(Integer pageSize,Integer currentPage,Map<String,Object> map);
}
