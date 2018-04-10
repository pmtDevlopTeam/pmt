package com.camelot.pmt.task.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.task.model.Task;

import java.util.List;


public interface TaskMapper {
    int deleteByPrimaryKey(Long taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    /**
     * 查询逾期任务+分页
    * @Title: queryoverdueTask
    * @Description: TODO
    * @param @param page
    * @param @return
    * @return List<Task> 
    * @throws
     */
     
	List<Task> queryoverdueTask(@Param(value = "page")Pager page);
	/**
	 * 查询逾期任务个数
	* @Title: queryCount
	* @Description: TODO
	* @param @return
	* @return Long 
	* @throws
	 */
	Long queryCount();

	


}