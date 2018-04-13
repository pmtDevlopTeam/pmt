package com.camelot.pmt.testmanage.casemanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseMapper;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UseCaseServiceImpl implements UseCaseService{
	
	@Bean
	public PageHelper pageHelper(){
	      PageHelper pageHelper = new PageHelper();
	      Properties properties = new Properties();
	      properties.setProperty("offsetAsPageNum","true");
	      properties.setProperty("rowBoundsWithCount","true");
	      properties.setProperty("reasonable","true");
	      properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
	     pageHelper.setProperties(properties);
	     return pageHelper;
	}
	
	@Autowired
	UseCaseMapper useCaseMapper;
	
	public PageInfo<UseCase> selectUseCase(PageBean pageBean){
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
        List<UseCase> docs = useCaseMapper.selectUseCase(new HashMap<String, Object>());
        PageInfo<UseCase> pageInfo = new PageInfo<UseCase>(docs);
        return pageInfo;
	}
}
