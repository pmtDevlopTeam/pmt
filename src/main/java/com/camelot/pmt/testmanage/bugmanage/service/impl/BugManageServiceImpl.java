package com.camelot.pmt.testmanage.bugmanage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.platform.user.mapper.UserMapper;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.task.utils.DateUtils;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugHistoryMapper;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugManageMapper;
import com.camelot.pmt.testmanage.bugmanage.model.BugHistory;
import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;
import com.camelot.pmt.testmanage.bugmanage.service.BugManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BugHistoryMapper bugHistoryMapper;
	
	/**
	 * 添加bug
	 */
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
	            //bug默认状态 未确认
	            bugManage.setBugStatus("0");
	            //创建人*
	            bugManage.setCreateUserId("1");
	            bugManageMapper.insertSelective(bugManage);
	            selectStatus(bugManage,"1","添加");
	            result.setResult("创建bug成功!");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}

	/**
	 * 根据id查询bug
	 */
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

	/**
	 * 编辑bug
	 */
	@Transactional
	public ExecuteResult<String> edit(BugManage bugManage) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		 try {
			 	//判断传入的bug对象是否为空
	            if (bugManage == null || bugManage.getId()==null) {
	                result.addErrorMessage("传入的bug实体有误!");
	                return result;
	            }
	            BugManage currentBugManage = bugManageMapper.selectByPrimaryKey(bugManage.getId());
	            if(currentBugManage.getDesignatedId()!=null){
	            	 result.addErrorMessage("bug已经被指派，不可编辑");
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
            	
               selectStatus(bugManage,"1","编辑");
	           bugManageMapper.updateByPrimaryKeySelective(bugManage);
	            result.setResult("修改bug成功!");
	        } catch (Exception e) {
	        	e.printStackTrace();
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}

	

	/**
	 * 撤销bug
	 */
	@Transactional
	public ExecuteResult<String> updateBugStatusRevoke(BugManage bugManage) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		 try {
			 	//判断传入的bug对象是否为空
	            if (bugManage==null||bugManage.getId()==null) {
	                result.addErrorMessage("传入bugId错误");
	                return result;
	            }
	            //更新时间
	            bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
	            //更新人*
	            bugManage.setModifyUserId("1");
	            selectStatus(bugManage,"1","编辑");
	            bugManageMapper.updateBugStatusRevoke(bugManage);
	            result.setResult("撤销bug成功!");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}

	/**
	 * 关闭bug
	 */
	@Transactional
	public ExecuteResult<String> updateBugStatusClose(BugManage bugManage) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		 try {
			 	//判断传入的bug对象是否为空
	            if (bugManage==null||bugManage.getId()==null) {
	                result.addErrorMessage("传入参数错误");
	                return result;
	            }
	            //更新时间
	            bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
	            //更新人*
	            bugManage.setModifyUserId("1");
	            //关闭时间
	            bugManage.setCloseTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
	            //关闭人*
	            bugManage.setCloseId("1");
	            selectStatus(bugManage,"1","编辑");
	            bugManageMapper.updateBugStatusClose(bugManage);
	            result.setResult("关闭bug成功!");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}

	/**
	 * 确认bug
	 */
	@Transactional
	public ExecuteResult<String> updateBugStatusYes(BugManage bugManage) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		 try {
			 	//判断传入的bug对象是否为空
	            if (bugManage==null||bugManage.getId()==null) {
	                result.addErrorMessage("传入参数错误");
	                return result;
	            }
	            //更新时间
	            bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
	            //更新人*
	            bugManage.setModifyUserId("1");
	            selectStatus(bugManage,"1","编辑");
	            bugManageMapper.updateBugStatusYes(bugManage);
	            result.setResult("确认bug成功!");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}

	/**
	 * 指派bug
	 */
	@Transactional
	public ExecuteResult<String> updateBugAssign(BugManage bugManage) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		 try {
			 	//判断传入的bug对象是否为空
	            if (bugManage==null||bugManage.getId()==null) {
	                result.addErrorMessage("传入参数错误");
	                return result;
	            }
	            //更新时间
	            bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
	            //更新人*
	            bugManage.setModifyUserId("1");
	            selectStatus(bugManage,"1","编辑");
	            bugManageMapper.updateBugAssign(bugManage);
	            result.setResult("指派bug成功!");
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
	        return result;
	}
	
	/**
	 * 解决bug
	 */
	@Transactional
	public ExecuteResult<String> updateBugSolve(BugManage bugManage) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			//判断传入的bug对象是否为空
			if (bugManage==null||bugManage.getId()==null) {
				result.addErrorMessage("传入参数错误");
				return result;
			}
            //更新时间
            bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            //更新人*
            bugManage.setModifyUserId("1");
            //解决人
            bugManage.setSolveId("1");
			//如果没有填写解决日期 默认添加为当前时间
			if(StringUtils.isEmpty(bugManage.getSolveTime())){
				bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
			}
			//解决者*
			bugManage.setSolveId("1");
			selectStatus(bugManage,"1","编辑");
			bugManageMapper.updateBugSolve(bugManage);
			result.setResult("解决bug成功!");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public ExecuteResult<PageInfo> selectCondition(Map<String, Object> map) {
		
		  ExecuteResult<PageInfo> result = new ExecuteResult<PageInfo>();
			 try {
				 PageBean pageBean = (PageBean) map.get("pageBean");
				 	//判断传入的bug对象是否为空
		            if (pageBean == null) {
		                result.setResultMessage("传入实体有误!");
		                return result;
		            }
		            PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
		            List<SelectBugManage> docs = bugManageMapper.selectCondition(map);
		            PageInfo<SelectBugManage> pageInfo = new PageInfo<SelectBugManage>(docs);
		            //result.setResult("用例查询成功!");
		            result.setResult(pageInfo);
		        } catch (Exception e) {
		            LOGGER.error(e.getMessage());
		            throw new RuntimeException(e);
		        }
		        return result;
	}
	
	private String getStatus(BugManage bug) {
		String bugStatus=bug.getBugStatus();
		if("0".equals(bugStatus)){
			return "未确认"; 
		}
		if("1".equals(bugStatus)){
			return "已确认"; 
		}
		if("2".equals(bugStatus)){
			return "已解决"; 
		}
		if("3".equals(bugStatus)){
			return "已关闭"; 
		}
		if("4".equals(bugStatus)){
			return "已撤销"; 
		}
		return null;
	}
	
	public void selectStatus(BugManage bugManage,String userId,String operation){
		 StringBuilder operationRecord=new StringBuilder();
		 BugHistory bugHistory=new BugHistory();
         BugManage bug = bugManageMapper.selectByPrimaryKey(bugManage.getId());
         if(!"添加".equals(operation)){
        	  if(bugManage.getBugTitle()!=null){
               	if(bug.getBugTitle()!=bugManage.getBugTitle()){
               		operationRecord.append("修改了 bug标题 ,旧值为'"+bug.getBugTitle()+"' , 新值为'"+bugManage.getBugTitle()+"'。");
               	}
               }
               if(bugManage.getStepsReproduce()!=null){
               	if(bug.getStepsReproduce()!=bugManage.getStepsReproduce()){
               		operationRecord.append("修改了 重现步骤 ,旧值为'"+bug.getStepsReproduce()+"' , 新值为'"+bugManage.getStepsReproduce()+"'。");
               	}
               }
               if(bugManage.getBugDescribe()!=null){
               	if(bug.getBugDescribe()!=bugManage.getBugDescribe()){
               		operationRecord.append("修改了 备注 ,旧值为'"+bug.getBugDescribe()+"' , 新值为'"+bugManage.getBugDescribe()+"'。");
               	}
               }
               if(bugManage.getDesignatedId()!=null){
               	if(bug.getDesignatedId()!=bugManage.getDesignatedId()){
               		String username = userMapper.selectUserById(bug.getDesignatedId()).getUsername();
               		String newusername = userMapper.selectUserById(bug.getDesignatedId()).getUsername();
               		operationRecord.append("修改了 指派人 ,旧值为'"+username+"' , 新值为'"+newusername+"'。");
               	}
               }
               if(bugManage.getVersionId()!=null){
               	if(bug.getVersionId()!=bugManage.getVersionId()){
               		operationRecord.append("修改了 版本 ,旧值为'"+bug.getVersionId()+"' , 新值为'"+bugManage.getVersionId()+"'。");
               	}
               }
               if(bugManage.getBugType()!=null){
               	if(bug.getBugType()!=bugManage.getBugType()){
               		operationRecord.append("修改了 bug类型 ,旧值为'"+bug.getBugType()+"' , 新值为'"+bugManage.getBugType()+"'。");
               	}
               }
               if(bugManage.getSeriousDegree()!=null){
               	if(bug.getSeriousDegree()!=bugManage.getSeriousDegree()){
               		operationRecord.append("修改了 bug严重程度 ,旧值为'"+bug.getSeriousDegree()+"' , 新值为'"+bugManage.getSeriousDegree()+"'。");
               	}
               	
               }
               if(bugManage.getBugLevel()!=null){
               	if(!bugManage.getBugLevel().equals(bug.getBugLevel())){
               		operationRecord.append("修改了 bug优先级  ,旧值为'"+bug.getBugLevel()+"' , 新值为'"+bugManage.getBugLevel()+"'。");
               	}
               }
               if(bugManage.getBugStatus()!=null){
               	if(!bugManage.getBugStatus().equals(bug.getBugStatus())){
               		String status=getStatus(bug);
               		String newStatus=getStatus(bugManage);
               		operationRecord.append("修改了 bug状态 ,旧值为'"+status+"' , 新值为'"+newStatus+"'。");
               	}
               }
               if(bugManage.getEndTime()!=null){
               	if(bug.getEndTime()!=bugManage.getEndTime()){
               		operationRecord.append("修改了 截止日期  ,旧值为'"+bug.getEndTime()+"' , 新值为'"+bugManage.getEndTime()+"'。");
               	}
               }
               if(bugManage.getCaseEnvironment()!=null){
               	if(bug.getCaseEnvironment()!=bugManage.getCaseEnvironment()){
               		operationRecord.append("修改了 测试环境  ,旧值为'"+bug.getCaseEnvironment()+"' , 新值为'"+bugManage.getCaseEnvironment()+"'。");
               	}
               }
               if(bugManage.getCaseTerminal()!=null){
               	if(bug.getCaseTerminal()!=bugManage.getCaseTerminal()){
               		operationRecord.append("修改了 测试终端  ,旧值为'"+bug.getCaseTerminal()+"' , 新值为'"+bugManage.getCaseTerminal()+"'。");
               	}
               }
               if(bugManage.getProjectId()!=null){
               	if(bug.getProjectId()!=bugManage.getProjectId()){
               		operationRecord.append("修改了 所属项目 ,旧值为'"+bug.getProjectId()+"' , 新值为'"+bugManage.getProjectId()+"'。");
               	}
               }
               if(bugManage.getDemandId()!=null){
               	if(bug.getDemandId()!=bugManage.getDemandId()){
               		operationRecord.append("修改了 相关需求 ,旧值为'"+bug.getDemandId()+"' , 新值为'"+bugManage.getDemandId()+"'。");
               	}
               }
               if(bugManage.getTaskId()!=null){
               	if(bug.getTaskId()!=bugManage.getTaskId()){
               		operationRecord.append("修改了 相关任务 ,旧值为'"+bug.getTaskId()+"' , 新值为'"+bugManage.getTaskId()+"'。");
               	}
               }
               if(bugManage.getCaseId()!=null){
               	if(bug.getCaseId()!=bugManage.getCaseId()){
               		operationRecord.append("修改了 相关用例 ,旧值为'"+bug.getCaseId()+"' , 新值为'"+bugManage.getCaseId()+"'。");
               	}
               }
               if(bugManage.getSolveId()!=null){
               	if(bug.getSolveId()!=bugManage.getSolveId()){
               		String username = userMapper.selectUserById(bug.getSolveId()).getUsername();
               		String newusername = userMapper.selectUserById(bug.getSolveId()).getUsername();
               		operationRecord.append("修改了 解决者 ,旧值为'"+username+"' , 新值为'"+newusername+"'。");
               	}
               }
               if(bugManage.getSolveProgram()!=null){
               	if(bug.getSolveProgram()!=bugManage.getSolveProgram()){
               		operationRecord.append("修改了 解决方案 ,旧值为'"+bug.getSolveProgram()+"' , 新值为'"+bugManage.getSolveProgram()+"'。");
               	}
               }
               if(bugManage.getCloseId()!=null){
               	if(bug.getCloseId()!=bugManage.getCloseId()){
               		String username = userMapper.selectUserById(bug.getCloseId()).getUsername();
               		String newusername = userMapper.selectUserById(bug.getCloseId()).getUsername();
               		operationRecord.append("修改了 关闭人 ,旧值为'"+username+"' , 新值为'"+newusername+"'。");
               	}
               	
               }
               if(bugManage.getCloseTime()!=null){
               	if(bug.getCloseTime()!=bugManage.getCloseTime()){
               		operationRecord.append("修改了关闭日期,旧值为'"+bug.getCloseTime()+"' , 新值为'"+bugManage.getCloseTime()+"'。");
               	}
               	
               }
               //历史详情
               String or=operationRecord.toString();
               //历史详情
               bugHistory.setOperationRecord(or);
         }
       
         BugHistory history = bugHistoryMapper.getBugHistoryByBugId(bugManage.getId());
         //编号
         if(history==null||StringUtils.isEmpty(history.getNum())){
         	bugHistory.setNum("1");
         }else{
         	bugHistory.setNum((Integer.parseInt(history.getNum())+1)+"");
         }
        
        //关联bugId 
        bugHistory.setBugId(bugManage.getId());
        //操作时间
        bugHistory.setOperationTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        //操作人*
        bugHistory.setOperationId(userId);
        //操作功能
        bugHistory.setOperationFunction(operation);
        bugHistoryMapper.insertSelective(bugHistory);
	}

}