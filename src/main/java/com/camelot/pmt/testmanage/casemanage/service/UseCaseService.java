package com.camelot.pmt.testmanage.casemanage.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.github.pagehelper.PageInfo;

public interface UseCaseService {
	
	ExecuteResult<PageInfo> selectUseCase(PageBean pageBean,Map<String,Object> map);

	/**
	 * 新增测试用例
	 *
	 * @param useCase 测试用例
	 */
	void add(UserModel userModel, UseCase useCase);
	
	/**
	 * 根据用例id获取用例
	 * @param id
	 * @return
	 */
	UseCase getUseCaseByUseCaseId (long id);
	
	/**
	 * 修改用例状态
	 * @param id
	 */
	void updateUserCaseDelFlag(long id);

	/**
	 * 批量新增
	 *
	 * @param userModel 用户信息
	 * @param list      用例集合
	 */
	void addBatch(UserModel userModel, List<UseCase> list);
	
	/***
	 * 修改用例
	 * @param userModel
	 * @param useCase
	 */
	 void edit(UserModel userModel, UseCase useCase);
}
