package com.camelot.pmt.filemanage.service;

import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.List;

/**
 *
 * FileManage 表数据服务层接口


 */
public interface FileManageService {
    ExecuteResult<String> addFileManager(HttpServletRequest request, FileManage fileManage);//文件添加

    ExecuteResult<String> deleteFileById(FileManage fileManage);//文件删除


    ExecuteResult<String> updateFileById(HttpServletRequest request, FileManage fileManage);//文件修改



    ExecuteResult<PageInfo> selectFileByGroupID(FileManageGroup fileManageGroup);// 文件详细信息查询（根据组idprojectID）

    ExecuteResult<List<FileManage>> selectAllFile();
}