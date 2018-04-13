package com.camelot.pmt.testmanage.casemanage.service.impl;

import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseMapper;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseProcedureMapper;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedure;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UseCaseServiceImpl implements UseCaseService{

	@Autowired
	private UseCaseProcedureMapper useCaseProcedureMapper;
	
	
	@Autowired
	UseCaseMapper useCaseMapper;
	
	@Transactional
	public PageInfo<UseCase> selectUseCase(PageBean pageBean){
		PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
        List<UseCase> docs = useCaseMapper.selectUseCase(new HashMap<String, Object>());
        PageInfo<UseCase> pageInfo = new PageInfo<UseCase>(docs);
        return pageInfo;
	}
	
	/**
	 * 
	 * 获取用例信息
	 */
	public UseCase getUseCaseByUseCaseId (long id){
		
		UseCase useCase=useCaseMapper.selectByPrimaryKey(id);
		//获取步骤
		List<UseCaseProcedure> useCaseProcedureList=useCaseProcedureMapper.selectByUseCaseId(useCase.getId());
		if(useCaseProcedureList!=null){
			useCase.setProcedure(useCaseProcedureList);
		}
		return useCaseMapper.selectByPrimaryKey(id);
	}
	
	public void updateUserCaseDelFlag(long id){
		useCaseMapper.updateUserCaseDelFlag(id);
	}
	@Override
	@Transactional
	public void add(UserModel userModel, UseCase useCase) {

		// 设置创建人和创建时间
		if (userModel != null) {
			useCase.setCreateUserId(userModel.getUserId());
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

	@Override
	public void addBatch(UserModel userModel, List<UseCase> list) {

		// 设置创建人和时间
		String id = null;
		Date date = new Date();
		if (userModel != null) id = userModel.getUserId();
		for (UseCase useCase : list) {
			useCase.setCreateUserId(id);
			useCase.setCreateTime(date);
		}

		// 批量插入
		useCaseMapper.insertBatch(list);
	}
}
