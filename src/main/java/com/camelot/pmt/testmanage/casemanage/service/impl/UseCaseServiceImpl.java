package com.camelot.pmt.testmanage.casemanage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseProcedureMapper;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseMapper;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UseCaseServiceImpl implements UseCaseService{

	@Autowired
	private UseCaseProcedureMapper useCaseProcedureMapper;
	
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

	@Override
	@Transactional
	public void add(UserModel userModel, UseCase useCase) {

		// 设置创建人和创建时间
		if (userModel != null) {
			useCase.setCreateUserId(userModel.getId());
			useCase.setCreateTime(new Date());
		}

		// 插入
		useCaseMapper.insertSelective(useCase);

		// 返回主键
		Long id = useCase.getId();
		List<UseCaseProcedure> list = useCase.getProcedure();
		for (UseCaseProcedure useCaseProcedure : list) {
			useCaseProcedure.setUseCaseId(id);
		}

		// 批量插入
		useCaseProcedureMapper.insertBatch(list);
	}
}
