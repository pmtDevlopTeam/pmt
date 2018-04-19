package com.camelot.pmt.caserepertory.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.caserepertory.model.CaseRepertory;
import com.camelot.pmt.common.ExecuteResult;
import com.github.pagehelper.PageInfo;

public interface CaseRepertoryService {

	ExecuteResult<PageInfo> selectCondition(Map<String, Object> map);

	ExecuteResult<String> addCaseRepertoryByCaseid(String ids);

	ExecuteResult<String> addUserCaseByCaseRepertoryid(String ids);

	/**
	 * 根据ID查询用例和用例步骤
	 *
	 * @param id 用例库ID
	 * @return 返回用例库
	 */
	CaseRepertory getById(Long id);

	/**
	 * 新增测试用例库
	 *
	 * @param caseRepertory 用例库
	 */
	void add(CaseRepertory caseRepertory);

	/**
	 * 批量新增测试用例库
	 *
	 * @param list 用例库集合
	 */
	void addBatch(List<CaseRepertory> list);

	/**
	 * 更新测试用例库
	 *
	 * @param caseRepertory 用例库
	 */
	void update(CaseRepertory caseRepertory);

	/**
	 * 删除用例库
	 *
	 * @param ids 用例库id 逗号隔开
	 */
	void remove(String ids);

}
