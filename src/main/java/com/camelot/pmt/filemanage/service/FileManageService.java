package com.camelot.pmt.filemanage.service;

import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.common.ExecuteResult;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.List;

/**
 *
 * FileManage 表数据服务层接口


 */
public interface FileManageService {
    Boolean addFileManager(HttpServletRequest request, FileManage fileManage);//文件添加

    Boolean deleteFileById(FileManage fileManage);//文件删除

    Boolean updateFileById(HttpServletRequest request, FileManage fileManage);//文件修改

    PageInfo<FileManage> queryFileByGroupId(FileManageGroup fileManageGroup);// 文件详细信息查询（根据组idprojectID）

    ExecuteResult<List<FileManage>> queryAllFile();
}