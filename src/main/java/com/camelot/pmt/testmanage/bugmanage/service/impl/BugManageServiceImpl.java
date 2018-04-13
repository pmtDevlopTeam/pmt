package com.camelot.pmt.testmanage.bugmanage.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.task.utils.DateUtils;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugManageMapper;
import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.service.BugManageService;

/**
 *
 * BugManage 表数据服务层接口实现类
 *
 */
@Service
public class BugManageServiceImpl implements BugManageService {
	
private static final Logger LOGGER = LoggerFactory.getLogger(BugManageServiceImpl.class);
	
	@Autowired
	private BugManageMapper bugManageMapper;
	
	@Transactional
	public ExecuteResult<String> save(BugManage bugManage) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		 try {
			 	//判断传入的bug对象是否为空
	            if (bugManage == null) {
	                result.addErrorMessage("传入的bug实体有误!");
	                return result;
	            }
	            //创建时间
	            bugManage.setCreateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
	            //bug默认状态
	            bugManage.setBugStatus("0");
	            //创建人*
	            bugManage.setCreateUserId("1");
	            bugManageMapper.insertSelective(bugManage);
	            result.setResult("创建bug成功!");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}

	@Override
	public ExecuteResult<BugManage> getBugById(Long id) {
		
		 ExecuteResult<BugManage> result = new ExecuteResult<BugManage>();
	        try {
	            if (id!=null) {
	            	BugManage bugManage = bugManageMapper.selectByPrimaryKey(id);
	                result.setResult(bugManage);
	                return result;
	            }
	            result.addErrorMessage("查询失败！");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
		return result;
	}

	@Transactional
	public ExecuteResult<String> edit(BugManage bugManage) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		 try {
			 	//判断传入的bug对象是否为空
	            if (bugManage == null) {
	                result.addErrorMessage("传入的bug实体有误!");
	                return result;
	            }
	            //更新时间
	            bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
	            //更新人*
	            bugManage.setModifyUserId("1");
	           
	            //解决方案
	            Integer solveProgram = bugManage.getSolveProgram();
	            //解决日期
	            String solveTime = bugManage.getSolveTime();
	            //解决人
            	String solveId = bugManage.getSolveId();
            	//关闭人
            	String closeId = bugManage.getCloseId();
            	//关闭时间
            	String closeTime = bugManage.getCloseTime();
            	
            	//如果解决人解决日期解决方案关闭人关闭日期都未填写
            	if(StringUtils.isEmpty(solveId)&&StringUtils.isEmpty(solveTime)&&solveProgram==null&&StringUtils.isEmpty(closeId)&&StringUtils.isEmpty(closeTime)){
            		//修改bug状态无变化 为激活状态 
            		bugManage.setBugStatus("0");
            	}
            	
            	
            	//如果只填写解决人关闭人关闭日期都未填写
            	if(!StringUtils.isEmpty(solveId)&&StringUtils.isEmpty(closeId)&&StringUtils.isEmpty(closeTime)){
            		//如果解决方案未填写
            		if(solveProgram==null){
            			result.addErrorMessage("解决方案不能为空!");
            			return result;
            		}else{
            			//如果解决日期未填写
            			if(StringUtils.isEmpty(solveTime)){
            				bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            				//bug状态为已解决状态
            				bugManage.setBugStatus("1");
            			}
            		}
            		
            	}
            	//如果只填写解决日期，未填写关闭人关闭时间情况下
            	if(!StringUtils.isEmpty(solveTime)&&StringUtils.isEmpty(closeId)&&StringUtils.isEmpty(closeTime)){
            		//bug状态为已解决
            		bugManage.setBugStatus("1");
            	}
            	
	          
            	//如果填写了解决方案 但是解决者解决日期关闭人和关闭日期都未填写的情况下 
            	if(solveProgram!=null&&StringUtils.isEmpty(closeId)&&StringUtils.isEmpty(closeTime)){
            		//如果没有填写解决人*
            		if(StringUtils.isEmpty(solveId)){
            			//解决者为当前操作用户，解决日期为当前时间 
            			bugManage.setSolveId("1");
            		}
            		//如果没有填写解决日期
            		if(StringUtils.isEmpty(solveTime)){
            			bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            		}
            		//bug状态为已解决
            		bugManage.setBugStatus("1");
            	}
	            
	            //如果只填写关闭人情况下
            	if(!StringUtils.isEmpty(closeId)){
            		//如果解决方案未填写
            		if(solveProgram==null){
            			result.addErrorMessage("解决方案不能为空!");
            			return result;
            		}else{
            			//如果关闭日期未填写
            			if(StringUtils.isEmpty(closeTime)){
            				bugManage.setCloseTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            			}
            			//如果解决者未填写*
            			if(StringUtils.isEmpty(solveId)){
            				//默认为当前操作用户
            				bugManage.setSolveId("1");
            			}
            			//如果解决日期未填写
            			if(StringUtils.isEmpty(solveTime)){
            				bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            			}
            			//bug状态为已关闭
                		bugManage.setBugStatus("2");
            		}
            	}
            	//只填写了关闭日期的情况下
            	if(!StringUtils.isEmpty(closeTime)){
            		//如果解决方案未填写
            		if(solveProgram==null){
            			result.addErrorMessage("解决方案不能为空!");
            			return result;
            		}else{
            			//如果解决者未填写*
            			if(StringUtils.isEmpty(solveId)){
            				//默认为当前操作用户
            				bugManage.setSolveId("1");
            			}
            			//如果关闭人未填写*
            			if(StringUtils.isEmpty(closeId)){
            				//默认为当前操作用户*
            				bugManage.setCloseId("1");
            			}
            			//如果解决日期未填写
            			if(StringUtils.isEmpty(solveTime)){
            				bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            			}
            			//bug状态为已关闭
                		bugManage.setBugStatus("2");
            		}
            		
            	}
            	
	            bugManageMapper.updateByPrimaryKeySelective(bugManage);
	            result.setResult("修改bug成功!");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}
}