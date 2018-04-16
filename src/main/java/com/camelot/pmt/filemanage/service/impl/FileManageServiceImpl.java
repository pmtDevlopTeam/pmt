package com.camelot.pmt.filemanage.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.camelot.pmt.filemanage.mapper.FileManageGroupMapper;
import com.camelot.pmt.filemanage.mapper.FileManageMapper;
import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageService;
import com.camelot.pmt.platform.utils.ExecuteResult;
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
    public ExecuteResult<String> addFileManager(HttpServletRequest request, FileManage fileManage) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(fileManage==null){
                result.addErrorMessage("传入的文件夹实体有误!");
                return result;
            }
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
            fileManageGroupMapper.insertSelective(group);//添加文件的文件夹
            result.setResult("添加文件成功!");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;

    }
    @Override
    @Transactional
    public ExecuteResult<String> deleteFileById(FileManage fileManage) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            Long id = fileManage.getId();//获取文件id
            Long groupId = fileManage.getGroupId();
            int i = fileManageMapper.deleteByPrimaryKey(id);//文件删除
            int i1 = fileManageGroupMapper.deleteByPrimaryKey(groupId);
            result.setResult("删除文件成功！");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    @Transactional
    public ExecuteResult<String> updateFileById(HttpServletRequest request,FileManage fileManage) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(fileManage.getId()==null){
                result.setResult("该文件不存在!");
                return result;
            }
            Long  modifyUserId = (Long) request.getSession().getAttribute("");//获取修改人id
            if(modifyUserId!=null){
                Date modifyTime = new Date();//获取当前时间
                fileManage.setModifyUserId(modifyUserId);
                fileManage.setModifyTime(modifyTime);
            }
            int i = fileManageMapper.updateByPrimaryKeySelective(fileManage);//文件修改
            if(i==0){
                result.setResult("文件修改失败!");
                return result;
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        result.setResult("文件修改成功!");
        return result;

    }

    @Override
    public ExecuteResult<PageInfo> selectFileByGroupID(FileManageGroup fileManageGroup,int currentPage,int pageSize) {
        ExecuteResult<PageInfo> result = new ExecuteResult<PageInfo>();
        try {
            Long id = fileManageGroup.getId();//获取文件group_id
            PageHelper.startPage(currentPage,pageSize);
            List<FileManage> fileList= fileManageMapper.selectFileByGroupID(id);//文件查询
            PageInfo<FileManage> fileManagePageInfo = new PageInfo<>(fileList);
            if (fileList.size() <= 0) {
                return result;
            }
            result.setResult(fileManagePageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}