package com.camelot.pmt.filemanage.service;

import com.camelot.pmt.filemanage.model.FileManageGroup;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.List;

/**
 *
 * FileManageGroup 表数据服务层接口
 *
 */
public interface FileManageGroupService {
    Boolean addFileManagerGroup(HttpServletRequest request, FileManageGroup fileManageGroup);//添加文件夹

    Boolean deleteFileGroup(FileManageGroup fileManageGroup);//删除文件夹

    Boolean updateFileGroupById(FileManageGroup fileManageGroup);//修改文件夹

    /*List<FileManageGroup> selectFileGroupByProjectID(FileManageGroup fileManageGroup); */

    List<FileManageGroup> selectFileGroup(FileManageGroup fileManageGroup);
}