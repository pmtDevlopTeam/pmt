package com.camelot.pmt.filemanage.service.impl;

import com.camelot.pmt.filemanage.mapper.FileManageGroupMapper;
import com.camelot.pmt.filemanage.mapper.FileManageMapper;
import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageService;
import javax.servlet.http.HttpServletRequest;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
    @Transactional
    public Boolean deleteFileById(FileManage fileManage) {
        Long id = fileManage.getId();//获取文件id
        Long groupId = fileManage.getGroupId();
        int i = fileManageMapper.deleteByPrimaryKey(id);//文件删除
        int i1 = fileManageGroupMapper.deleteByPrimaryKey(groupId);
        Boolean b=true;
        if(i==0||i1==0){
            b=false;
        }
        return b;
    }

    @Override
    @Transactional
    public Boolean updateFileById(HttpServletRequest request,FileManage fileManage) {
        Date modifyTime = new Date();//获取当前时间
        Long  modifyUserId = (Long) request.getSession().getAttribute("");//获取修改人id
        fileManage.setModifyUserId(modifyUserId);
        fileManage.setModifyTime(modifyTime);
        int i = fileManageMapper.updateByPrimaryKeySelective(fileManage);//文件修改
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