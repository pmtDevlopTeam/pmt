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

import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.utils.DateUtils;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugHistoryMapper;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugManageMapper;
import com.camelot.pmt.testmanage.bugmanage.model.BugHistory;
import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;
import com.camelot.pmt.testmanage.bugmanage.service.BugManageService;
import com.github.pagehelper.PageHelper;

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
	private ShiroUtils shiroUtils;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BugHistoryMapper bugHistoryMapper;
	
	
	/**
	 * 添加bug
	 */
	@Transactional
	public boolean addBugManage(BugManage bugManage) {
		bugManage=createBugManageModel(bugManage);
		int addCount = bugManageMapper.addBugManage(bugManage);
		selectStatus(bugManage,bugManage.getCreateUserId(),"添加");
		return addCount==1?true:false;
	}
	private BugManage createBugManageModel(BugManage bugManage) {
		//创建时间
        bugManage.setCreateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        //bug默认状态 未确认
        bugManage.setBugStatus("0");
        //创建人*
        User user = (User) shiroUtils.getSessionAttribute("user");
        bugManage.setCreateUserId(user.getUserId());
		return bugManage;
	}
	
	/**
	 * 编辑bug
	 */
	@Transactional
	public  boolean updateBugManage(BugManage bugManage) {
		//判断传入的bug对象是否为空
        if (bugManage == null || bugManage.getId()==null) {
            return false;
        }
        BugManage currentBugManage = bugManageMapper.queryBugById(bugManage.getId());
        if(currentBugManage.getDesignatedId()!=null){
        	 //bug已经被指派，不可编辑
             return false;
        }
        //更新时间
        bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        //更新人*
        User user = (User) shiroUtils.getSessionAttribute("user");
        bugManage.setModifyUserId(user.getUserId());
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
    			//解决方案不能为空
    			return false;
    		}else{
    			//如果解决日期未填写
    			if(StringUtils.isEmpty(solveTime)){
    				bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
    				//bug状态为已解决状态
    				bugManage.setBugStatus("2");
    			}
    		}
    		
    	}
    	//如果只填写解决日期，未填写关闭人关闭时间情况下
    	if(!StringUtils.isEmpty(solveTime)&&StringUtils.isEmpty(closeId)&&StringUtils.isEmpty(closeTime)){
    		//bug状态为已解决
    		bugManage.setBugStatus("2");
    	}
    	
      
    	//如果填写了解决方案 但是解决者解决日期关闭人和关闭日期都未填写的情况下 
    	if(solveProgram!=null&&StringUtils.isEmpty(closeId)&&StringUtils.isEmpty(closeTime)){
    		//如果没有填写解决人*
    		if(StringUtils.isEmpty(solveId)){
    			//解决者为当前操作用户，解决日期为当前时间 
    			bugManage.setSolveId(user.getUserId());
    		}
    		//如果没有填写解决日期
    		if(StringUtils.isEmpty(solveTime)){
    			bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
    		}
    		//bug状态为已解决
    		bugManage.setBugStatus("2");
    	}
        
        //如果只填写关闭人情况下
    	if(!StringUtils.isEmpty(closeId)){
    		//如果解决方案未填写
    		if(solveProgram==null){
    			//解决方案不能为空
    			return false;
    		}else{
    			//如果关闭日期未填写
    			if(StringUtils.isEmpty(closeTime)){
    				bugManage.setCloseTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
    			}
    			//如果解决者未填写*
    			if(StringUtils.isEmpty(solveId)){
    				//默认为当前操作用户
    				bugManage.setSolveId(user.getUserId());
    			}
    			//如果解决日期未填写
    			if(StringUtils.isEmpty(solveTime)){
    				bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
    			}
    			//bug状态为已关闭
        		bugManage.setBugStatus("3");
    		}
    	}
    	//只填写了关闭日期的情况下
    	if(!StringUtils.isEmpty(closeTime)){
    		//如果解决方案未填写
    		if(solveProgram==null){
    			//解决方案不能为空
    			return false;
    		}else{
    			//如果解决者未填写*
    			if(StringUtils.isEmpty(solveId)){
    				//默认为当前操作用户
    				bugManage.setSolveId(user.getUserId());
    			}
    			//如果关闭人未填写*
    			if(StringUtils.isEmpty(closeId)){
    				//默认为当前操作用户*
    				bugManage.setCloseId(user.getUserId());
    			}
    			//如果解决日期未填写
    			if(StringUtils.isEmpty(solveTime)){
    				bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
    			}
    			//bug状态为已关闭
        		bugManage.setBugStatus("3");
    		}
    	}
       selectStatus(bugManage,user.getUserId(),"编辑");
	   return bugManageMapper.updateBugManage(bugManage)==1?true:false;
			 	
	}


	/**
	 * 撤销bug
	 */
	@Transactional
	public boolean updateBugStatusRevoke(BugManage bugManage) {
		 	//判断传入的bug对象是否为空
            if (bugManage==null||bugManage.getId()==null) {
                //传入bugId错误
                return false;
            }
            //更新时间
            bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            //更新人*
            User user = (User) shiroUtils.getSessionAttribute("user");
            bugManage.setModifyUserId(user.getUserId());
            selectStatus(bugManage,user.getUserId(),"编辑");
            return bugManageMapper.updateBugStatusRevoke(bugManage)==1?true:false;
        } 

	/**
	 * 关闭bug
	 */
	@Transactional
	public boolean updateBugStatusClose(BugManage bugManage) {
		 	//判断传入的bug对象是否为空
            if (bugManage==null||bugManage.getId()==null) {
            	//传入参数错误
                return false;
            }
            User user = (User) shiroUtils.getSessionAttribute("user");
            //更新时间
            bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            //更新人*
            bugManage.setModifyUserId(user.getUserId());
            //关闭时间
            bugManage.setCloseTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            //关闭人*
            bugManage.setCloseId(user.getUserId());
            selectStatus(bugManage,user.getUserId(),"编辑");
	        return bugManageMapper.updateBugStatusClose(bugManage)==1?true:false;
	}

	/**
	 * 确认bug
	 */
	@Transactional
	public boolean updateBugStatusYes(BugManage bugManage) {
	 	//判断传入的bug对象是否为空
        if (bugManage==null||bugManage.getId()==null) {
            //传入参数错误
            return false;
        }
        User user = (User) shiroUtils.getSessionAttribute("user");
        //更新时间
        bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        //更新人*
        bugManage.setModifyUserId(user.getUserId());
        selectStatus(bugManage,user.getUserId(),"编辑");
        return bugManageMapper.updateBugStatusYes(bugManage)==1?true:false;
	}

	/**
	 * 指派bug
	 */
	@Transactional
	public boolean updateBugAssign(BugManage bugManage) {
	 	//判断传入的bug对象是否为空
        if (bugManage==null||bugManage.getId()==null) {
            //传入参数错误
            return false;
        }
        User user = (User) shiroUtils.getSessionAttribute("user");
        //更新时间
        bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        //更新人*
        bugManage.setModifyUserId(user.getUserId());
        selectStatus(bugManage,user.getUserId(),"编辑");
        return bugManageMapper.updateBugAssign(bugManage)==1?true:false;
	}
	
	/**
	 * 解决bug
	 */
	@Transactional
	public boolean updateBugSolve(BugManage bugManage) {
		//判断传入的bug对象是否为空
		if (bugManage==null||bugManage.getId()==null) {
			//传入参数错误
			return false;
		}
		 User user = (User) shiroUtils.getSessionAttribute("user");
        //更新时间
        bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        //更新人*
        bugManage.setModifyUserId(user.getUserId());
        //解决人
        bugManage.setSolveId(user.getUserId());
		//如果没有填写解决日期 默认添加为当前时间
		if(StringUtils.isEmpty(bugManage.getSolveTime())){
			bugManage.setSolveTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		}
		//解决者*
		bugManage.setSolveId(user.getUserId());
		selectStatus(bugManage,user.getUserId(),"编辑");
		return bugManageMapper.updateBugSolve(bugManage)==1?true:false;
	}

	
	
	/**
	 * 根据id查询bug
	 */
	@Override
	public BugManage queryBugById(Long id) {
        return bugManageMapper.queryBugById(id);
	}


	@Override
	public List<SelectBugManage> queryBugManageCondition(Map<String, Object> map) {
		User user=(User)shiroUtils.getSessionAttribute("user");
		map.put("userId", user.getUserId());
		Integer currentPage = (Integer) map.get("currentPage");
		Integer pageSize = (Integer) map.get("pageSize");
        PageHelper.startPage(currentPage,pageSize);
        List<SelectBugManage> docs = bugManageMapper.queryBugManageCondition(map);
        return docs;
	}
	
	@Override
	public Boolean queryByPUS(Map<String, Object> map) {
		List<SelectBugManage> selectByPUS = bugManageMapper.queryByPUS(map);
		if(selectByPUS!=null&&selectByPUS.size()>0){
			return true;
		}
		return false;
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
		return "";
	}
	
	public void selectStatus(BugManage bugManage,String userId,String operation){
		 StringBuilder operationRecord=new StringBuilder();
		 BugHistory bugHistory=new BugHistory();
         BugManage bug = bugManageMapper.queryBugById(bugManage.getId());
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
               		String old=bug.getVersionId()==null?"":bug.getVersionId().toString();
               		operationRecord.append("修改了 版本 ,旧值为'"+old+"' , 新值为'"+bugManage.getVersionId()+"'。");
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
               		String old=bug.getProjectId()==null?"":bug.getProjectId().toString();
               		operationRecord.append("修改了 所属项目 ,旧值为'"+old+"' , 新值为'"+bugManage.getProjectId()+"'。");
               	}
               }
               if(bugManage.getDemandId()!=null){
               	if(bug.getDemandId()!=bugManage.getDemandId()){
               		String old=bug.getDemandId()==null?"":bug.getDemandId().toString();
               		operationRecord.append("修改了 相关需求 ,旧值为'"+old+"' , 新值为'"+bugManage.getDemandId()+"'。");
               	}
               }
               if(bugManage.getTaskId()!=null){
               	if(bug.getTaskId()!=bugManage.getTaskId()){
               		String old=bug.getTaskId()==null?"":bug.getTaskId().toString();
               		operationRecord.append("修改了 相关任务 ,旧值为'"+old+"' , 新值为'"+bugManage.getTaskId()+"'。");
               	}
               }
               if(bugManage.getCaseId()!=null){
               	if(bug.getCaseId()!=bugManage.getCaseId()){
               		String old=bug.getCaseId()==null?"":bug.getCaseId().toString();
               		operationRecord.append("修改了 相关用例 ,旧值为'"+old+"' , 新值为'"+bugManage.getCaseId()+"'。");
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
               		String old=bug.getSolveProgram()==null?"":bug.getSolveProgram().toString();
               		operationRecord.append("修改了 解决方案 ,旧值为'"+old+"' , 新值为'"+bugManage.getSolveProgram()+"'。");
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
       
         BugHistory history = bugHistoryMapper.queryBugHistoryByBugId(bugManage.getId());
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
        bugHistoryMapper.addBugHistory(bugHistory);
	}

	



}