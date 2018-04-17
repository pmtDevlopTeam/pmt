package com.camelot.pmt.filemanage.service;

import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.platform.utils.ExecuteResult;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.List;

/**
 *
 * FileManageGroup 表数据服务层接口



 */
public interface FileManageGroupService {
    ExecuteResult<String> addFileManagerGroup(HttpServletRequest request, FileManageGroup fileManageGroup);//添加文件夹

    ExecuteResult<String> deleteFileGroup(FileManageGroup fileManageGroup);//删除文件夹


    ExecuteResult<String> updateFileGroupById(HttpServletRequest request, FileManageGroup fileManageGroup);//修改文件夹


    ExecuteResult<List<FileManageGroup>> selectFileGroup(FileManageGroup fileManageGroup);//通过条件查询文件夹

    List<FileManageGroup> selectTree(FileManageGroup fileManageGroup);
}