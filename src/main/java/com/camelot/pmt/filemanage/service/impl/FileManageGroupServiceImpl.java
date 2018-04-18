package com.camelot.pmt.filemanage.service.impl;

import com.camelot.pmt.filemanage.mapper.FileManageGroupMapper;
import com.camelot.pmt.filemanage.mapper.FileManageMapper;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageGroupService;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * FileManageGroup 表数据服务层接口实现类1

 */
@Service
public class FileManageGroupServiceImpl implements FileManageGroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileManageGroupServiceImpl.class);
    @Autowired
    private FileManageGroupMapper fileManageGroupMapper;
    @Autowired
    private FileManageMapper fileManageMapper;
    @Override
    public ExecuteResult<String> addFileManagerGroup(HttpServletRequest request, FileManageGroup fileManageGroup) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(fileManageGroup==null){
                result.addErrorMessage("传入的文件夹实体有误!");
                return result;
            }
            Long parentId = fileManageGroup.getParentId();
            if(parentId==null){//当添加的文件夹是父节点时
                fileManageGroup.setParentId((long) 0);
            }else{
                Long projectId = fileManageGroup.getProjectId();
                if(projectId!=null){//当添加的文件夹是子文件夹时
                    fileManageGroup.setProjectId(null);//如果是子文件夹的时候设置项目id（projectID）为null
                }
            }
            Long  createId= (Long) request.getSession().getAttribute("  ");//从session获取创建者
            if(createId!=null){
                Date date = new Date();
                fileManageGroup.setCreateTime(date);
            }

        /* SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        String createTime = format.format(date);//创建时间*/
            fileManageGroup.setCreateUserId(createId);
            fileManageGroup.setIsfile(0);
            fileManageGroupMapper.insertSelective(fileManageGroup);//添加结果
            result.setResult("添加文件夹成功!");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;

    }

    @Override
    public ExecuteResult<String> deleteFileGroup(FileManageGroup fileManageGroup) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            Long id = fileManageGroup.getId();//文件夹id
            fileManageGroupMapper.deleteByPrimaryKey(id);
            deleteFileGroupAndFileById(id);
            result.setResult("删除文件夹成功！");
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ExecuteResult<String> updateFileGroupById(HttpServletRequest request,FileManageGroup fileManageGroup) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(fileManageGroup.getId()==null){
                result.setResult("该文件夹不存在!");
                return result;
            }
            Date date = new Date();//修改时间
            Long  modifyUserID = (Long) request.getSession().getAttribute("");
            if(modifyUserID!=null){
                fileManageGroup.setModifyTime(date);
                fileManageGroup.setModifyUserId(modifyUserID);
            }
            int i = fileManageGroupMapper.updateByPrimaryKeySelective(fileManageGroup);//文件夹修改
                if(i==0){
                    result.setResult("文件夹修改失败!");
                    return result;
                }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        result.setResult("文件夹修改成功!");
        return result;
    }

   /* @Override
    public List<FileManageGroup> selectFileGroupByProjectID(FileManageGroup fileManageGroup) {
        Long projectId = fileManageGroup.getProjectId();
        List<FileManageGroup> filegrouplist= fileManageGroupMapper.selectFileGroupByProjectID(projectId);
        return filegrouplist;
    }*/

    @Override
    public ExecuteResult<List<FileManageGroup>> selectFileGroup(FileManageGroup fileManageGroup) {
        ExecuteResult<List<FileManageGroup>> result = new ExecuteResult<List<FileManageGroup>>();
        try {
            List<FileManageGroup> groupList=fileManageGroupMapper.selectFileGroup(fileManageGroup);
            if (groupList.size() <= 0) {
                return result;
            }
            result.setResult(groupList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }



    public void  deleteFileGroupAndFileById(Long id){
        List<Long> fileManageGroupIds=fileManageGroupMapper.selectFileManagerGroupByParentId(id);//获取子文件夹的id
     if(fileManageGroupIds.size()>0){
         fileManageGroupMapper.deleteBatchFileGroupById(fileManageGroupIds);//批量删除文件夹
     }
        List fileManagerIds= fileManageMapper.selectFileManagerByGroupId(id);//查询文件夹下的所有文件id
     if(fileManagerIds.size()>0){
         fileManageMapper.deleteBatchFileById(fileManagerIds);//删除文件夹下的文档
     }
       for (Long l:
                fileManageGroupIds) {
            deleteFileGroupAndFileById(l);
        }

    }
    @Override
    public List<FileManageGroup> queryTree(FileManageGroup fileManageGroup) {
        FileManageGroup fileManageGroup1=new FileManageGroup();
          List<FileManageGroup> groupList=fileManageGroupMapper.selectFileGroup(fileManageGroup);
            for (FileManageGroup group :
            groupList) {
                Long id = group.getId();
                fileManageGroup1.setParentId(id);
                List<FileManageGroup> children = this.digui(fileManageGroup1);
                group.setListGroup(children);
            }
        System.out.println(groupList);
        return groupList;
    }
    public List<FileManageGroup> digui(FileManageGroup fileManageGroup){
        FileManageGroup fileManageGroup1=null;
        List<FileManageGroup> groupList=fileManageGroupMapper.selectFileGroup(fileManageGroup);
        for (FileManageGroup group:
        groupList) {
            List<FileManageGroup> groupList1=fileManageGroupMapper.selectFileGroup(fileManageGroup);
            if (groupList1 != null && groupList1.size() != 0) {
                Long id = group.getId();
                fileManageGroup1.setParentId(id);
                group.setListGroup(this.digui(fileManageGroup1));
            } else {
                return groupList;
            }
        }
        return groupList;
    }
}
