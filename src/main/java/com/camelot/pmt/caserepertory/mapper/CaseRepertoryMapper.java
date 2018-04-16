package com.camelot.pmt.caserepertory.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.caserepertory.model.CaseRepertory;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;
@Mapper
public interface CaseRepertoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_repertory
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_repertory
     *
     * @mbg.generated
     */
    int insert(CaseRepertory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_repertory
     *
     * @mbg.generated
     */
    int insertSelective(CaseRepertory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_repertory
     *
     * @mbg.generated
     */
    CaseRepertory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_repertory
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CaseRepertory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table case_repertory
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CaseRepertory record);

	List<SelectBugManage> selectCondition(Map<String, Object> map);
}