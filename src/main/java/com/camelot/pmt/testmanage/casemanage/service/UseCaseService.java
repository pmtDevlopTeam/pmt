package com.camelot.pmt.testmanage.casemanage.service;

import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.util.ActionBean;
import com.github.pagehelper.PageInfo;

public interface UseCaseService {
	
	public PageInfo<UseCase> selectUseCase(PageBean pageBean);

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
}
