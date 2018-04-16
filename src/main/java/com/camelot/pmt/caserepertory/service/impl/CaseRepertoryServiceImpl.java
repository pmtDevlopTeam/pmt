package com.camelot.pmt.caserepertory.service.impl;

import java.util.List;
import java.util.Map;

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
}
