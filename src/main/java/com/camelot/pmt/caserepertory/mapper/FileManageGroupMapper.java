package com.camelot.pmt.caserepertory.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.caserepertory.model.FileManageGroup;
@Mapper
public interface FileManageGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileManageGroup record);

    int insertSelective(FileManageGroup record);

    FileManageGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileManageGroup record);

    int updateByPrimaryKey(FileManageGroup record);
}