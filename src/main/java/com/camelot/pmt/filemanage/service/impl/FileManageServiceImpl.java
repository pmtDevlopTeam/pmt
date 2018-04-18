package com.camelot.pmt.filemanage.service.impl;

import com.camelot.pmt.filemanage.mapper.FileManageGroupMapper;
import com.camelot.pmt.filemanage.mapper.FileManageMapper;
import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageService;
import com.camelot.pmt.common.ExecuteResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 * FileManage 表数据服务层接口实现类


 */
@Service
public class FileManageServiceImpl implements FileManageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileManageServiceImpl.class);
    @Autowired
    private FileManageMapper fileManageMapper;
    @Autowired
    private FileManageGroupMapper fileManageGroupMapper;
    @Override
    @Transactional
    public Boolean addFileManager(HttpServletRequest request, FileManage fileManage) {//文件添加
        String createUserId = (String) request.getSession().getAttribute("");//从session获取创建者id
        long l= fileManageMapper.insertSelective(fileManage);//添加结果
        FileManageGroup group = new FileManageGroup();//新创建组对象
        Long createUserId2 = (Long) request.getSession().getAttribute("");//从session获取创建者id
        if(createUserId2!=null){
            Date createTime = new Date();//创建时间
            group.setCreateUserId(createUserId2);//设置创建时间
            group.setCreateTime(createTime);//
        }
        group.setIsfile(1);//设置文件格式（0，文件夹，1:文件）
        int i = fileManageGroupMapper.insertSelective(group);//添加文件的文件夹
        Boolean b=true;
        if(i<=0){
            b=false;
        }
            return  b;
    }
    @Override
    @Transactional
    public Boolean deleteFileById(FileManage fileManage) {//文件删除操作
        Long id = fileManage.getId();//获取文件id
        Long groupId = fileManage.getGroupId();
        int i = fileManageMapper.deleteByPrimaryKey(id);//文件删除
        int i1 = fileManageGroupMapper.deleteByPrimaryKey(groupId);
        Boolean b=true;
        if(i<=0||i1<=0){
            b=false;
        }
        return b;
    }
    @Override
    @Transactional
    public Boolean updateFileById(HttpServletRequest request,FileManage fileManage) {//文件修改
        Long  modifyUserId = (Long) request.getSession().getAttribute("");//获取修改人id
        if(modifyUserId!=null){
            Date modifyTime = new Date();//获取当前时间
            fileManage.setModifyUserId(modifyUserId);
            fileManage.setModifyTime(modifyTime);
        }
        int i = fileManageMapper.updateByPrimaryKeySelective(fileManage);//文件修改
        Boolean b=true;
        if(i<=0){
            b=false;
        }
        return b;
    }

    @Override
    public PageInfo<FileManage> queryFileByGroupId(FileManageGroup fileManageGroup) {//根据组id查询文件
            Long id = fileManageGroup.getId();//获取文件group_id
            PageHelper.startPage(fileManageGroup.getCurrentPage(),fileManageGroup.getPageSize());
            List<FileManage> fileList= fileManageMapper.queryFileByGroupId(fileManageGroup);//文件查询
            PageInfo<FileManage> fileManagePageInfo = new PageInfo<>(fileList);
           return fileManagePageInfo;
    }

    @Override
    public ExecuteResult<List<FileManage>> queryAllFile() {
        ExecuteResult<List<FileManage>> result = new ExecuteResult<List<FileManage>>();
        try {

            List<FileManage> fileList= fileManageMapper.queryAllFile();//文件查询
            if (fileList.size() <= 0) {
                return result;
            }
            result.setResult(fileList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}