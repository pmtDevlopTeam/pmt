package com.camelot.pmt.testmanage.casemanage.mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseProcedureImplement;

import java.util.List;

public interface UseCaseProcedureImplementMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table use_case_procedure_implement
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table use_case_procedure_implement
     *
     * @mbg.generated
     */
    int insert(UseCaseProcedureImplement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table use_case_procedure_implement
     *
     * @mbg.generated
     */
    int insertSelective(UseCaseProcedureImplement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table use_case_procedure_implement
     *
     * @mbg.generated
     */
    UseCaseProcedureImplement selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table use_case_procedure_implement
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UseCaseProcedureImplement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table use_case_procedure_implement
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UseCaseProcedureImplement record);

    int insertBatch(List<UseCaseProcedureImplement> list);

    List<UseCaseProcedureImplement> findByImplementId(Long implementId);
}