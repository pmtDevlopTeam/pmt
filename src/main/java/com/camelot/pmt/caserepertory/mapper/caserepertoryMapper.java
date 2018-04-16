package com.camelot.pmt.caserepertory.mapper;

import com.camelot.pmt.caserepertory.model.caserepertory;

public interface caserepertoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(caserepertory record);

    int insertSelective(caserepertory record);

    caserepertory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(caserepertory record);

    int updateByPrimaryKey(caserepertory record);
}