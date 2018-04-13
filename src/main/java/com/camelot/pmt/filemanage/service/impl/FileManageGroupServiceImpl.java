package com.camelot.pmt.filemanage.service.impl;

import com.camelot.pmt.filemanage.mapper.FileManageGroupMapper;
import com.camelot.pmt.filemanage.mapper.FileManageMapper;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageGroupService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * FileManageGroup 表数据服务层接口实现类
 *张战
 */
@Service
public class FileManageGroupServiceImpl implements FileManageGroupService {
    @Autowired
    private FileManageGroupMapper fileManageGroupMapper;
    @Autowired
    private FileManageMapper fileManageMapper;
    @Override
    public Boolean addFileManagerGroup(HttpServletRequest request, FileManageGroup fileManageGroup) {
        Long parentId = fileManageGroup.getParentId();
        if(parentId==null){//当添加的文件夹是父节点时
            fileManageGroup.setParentId((long) 0);
        }else{
            Long projectId = fileManageGroup.getProjectId();
            if(projectId!=null){
                fileManageGroup.setProjectId(null);//如果是子文件夹的时候设置项目id（projectID）为null
            }
        }
        Long  createId= (Long) request.getSession().getAttribute("  ");//从session获取创建者
        Date date = new Date();
        /* SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        String createTime = format.format(date);//创建时间*/
        fileManageGroup.setCreateUserId(createId);
        fileManageGroup.setCreateTime(date);
        fileManageGroup.setIsfile(0);
        int status = fileManageGroupMapper.insertSelective(fileManageGroup);//添加结果
        Boolean b=true;
        if(status==0){
            b=false;
        }
        return b;
    }

    @Override
    public Boolean deleteFileGroup(FileManageGroup fileManageGroup) {
        Long count = null;//删除记录数
        Long id = fileManageGroup.getId();//文件夹id
        int i = fileManageGroupMapper.deleteByPrimaryKey(id);
    /* List fileManageGroupIds=fileManageGroupMapper.selectFileManagerGroupByParentId(id);//查询id下的所有子文件夹id
        fileManageGroupIds.add(id);//添加当前文件夹id到集合
        fileManageGroupMapper.deleteBatchFileGroupById();//批量删除文件夹
       List fileManagerIds= fileManageMapper.selectFileManagerByGroupId(id);//查询文件夹下的所有文件id
        fileManageMapper.deleteBatchFileById();*/
        try{
            deleteFileGroupAndFileById(id);
            return true;
        }catch (Exception e){
            System.out.println("删除失败");
            return false;
        }

    }

    @Override
    public Boolean updateFileGroupById(HttpServletRequest request,FileManageGroup fileManageGroup) {
        Date date = new Date();//修改时间
        Long  modifyUserID = (Long) request.getSession().getAttribute("");
        fileManageGroup.setModifyTime(date);
        fileManageGroup.setModifyUserId(modifyUserID);
        int i = fileManageGroupMapper.updateByPrimaryKeySelective(fileManageGroup);//文件夹修改
        Boolean b=true;
        if(i==0){
            b=false;
        }
        return b;
    }

   /* @Override
    public List<FileManageGroup> selectFileGroupByProjectID(FileManageGroup fileManageGroup) {
        Long projectId = fileManageGroup.getProjectId();
        List<FileManageGroup> filegrouplist= fileManageGroupMapper.selectFileGroupByProjectID(projectId);
        return filegrouplist;
    }*/

    @Override
    public List<FileManageGroup> selectFileGroup(FileManageGroup fileManageGroup) {
        Long parentId = fileManageGroup.getParentId();
        List<FileManageGroup> groupList=fileManageGroupMapper.selectFileGroup(fileManageGroup);
        return groupList;
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
}
