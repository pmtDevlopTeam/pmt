package com.camelot.pmt.filemanage.mapper;

import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;

import java.util.List;
/*

张战



 */
public interface FileManageMapper {
    int deleteByPrimaryKey(Long id);//根据id进行删除

    int insert(FileManage record);//添加文件

    int insertSelective(FileManage record);//添加文件

    FileManage selectByPrimaryKey(Long id);//根据id查询

    int updateByPrimaryKeySelective(FileManage record);//根据id修改

    int updateByPrimaryKey(FileManage record);//根据id修改

    List<FileManage> queryFileByGroupId(FileManageGroup fileManageGroup);//根据组id查询

    List selectFileManagerByGroupId(Long id);//根据组id查询

    int deleteBatchFileById(List fileManagerIds);//根据id进行批量删除

}