package com.camelot.pmt.testmanage.bugmanage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;

@Mapper
public interface BugManageMapper {

    /**
     * 添加bug
     * 
     * @param record
     * @return
     */
    int addBugManage(BugManage record);

    /**
     * 修改bug
     * 
     * @param record
     * @return
     */
    int updateBugManage(BugManage record);

    /**
     * 撤销bug
     * 
     * @param record
     * @return
     */
    int updateBugStatusRevoke(BugManage record);

    /**
     * 关闭 bug
     * 
     * @param record
     * @return
     */
    int updateBugStatusClose(BugManage record);

    /**
     * 确认bug
     * 
     * @param
     * @return
     */
    int updateBugStatusYes(BugManage record);

    /**
     * 指派bug
     * 
     * @param
     * @return
     */
    int updateBugAssign(BugManage record);

    /**
     * 解决bug
     * 
     * @param
     * @return
     */
    int updateBugSolve(BugManage record);

    /**
     * 根据id查询bug
     * 
     * @param id
     * @return
     */
    BugManage queryBugById(Long id);

    /**
     * bug列表
     * 
     * @param
     * @return
     */
    List<SelectBugManage> queryBugManageCondition(Map<String, Object> map);

    /**
     * bug 通过项目id ,指派给,状态 条件查询
     * 
     * @param map
     * @return
     */
    List<SelectBugManage> queryByPUS(Map<String, Object> map);

}