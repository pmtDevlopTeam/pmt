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

import com.camelot.pmt.common.compareBeanAttr;
import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.utils.DateUtils;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugHistoryMapper;
import com.camelot.pmt.testmanage.bugmanage.mapper.BugManageMapper;
import com.camelot.pmt.testmanage.bugmanage.model.BugHistory;
import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManageCount;
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
	@Transactional(rollbackFor = Exception.class)
	public boolean addBugManage(BugManage bugManage,User user) {
		boolean flag=false;
		bugManage=createBugManageModel(bugManage,user);
		int addCount = bugManageMapper.addBugManage(bugManage);
		// selectStatus(bugManage,bugManage.getCreateUserId(),"添加");
		if(addCount>0){
			function(bugManage,user,"添加",null);
			flag=true;
		}
		return flag;
	}
	private BugManage createBugManageModel(BugManage bugManage, User user) {
		SelectBugManage queryBugLimit = bugManageMapper.queryBugLimit();
		String bugNo="";
		if(queryBugLimit==null||queryBugLimit.getBugNo()==null){
			 bugNo=getIncreNum("");
		}else{
			 bugNo=getIncreNum(queryBugLimit.getBugNo());
		}
		bugManage.setBugNo(bugNo);
		//创建时间
        bugManage.setCreateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        bugManage.setModifyTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
        //bug默认状态 未确认
        bugManage.setBugStatus("0");
        //创建人*
        bugManage.setCreateUserId(user.getUserId());
        bugManage.setModifyUserId(user.getUserId());
		return bugManage;
	}
	
	/**
	 * 编辑bug
	 */
	@Transactional(rollbackFor = Exception.class)
	public  boolean updateBugManage(BugManage bugManage, User user) {
		boolean flag=false;
		// 判断传入的bug对象是否为空
		if (bugManage == null || bugManage.getId() == null) {
			return false;
		}
		try {
			BugManage currentBugManage = bugManageMapper.queryBugById(bugManage.getId());
			if (currentBugManage.getDesignatedId() != null) {
				// bug已经被指派，不可编辑
				return false;
			}
			// 更新时间
			bugManage.setModifyTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
			// 更新人*
			bugManage.setModifyUserId(user.getUserId());
			// 解决方案
			Integer solveProgram = bugManage.getSolveProgram();
			// 解决日期
			String solveTime = bugManage.getSolveTime();
			// 解决人
			String solveId = bugManage.getSolveId();
			// 关闭人
			String closeId = bugManage.getCloseId();
			// 关闭时间
			String closeTime = bugManage.getCloseTime();

			// 如果解决人解决日期解决方案关闭人关闭日期都未填写
			if (StringUtils.isEmpty(solveId) && StringUtils.isEmpty(solveTime) && solveProgram == null
					&& StringUtils.isEmpty(closeId) && StringUtils.isEmpty(closeTime)) {
				// 修改bug状态无变化 为激活状态
				bugManage.setBugStatus("0");
			}
			// 如果只填写解决人关闭人关闭日期都未填写
			if (!StringUtils.isEmpty(solveId) && StringUtils.isEmpty(closeId) && StringUtils.isEmpty(closeTime)) {
				// 如果解决方案未填写
				if (solveProgram == null) {
					// 解决方案不能为空
					return false;
				} else {
					// 如果解决日期未填写
					if (StringUtils.isEmpty(solveTime)) {
						bugManage.setSolveTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
						// bug状态为已解决状态
						bugManage.setBugStatus("2");
					}
				}

			}
			// 如果只填写解决日期，未填写关闭人关闭时间情况下
			if (!StringUtils.isEmpty(solveTime) && StringUtils.isEmpty(closeId) && StringUtils.isEmpty(closeTime)) {
				// bug状态为已解决
				bugManage.setBugStatus("2");
			}

			// 如果填写了解决方案 但是解决者解决日期关闭人和关闭日期都未填写的情况下
			if (solveProgram != null && StringUtils.isEmpty(closeId) && StringUtils.isEmpty(closeTime)) {
				// 如果没有填写解决人*
				if (StringUtils.isEmpty(solveId)) {
					// 解决者为当前操作用户，解决日期为当前时间
					bugManage.setSolveId(user.getUserId());
				}
				// 如果没有填写解决日期
				if (StringUtils.isEmpty(solveTime)) {
					bugManage.setSolveTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
				}
				// bug状态为已解决
				bugManage.setBugStatus("2");
			}

			// 如果只填写关闭人情况下
			if (!StringUtils.isEmpty(closeId)) {
				// 如果解决方案未填写
				if (solveProgram == null) {
					// 解决方案不能为空
					return false;
				} else {
					// 如果关闭日期未填写
					if (StringUtils.isEmpty(closeTime)) {
						bugManage.setCloseTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
					}
					// 如果解决者未填写*
					if (StringUtils.isEmpty(solveId)) {
						// 默认为当前操作用户
						bugManage.setSolveId(user.getUserId());
					}
					// 如果解决日期未填写
					if (StringUtils.isEmpty(solveTime)) {
						bugManage.setSolveTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
					}
					// bug状态为已关闭
					bugManage.setBugStatus("3");
				}
			}
			// 只填写了关闭日期的情况下
			if (!StringUtils.isEmpty(closeTime)) {
				// 如果解决方案未填写
				if (solveProgram == null) {
					// 解决方案不能为空
					return false;
				} else {
					// 如果解决者未填写*
					if (StringUtils.isEmpty(solveId)) {
						// 默认为当前操作用户
						bugManage.setSolveId(user.getUserId());
					}
					// 如果关闭人未填写*
					if (StringUtils.isEmpty(closeId)) {
						// 默认为当前操作用户*
						bugManage.setCloseId(user.getUserId());
					}
					// 如果解决日期未填写
					if (StringUtils.isEmpty(solveTime)) {
						bugManage.setSolveTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
					}
					// bug状态为已关闭
					bugManage.setBugStatus("3");
				}
			}

			Object obj = compareBeanAttr.compareBeanAttr(BugManage.class, bugManage, currentBugManage,
					new String[] { "id" });
			String operateDesc = (String) obj;
			int updateBugManage = bugManageMapper.updateBugManage(bugManage);
			if(updateBugManage>0){
				function(bugManage,user,"更新",operateDesc);
			    flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// selectStatus(bugManage,user.getUserId(),"编辑");
		return flag;
			 	
	}


	/**
	 * 撤销bug
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateBugStatusRevoke(BugManage bugManage, User user) {
		boolean flag=false;
		// 判断传入的bug对象是否为空
		if (bugManage == null || bugManage.getId() == null) {
			// 传入bugId错误
			return false;
		}
		try{
		BugManage currentBugManage = bugManageMapper.queryBugById(bugManage.getId());
		// 更新时间
		bugManage.setModifyTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		// 更新人*
		bugManage.setModifyUserId(user.getUserId());
		// selectStatus(bugManage,user.getUserId(),"编辑");
		Object obj = compareBeanAttr.compareBeanAttr(BugManage.class, bugManage, currentBugManage,
				new String[] { "id" });
		String operateDesc = (String) obj;
		int updateBugStatusRevoke = bugManageMapper.updateBugStatusRevoke(bugManage);
		if(updateBugStatusRevoke>0){
			function(bugManage,user,"更新",operateDesc);
			flag=true;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return  flag;
        } 

	/**
	 * 关闭bug
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateBugStatusClose(BugManage bugManage, User user) {
		boolean flag=false;
		// 判断传入的bug对象是否为空
		if (bugManage == null || bugManage.getId() == null) {
			// 传入参数错误
			return false;
		}
		try{
		BugManage currentBugManage = bugManageMapper.queryBugById(bugManage.getId());
		// 更新时间
		bugManage.setModifyTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		// 更新人*
		bugManage.setModifyUserId(user.getUserId());
		// 关闭时间
		bugManage.setCloseTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		// 关闭人*
		bugManage.setCloseId(user.getUserId());
		
		Object obj = compareBeanAttr.compareBeanAttr(BugManage.class, bugManage, currentBugManage,
				new String[] { "id" });
		String operateDesc = (String) obj;
		int updateBugStatusClose = bugManageMapper.updateBugStatusClose(bugManage);
		if(updateBugStatusClose>0){
			function(bugManage,user,"更新",operateDesc);
			flag=true;
		}}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 确认bug
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateBugStatusYes(BugManage bugManage, User user) {
		boolean flag=false;
		// 判断传入的bug对象是否为空
		if (bugManage == null || bugManage.getId() == null) {
			// 传入参数错误
			return false;
		}
		try{
		BugManage currentBugManage = bugManageMapper.queryBugById(bugManage.getId());
		// 更新时间
		bugManage.setModifyTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		// 更新人*
		bugManage.setModifyUserId(user.getUserId());
		Object obj = compareBeanAttr.compareBeanAttr(BugManage.class, bugManage, currentBugManage,
				new String[] { "id" });
		String operateDesc = (String) obj;
		int updateBugStatusYes =bugManageMapper.updateBugStatusYes(bugManage);
		if(updateBugStatusYes>0){
			function(bugManage,user,"更新",operateDesc);
			flag=true;
		}}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 指派bug
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateBugAssign(BugManage bugManage, User user) {
		boolean flag=false;
		// 判断传入的bug对象是否为空
		if (bugManage == null || bugManage.getId() == null) {
			// 传入参数错误
			return false;
		}
		try{
		BugManage currentBugManage = bugManageMapper.queryBugById(bugManage.getId());
		// 更新时间
		bugManage.setModifyTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		// 更新人*
		bugManage.setModifyUserId(user.getUserId());
		Object obj = compareBeanAttr.compareBeanAttr(BugManage.class, bugManage, currentBugManage,
				new String[] { "id" });
		String operateDesc = (String) obj;
		int updateBugAssign = bugManageMapper.updateBugAssign(bugManage);
		if(updateBugAssign>0){
			function(bugManage,user,"更新",operateDesc);
			flag=true;
		}}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 解决bug
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateBugSolve(BugManage bugManage, User user) {
		boolean flag=false;
		// 判断传入的bug对象是否为空
		if (bugManage == null || bugManage.getId() == null) {
			// 传入参数错误
			return false;
		}
		try{
		BugManage currentBugManage = bugManageMapper.queryBugById(bugManage.getId());
		// 更新时间
		bugManage.setModifyTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		// 更新人*
		bugManage.setModifyUserId(user.getUserId());
		// 解决人
		bugManage.setSolveId(user.getUserId());
		// 如果没有填写解决日期 默认添加为当前时间
		if (StringUtils.isEmpty(bugManage.getSolveTime())) {
			bugManage.setSolveTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		}
		// 解决者*
		bugManage.setSolveId(user.getUserId());
		Object obj = compareBeanAttr.compareBeanAttr(BugManage.class, bugManage, currentBugManage,
				new String[] { "id" });
		String operateDesc = (String) obj;
		int updateBugSolve = bugManageMapper.updateBugSolve(bugManage);
		if(updateBugSolve>0){
			function(bugManage,user,"更新",operateDesc);
			flag=true;
		}}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
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
		Integer currentPage = (Integer) map.get("currentPage");
		Integer pageSize = (Integer) map.get("pageSize");
		PageHelper.startPage(currentPage, pageSize);
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
	
	
	@Override
	public SelectBugManage queryBugLimit() {
		 return bugManageMapper.queryBugLimit();
	}
	
	
	
	
	
	public String getIncreNum(String str) {
        if (StringUtils.isEmpty(str)) {
            return "BUG01";
        }
        str=str.substring(3);
        Long parseInt = Long.parseLong(str);
        if (parseInt < 9 && parseInt > 0) {
            String valueOf = String.valueOf(++parseInt);
            return "BUG0" + valueOf;
        } else if (parseInt <= 0) {
            return "BUG01";
        }
        return "BUG"+String.valueOf(++parseInt);
    }
	
	public void function(BugManage bugManage,User user,String operation,String operateDesc){
		BugHistory bugHistory=new BugHistory();
		BugHistory history =bugHistoryMapper.queryBugHistoryByBugId(bugManage.getId()); //编号
	    if(history==null||StringUtils.isEmpty(history.getNum())){bugHistory.setNum("1"); 
	       }else{
	    	bugHistory.setNum((Integer.parseInt(history.getNum())+1)+""); }
	    	//关联bugId 
	    	bugHistory.setBugId(bugManage.getId()); 
	    	//操作时间
	    	bugHistory.setOperationTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN)); 
	    	//操作人*
	    	bugHistory.setOperationId(user.getUserId()); 
	    	//操作功能
	    	bugHistory.setOperationFunction(operation);
	    	bugHistory.setOperationRecord(operateDesc);
	    	bugHistoryMapper.addBugHistory(bugHistory);
	}
	
	/**
     * 当日生产的bug
     * @return
     */
	@Override
	public Integer queryCreateTB(Long projectId) {
		return bugManageMapper.queryCreateTB(projectId);
	}
	 /**
     * 当日已解决bug
     */
	@Override
	public Integer querySolveTB(Long projectId) {
		return bugManageMapper.querySolveTB(projectId);
	}
	 /**
     * bug统计
     */
	@Override
	public Integer queryBugTJ(Map<String, Object> map) {
		return bugManageMapper.queryBugTJ(map);
	}
	
	/**
     * 根据项目统计出任务bug
     * @param projectId
     * @return
     */
	@Override
	public List<SelectBugManageCount> queryCountBugTask(Long projectId) {
		return bugManageMapper.queryCountBugTask(projectId);
	}
	 /**
     * 根据任务统计出负责人bug
     * @param taskId
     * @return
     */
	@Override
	public List<SelectBugManageCount> queryCountBugDesignated(Long taskId) {
		return bugManageMapper.queryCountBugDesignated(taskId);
	}


}