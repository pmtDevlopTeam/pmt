package com.camelot.pmt.caserepertory.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.camelot.pmt.caserepertory.mapper.CaseRepertoryStepMapper;
import com.camelot.pmt.caserepertory.model.CaseRepertory;
import com.camelot.pmt.caserepertory.model.CaseRepertoryStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.caserepertory.mapper.CaseRepertoryMapper;
import com.camelot.pmt.caserepertory.service.CaseRepertoryService;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugHistoryMapper;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;
import com.camelot.pmt.testmanage.bugmanage.service.impl.BugManageServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class CaseRepertoryServiceImpl implements CaseRepertoryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseRepertoryServiceImpl.class);
	@Autowired
	private CaseRepertoryMapper caseRepertoryMapper;

	@Autowired
	private CaseRepertoryStepMapper caseRepertoryStepMapper;
    
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
	            List<SelectBugManage> docs = caseRepertoryMapper.selectCondition(map);
	            PageInfo<SelectBugManage> pageInfo = new PageInfo<SelectBugManage>(docs);
	            result.setResult(pageInfo);
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}

	@Override
	public CaseRepertory getById(Long id) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		List<CaseRepertory> list = caseRepertoryMapper.find(param);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void add(CaseRepertory caseRepertory) {
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

	@Override
	public void addBatch(List<CaseRepertory> list) {
		caseRepertoryMapper.insertBatch(list);
	}

	@Override
	public void update(CaseRepertory caseRepertory) {
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
	public void remove(String ids) {
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
}
