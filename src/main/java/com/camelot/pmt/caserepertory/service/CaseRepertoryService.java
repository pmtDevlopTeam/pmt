package com.camelot.pmt.caserepertory.service;

import java.util.List;
import com.camelot.pmt.caserepertory.model.CaseRepertory;


/**
 * 用例库Service
 *
 * @author Yurnero
 */
public interface CaseRepertoryService {

	boolean addCaseRepertoryByCaseid(String ids);

	boolean addUserCaseByCaseRepertoryid(String ids);


	/**
	 * 新增测试用例库
	 *
	 * @param caseRepertory 用例库
	 */
	boolean addCaseRepertory(CaseRepertory caseRepertory);

	/**
	 * 批量新增测试用例库
	 *
	 * @param list 用例库集合
	 */
	boolean addBatchCaseRepertory(List<CaseRepertory> list);

	/**
	 * 删除用例库
	 *
	 * @param ids 用例库id 逗号隔开
	 */
	boolean deleteCaseRepertory(String ids);

	/**
	 * 更新测试用例库
	 *
	 * @param caseRepertory 用例库
	 */
	boolean updateCaseRepertory(CaseRepertory caseRepertory);


	/**
	 * 根据ID查询用例和用例步骤
	 *
	 * @param id 用例库ID
	 * @return 返回用例库
	 */
	CaseRepertory queryCaseRepertoryById(Long id);

	List<CaseRepertory> queryCaseRepertoryByPage(CaseRepertory caseRepertory, Integer pageSize,  Integer currentPage);
}
