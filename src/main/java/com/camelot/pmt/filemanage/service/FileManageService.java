package com.camelot.pmt.filemanage.service;

import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tools.ant.taskdefs.condition.Http;

import java.util.List;

/**
 *
 * FileManage 表数据服务层接口
<<<<<<< Updated upstream
 *张战
=======
 *张战zz
>>>>>>> Stashed changes
 */
public interface FileManageService {
    Boolean addFileManager(HttpServletRequest request, FileManage fileManage);

    Boolean deleteFileById(FileManage fileManage);//文件删除

    Boolean updateFileById(HttpServletRequest request,FileManage fileManage);//文件修改

    List<FileManage> selectFileByGroupID(FileManageGroup fileManageGroup);// 文件详细信息查询
}