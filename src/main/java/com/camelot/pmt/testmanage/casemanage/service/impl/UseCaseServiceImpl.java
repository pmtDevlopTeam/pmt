package com.camelot.pmt.testmanage.casemanage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.camelot.pmt.platform.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.caserepertory.PageBean;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseMapper;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseProcedureMapper;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedure;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UseCaseServiceImpl implements UseCaseService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UseCaseServiceImpl.class);

	@Autowired
	private UseCaseProcedureMapper useCaseProcedureMapper;
	
	
	@Autowired
	UseCaseMapper useCaseMapper;
	
	/**
	 * 
	 * 获取用例信息
	 */
	public  ExecuteResult<UseCase> getUseCaseByUseCaseId (Long id){
		
		  ExecuteResult<UseCase> result = new ExecuteResult<UseCase>();
			 try {
				 UseCase useCase=useCaseMapper.selectByPrimaryKey(id);
					//获取步骤
					List<UseCaseProcedure> useCaseProcedureList=useCaseProcedureMapper.selectByUseCaseId(useCase.getId());
					if(useCaseProcedureList!=null){
						useCase.setProcedure(useCaseProcedureList);
					}
					result.setResult(useCase);
		        } catch (Exception e) {
		            LOGGER.error(e.getMessage());
		            throw new RuntimeException(e);
		        }
		        return result;
	}
	
	public boolean updateUserCaseDelFlag(Long id){
		
	      return useCaseMapper.updateUserCaseDelFlag(id)==1?true:false;
	}
	@Override
	@Transactional
	public void add(User userModel, UseCase useCase) {

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
	@Transactional
	public ExecuteResult<String> edit(User userModel, UseCase useCase) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		 try {
			 	//判断传入的bug对象是否为空
			// 设置修改人和修改时间时间
				if (userModel != null) {
					useCase.setModifyUserId(userModel.getUserId());
					useCase.setModifyTime(new Date());
				}

				// 修改
				useCaseMapper.updateByPrimaryKeySelective(useCase);

				List<UseCaseProcedure> list = useCase.getProcedure();
				for (UseCaseProcedure useCaseProcedure : list) {
					if(useCase.getId()!=null){
						useCaseProcedure.setUseCaseId(useCase.getId());
					}
					//批量修改
					if(useCaseProcedure.getId()==null){
						//为空为添加
						useCaseProcedureMapper.insertSelective(useCaseProcedure);
					}else{
						//为空为修改
						useCaseProcedureMapper.updateByPrimaryKeySelective(useCaseProcedure);
					}
				}
	            result.setResult("编辑成功!");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}

	@Override
	public void addBatch(User userModel, List<UseCase> list) {

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
	
	/**
	 * 用例分页查询
	 */
	@Transactional
	public  List<UseCase> queryAllUserCaseList(Integer pageSize,Integer currentPage,Map<String,Object> map){
        
            PageHelper.startPage(currentPage, pageSize);
            List<UseCase> docs = useCaseMapper.queryAllUserCaseList(map);
	      return docs;
	}
	
}
