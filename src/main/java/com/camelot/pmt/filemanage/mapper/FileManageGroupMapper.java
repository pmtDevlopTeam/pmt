package com.camelot.pmt.filemanage.mapper;

import com.camelot.pmt.filemanage.model.FileManageGroup;

import java.util.List;

public interface FileManageGroupMapper {

    int insertSelective(FileManageGroup record);// 添加文件

    int insert(FileManageGroup record);// 添加文件

    int deleteByPrimaryKey(Long id);// 根据id删除

    int deleteBatchFileGroupById(List<Long> fileManageGroupIds);// 根据id进行批量删除

    int updateByPrimaryKeySelective(FileManageGroup record);// 根据id修改

    int updateByPrimaryKey(FileManageGroup record);// 根据id修改

    FileManageGroup selectByPrimaryKey(Long id);// 根据id查询

    List<FileManageGroup> querytFileGroup(FileManageGroup fileManageGroup);// 根据不同条件查询（parentID，projectID）

    List<Long> selectFileManagerGroupByParentId(Long id);// 根据父级ID进行查询

}