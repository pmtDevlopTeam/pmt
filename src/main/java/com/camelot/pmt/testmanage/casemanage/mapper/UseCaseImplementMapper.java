package com.camelot.pmt.testmanage.casemanage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCaseImplement;

import java.util.List;
import java.util.Map;

@Mapper
public interface UseCaseImplementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UseCaseImplement record);

    int insertSelective(UseCaseImplement record);

    UseCaseImplement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseCaseImplement record);

    int updateByPrimaryKey(UseCaseImplement record);

    List<UseCaseImplement> find(Map<String, Object> param);
}