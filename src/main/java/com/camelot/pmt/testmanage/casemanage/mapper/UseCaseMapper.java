package com.camelot.pmt.testmanage.casemanage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.github.pagehelper.Page;

public interface UseCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UseCase record);

    int insertSelective(UseCase record);

    UseCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseCase record);

    int updateByPrimaryKey(UseCase record);

    int updateUserCaseDelFlag(Long id);

    int insertBatch(List<UseCase> list);

    List<UseCase> queryAllUserCaseList(Map<String, Object> condition);
}