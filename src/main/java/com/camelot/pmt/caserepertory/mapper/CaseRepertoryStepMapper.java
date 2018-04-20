package com.camelot.pmt.caserepertory.mapper;

import com.camelot.pmt.caserepertory.model.CaseRepertoryStep;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseRepertoryStepMapper {
    /*
     * 添加
     */
    int insert(CaseRepertoryStep record);

    int insertSelective(CaseRepertoryStep record);

    int insertBatch(List<CaseRepertoryStep> list);

    /*
     * 删除
     */
    int deleteByPrimaryKey(Long id);

    int remove(Map<String, Object> param);

    /*
     * 修改
     */
    int updateByPrimaryKeySelective(CaseRepertoryStep record);

    int updateByPrimaryKey(CaseRepertoryStep record);

    /*
     * 查询
     */
    CaseRepertoryStep selectByPrimaryKey(Long id);

    List<CaseRepertoryStep> findByUseCaseId(Long useCaseId);

}