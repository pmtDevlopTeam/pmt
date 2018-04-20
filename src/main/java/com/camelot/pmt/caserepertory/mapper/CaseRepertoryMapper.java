package com.camelot.pmt.caserepertory.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.caserepertory.model.CaseRepertory;

@Mapper
public interface CaseRepertoryMapper {
    /*
     * 添加
     */
    int addCaseRepertoryByCaseid(CaseRepertory caseRepertory);

    int addCaseRepertoryStepByCaseid(Map<String, Object> map);

    int addUserCaseByCaseRepertoryid(CaseRepertory caseRepertory);

    int addUserCaseStepByCaseRepertoryid(Map<String, Object> map);

    int insert(CaseRepertory record);

    int insertSelective(CaseRepertory record);

    /*
     * 删除
     */
    int deleteByPrimaryKey(Long id);

    int remove(Map<String, Object> param);

    /*
     * 修改
     */
    int updateByPrimaryKeySelective(CaseRepertory record);

    int updateByPrimaryKey(CaseRepertory record);

    int insertBatch(List<CaseRepertory> list);

    /*
     * 查询
     */
    CaseRepertory selectByPrimaryKey(Long id);

    List<CaseRepertory> find(Map<String, Object> param);

    List<CaseRepertory> selectCondition(CaseRepertory caseRepertory);

}