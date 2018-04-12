package com.camelot.pmt.filemanage.mapper;

import com.camelot.pmt.filemanage.model.FileManageGroup;

import java.util.List;
/*
张战
 */
public interface FileManageGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileManageGroup record);

    int insertSelective(FileManageGroup record);

    FileManageGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileManageGroup record);

    int updateByPrimaryKey(FileManageGroup record);

    List<FileManageGroup> selectFileGroup(FileManageGroup fileManageGroup);

}