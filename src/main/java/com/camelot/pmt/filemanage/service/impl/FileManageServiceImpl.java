package com.camelot.pmt.filemanage.service.impl;

import com.camelot.pmt.filemanage.mapper.FileManageGroupMapper;
import com.camelot.pmt.filemanage.mapper.FileManageMapper;
import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * FileManage 表数据服务层接口实现类
 *张战
 */
@Service
public class FileManageServiceImpl implements FileManageService {
    @Autowired
    private FileManageMapper fileManageMapper;
    @Autowired
    private FileManageGroupMapper fileManageGroupMapper;
    @Override
    public Boolean addFileManager(HttpServletRequest request, FileManage fileManage) {
        String createUserId = (String) request.getSession().getAttribute("");//从session获取创建者id
        Date createTime = new Date();//创建时间
        long l= fileManageMapper.insertSelective(fileManage);//添加结果
        FileManageGroup group = new FileManageGroup();//新创建组对象
        Long createUserId2 = (Long) request.getSession().getAttribute("");//从session获取创建者id
        group.setCreateUserId(createUserId2);//设置创建时间
        group.setCreateTime(createTime);//
        group.setIsfile(1);//设置文件夹格式
        int insert = fileManageGroupMapper.insertSelective(group);//添加文件的文件夹
        Boolean b=true;
        if(l==0||insert==0){//判断是否添加成功
            b=false;
        }
        return b;
    }

    @Override
    public Boolean deleteFileById(FileManage fileManage) {
        Long id = fileManage.getId();//获取文件id
        int i = fileManageMapper.deleteByPrimaryKey(id);//文件删除
        Boolean b=true;
        if(i==0){
            b=false;
        }
        return b;
    }

    @Override
    public Boolean updateFileById(FileManage fileManage) {
        int i = fileManageMapper.updateByPrimaryKey(fileManage);//文件修改
        Boolean b=true;
        if(i==0){
            b=false;
        }
        return b;
    }

    @Override
    public List<FileManage> selectFileByGroupID(FileManageGroup fileManageGroup) {
        Long id = fileManageGroup.getId();//获取文件的group_id
        List<FileManage> fileList= fileManageMapper.selectFileByGroupID(id);//文件修改
        return fileList;
    }
}