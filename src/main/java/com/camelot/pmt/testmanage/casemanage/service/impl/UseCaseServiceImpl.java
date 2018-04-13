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
		UseCase seCase=useCaseMapper.selectByPrimaryKey(1L);
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
		/* PageInfo<UseCase> pageInfo = new PageInfo<UseCase>(seCase,5);
		PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
		  List<LytBbsTz> publishTz = bbsTzDao.getPublishTz(userId);
		  PageInfo<LytBbsTz> info = new PageInfo<LytBbsTz>(publishTz);
		UseCase UseCase=useCaseMapper.selectByPrimaryKey(1L);
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());*/
        List<UseCase> docs = useCaseMapper.selectUseCase(new HashMap<String, Object>());
        PageInfo<UseCase> pageInfo = new PageInfo<UseCase>(docs);
        
        return pageInfo;
      /*  PageInfo<Doc> pageInfo = new PageInfo<>(docs);
		Page<UseCase> page = new Page<UseCase>(pageBean.getCurrentPage(),
				pageBean.getPageSize());
	        List<UseCase> list = useCaseMapper.selectUseCase(page, pageBean.getCondition());
	        pageBean.setTotalNum(new Long(page.getTotal()).intValue());
	        pageBean.setItems(list);*/
	}
}
