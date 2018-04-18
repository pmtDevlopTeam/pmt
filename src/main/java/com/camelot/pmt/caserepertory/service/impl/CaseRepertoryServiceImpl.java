package com.camelot.pmt.caserepertory.service.impl;

import com.camelot.pmt.caserepertory.PageBean;
import com.camelot.pmt.caserepertory.mapper.CaseRepertoryMapper;
import com.camelot.pmt.caserepertory.mapper.CaseRepertoryStepMapper;
import com.camelot.pmt.caserepertory.model.CaseRepertory;
import com.camelot.pmt.caserepertory.model.CaseRepertoryStep;
import com.camelot.pmt.caserepertory.service.CaseRepertoryService;
import com.camelot.pmt.common.ExecuteResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用例库ServiceImpl
 *
 * @author Yurnero
 */
@Service
public class CaseRepertoryServiceImpl implements CaseRepertoryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseRepertoryServiceImpl.class);
	@Autowired
	private CaseRepertoryMapper caseRepertoryMapper;

	@Autowired
	private CaseRepertoryStepMapper caseRepertoryStepMapper;


	@Override
	@Transactional
	public ExecuteResult<String> addCaseRepertoryByCaseid(String ids) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			String[] params=ids.split(",");
			for (int i = 0; i < params.length; i++) {
				String param= params[i].toString().trim();
				long param1 =Long.parseLong(param);
				CaseRepertory caseRepertory=new CaseRepertory();
				caseRepertory.setId(param1);
				//复制数据到用例库主表并返回主键id
				caseRepertoryMapper.addCaseRepertoryByCaseid(caseRepertory);
				//复制步骤数到用例库步骤表中并且更新父id为 result1
				long id=caseRepertory.getId();
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("param1", param1);
				map.put("result1", id);
				int result2 = caseRepertoryMapper.addCaseRepertoryStepByCaseid(map);
			}
			result.setResult("成功");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	@Transactional
	public ExecuteResult<String> addUserCaseByCaseRepertoryid(String ids) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			String[] params=ids.split(",");
			for (int i = 0; i < params.length; i++) {
				String param= params[i].toString().trim();
				long param1 =Long.parseLong(param);
				CaseRepertory caseRepertory=new CaseRepertory();
				caseRepertory.setId(param1);
				//复制数据到用例主表并返回主键id
				caseRepertoryMapper.addUserCaseByCaseRepertoryid(caseRepertory);
				//复制步骤数到用例步骤表中并且更新父id为 result1
				long id=caseRepertory.getId();
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("param1", param1);
				map.put("result1", id);
				caseRepertoryMapper.addUserCaseStepByCaseRepertoryid(map);
			}
			result.setResult("成功");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 新增测试用例库
	 *
	 * @param caseRepertory 用例库
	 */
	@Override
	@Transactional
	public void addCaseRepertory(CaseRepertory caseRepertory) {
		// 新增用例
		caseRepertoryMapper.insertSelective(caseRepertory);

		// mybatis返回主键,并设置外键
		Long id = caseRepertory.getId();
		List<CaseRepertoryStep> list = caseRepertory.getDetail();
		for (CaseRepertoryStep caseRepertoryStep : list) {
			caseRepertoryStep.setUseCaseId(id);
		}

		// 批量新增用例步骤
		caseRepertoryStepMapper.insertBatch(list);

	}

	/**
	 * 批量新增测试用例库
	 *
	 * @param list 用例库集合
	 */
	@Override
	@Transactional
	public void addBatchCaseRepertory(List<CaseRepertory> list) {
		caseRepertoryMapper.insertBatch(list);
	}

	/**
	 * 删除用例库
	 *
	 * @param ids 用例库id 逗号隔开
	 */
	@Override
	@Transactional
	public void deleteCaseRepertory(String ids) {
		Map<String, Object> param = new HashMap<>();

		String[] arr = ids.split(",");
		int[] intArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			intArr[i] = Integer.parseInt(arr[i]);
		}

		// 删除用例
		param.put("ids", intArr);
		caseRepertoryMapper.remove(param);

		// 删除用例步骤
		param.clear();
		param.put("useCaseIds", intArr);
		caseRepertoryStepMapper.remove(param);
	}

	/**
	 * 更新测试用例库
	 *
	 * @param caseRepertory 用例库
	 */
	@Override
	@Transactional
	public void updateCaseRepertory(CaseRepertory caseRepertory) {
		// 更新用例库
		caseRepertoryMapper.updateByPrimaryKeySelective(caseRepertory);

		// 过滤出删除的步骤
		List<Long> ids = new ArrayList<>();
		Long id = caseRepertory.getId();
		List<CaseRepertoryStep> oldList = caseRepertoryStepMapper.findByUseCaseId(id);
		List<CaseRepertoryStep> list = caseRepertory.getDetail();
		for (CaseRepertoryStep caseRepertoryStep1 : oldList) {
			Long oldId = caseRepertoryStep1.getId();
			boolean flag = false;
			for (CaseRepertoryStep caseRepertoryStep2 : list) {
				if (oldId.equals(caseRepertoryStep2.getId())) {
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
			if (flag) {
				ids.add(oldId);
			}
		}

		// 更新用例步骤,若用例步骤ID不为null,则更新步骤,反之插入步骤
		for (CaseRepertoryStep caseRepertoryStep : list) {
			if (caseRepertoryStep.getId() != null) {
				caseRepertoryStepMapper.updateByPrimaryKeySelective(caseRepertoryStep);
			} else {
				caseRepertoryStep.setUseCaseId(id);
				caseRepertoryStepMapper.insertSelective(caseRepertoryStep);
			}
		}

		// 删除不存在的步骤
		if (ids.size() != 0) {
			Map<String, Object> param = new HashMap<>();
			param.put("ids", ids);
			caseRepertoryStepMapper.remove(param);
		}
	}

	@Override
	public ExecuteResult<PageInfo> selectCondition(Map<String, Object> map) {
		ExecuteResult<PageInfo> result = new ExecuteResult<PageInfo>();
		try {
			PageBean pageBean = (PageBean) map.get("pageBean");
			if (pageBean == null) {
				result.setResultMessage("传入实体有误!");
				return result;
			}
			PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
			List<CaseRepertory> docs = caseRepertoryMapper.selectCondition(map);
			PageInfo<CaseRepertory> pageInfo = new PageInfo<CaseRepertory>(docs);
			result.setResult(pageInfo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据ID查询用例和用例步骤
	 *
	 * @param id 用例库ID
	 * @return 返回用例库
	 */
	@Override
	public CaseRepertory queryCaseRepertoryById(Long id) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		List<CaseRepertory> list = caseRepertoryMapper.find(param);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
}
