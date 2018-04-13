package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.ProUser;

public interface ProUserMapper {
    int deleteByPrimaryKey(Integer proUserId);

    int insert(ProUser record);

    int insertSelective(ProUser record);

    ProUser selectByPrimaryKey(Integer proUserId);

    int updateByPrimaryKeySelective(ProUser record);

    int updateByPrimaryKey(ProUser record);
}