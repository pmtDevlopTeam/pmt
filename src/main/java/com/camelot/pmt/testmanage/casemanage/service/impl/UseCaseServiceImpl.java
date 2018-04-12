package com.camelot.pmt.testmanage.casemanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseMapper;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.github.pagehelper.Page;

@Service
public class UseCaseServiceImpl implements UseCaseService{
	
	
	@Autowired
	UseCaseMapper useCaseMapper;
	
	public void selectUseCase(PageBean pageBean){
		UseCase UseCase=useCaseMapper.selectByPrimaryKey(1L);
		Page<UseCase> page = new Page<UseCase>(pageBean.getCurrentPage(),
				pageBean.getPageSize());
	        List<UseCase> list = useCaseMapper.selectUseCase(page, pageBean.getCondition());
	        pageBean.setTotalNum(new Long(page.getTotal()).intValue());
	        pageBean.setItems(list);
	}
}
