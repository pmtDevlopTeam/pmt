package com.camelot.pmt.filemanage.service;

import com.camelot.pmt.filemanage.model.FileManageGroup;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * FileManageGroup 表数据服务层接口
 *张战
 */
public interface FileManageGroupService {
    Boolean addFileManagerGroup(HttpServletRequest request, FileManageGroup fileManageGroup);//添加文件夹

    Boolean deleteFileGroup(FileManageGroup fileManageGroup);//删除文件夹

    Boolean updateFileGroupById(HttpServletRequest request,FileManageGroup fileManageGroup);//修改文件夹

    List<FileManageGroup> selectFileGroup(FileManageGroup fileManageGroup);//通过条件查询文件夹
}