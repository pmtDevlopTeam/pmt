package com.camelot.pmt.filemanage.mapper;

import com.camelot.pmt.filemanage.model.FileManage;

import java.util.List;
/*
张战
 */
public interface FileManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileManage record);

    int insertSelective(FileManage record);

    FileManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileManage record);

    int updateByPrimaryKey(FileManage record);

    List<FileManage> selectFileByGroupID(Long id);
}