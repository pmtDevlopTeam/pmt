package com.camelot.pmt.filemanage.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.filemanage.mapper.FileManageGroupMapper;
import com.camelot.pmt.filemanage.mapper.FileManageMapper;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageGroupService;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;

/**
 *
 * FileManageGroup 表数据服务层接口实现类1
 * 
 */
@Service
public class FileManageGroupServiceImpl implements FileManageGroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileManageGroupServiceImpl.class);
    @Autowired
    private FileManageGroupMapper fileManageGroupMapper;
    @Autowired
    private FileManageMapper fileManageMapper;
    @Autowired
    private ShiroUtils shiroUtils;

    @Override
    @Transactional
    public Boolean addFileManagerGroup(HttpServletRequest request, FileManageGroup fileManageGroup) {// 添加文件夹
        Long parentId = fileManageGroup.getParentId();
        if (parentId == null) {// 当添加的文件夹是父节点时
            fileManageGroup.setParentId((long) 0);
        } else {
            Long projectId = fileManageGroup.getProjectId();
            if (projectId != null) {// 当添加的文件夹是子文件夹时
                fileManageGroup.setProjectId(null);// 如果是子文件夹的时候设置项目id（projectID）为null
            }
        }
        User user = (User) shiroUtils.getSessionAttribute("user");// 获取用户id
        if (user != null) {
            if (user.getUserId() != null) {
                Date date = new Date();// 获取按当前时间
                fileManageGroup.setCreateTime(date);
            }
            fileManageGroup.setCreateUserId(user.getUserId());
        }

        fileManageGroup.setIsfile(0);
        int i = fileManageGroupMapper.insertSelective(fileManageGroup);// 添加结果
        Boolean b = true;
        if (i <= 0) {
            b = false;
        }
        return b;
    }

    @Override
    @Transactional
    public Boolean deleteFileGroup(FileManageGroup fileManageGroup) {
        Boolean b = null;
        Long id = fileManageGroup.getId();// 文件夹id
        int i = fileManageGroupMapper.deleteByPrimaryKey(id);
        b = deleteFileGroupAndFileById(id);
        return b;
    }

    public Boolean deleteFileGroupAndFileById(Long id) {// 删除文件夹和文件
        List<Long> fileManageGroupIds = fileManageGroupMapper.selectFileManagerGroupByParentId(id);// 获取子文件夹的id
        if (fileManageGroupIds.size() > 0) {
            fileManageGroupMapper.deleteBatchFileGroupById(fileManageGroupIds);// 批量删除文件夹
        }
        List fileManagerIds = fileManageMapper.selectFileManagerByGroupId(id);// 查询文件夹下的所有文件id
        if (fileManagerIds.size() > 0) {
            fileManageMapper.deleteBatchFileById(fileManagerIds);// 删除文件夹下的文档
        }
        if (fileManageGroupIds.size() > 0) {
            for (Long l : fileManageGroupIds) {
                deleteFileGroupAndFileById(l);
            }
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean updateFileGroupById(HttpServletRequest request, FileManageGroup fileManageGroup) {// 修改文件夹
        Date date = new Date();// 修改时间
        User user = (User) shiroUtils.getSessionAttribute("user");// 获取用户id
        if (user != null) {
            if (user.getUserId() != null) {
                fileManageGroup.setModifyTime(date);
                fileManageGroup.setModifyUserId(user.getUserId());
            }
        }

        int i = fileManageGroupMapper.updateByPrimaryKeySelective(fileManageGroup);// 文件夹修改
        Boolean b = true;
        if (i <= 0) {
            b = false;
        }
        return b;
    }

    @Override
    public List<FileManageGroup> querytFileGroup(FileManageGroup fileManageGroup) {
        List<FileManageGroup> groupList = fileManageGroupMapper.querytFileGroup(fileManageGroup);
        return groupList;
    }

    @Override
    public List<FileManageGroup> queryTree(FileManageGroup fileManageGroup) {// 根据项目id查询所有节点
        FileManageGroup fileManageGroup1 = new FileManageGroup();
        List<FileManageGroup> groupList = fileManageGroupMapper.querytFileGroup(fileManageGroup);
        for (FileManageGroup group : groupList) {
            Long id = group.getId();
            fileManageGroup1.setParentId(id);
            List<FileManageGroup> children = this.digui(fileManageGroup1);
            group.setListGroup(children);
        }
        System.out.println(groupList);
        return groupList;
    }

    public List<FileManageGroup> digui(FileManageGroup fileManageGroup) {// 查询递归
        FileManageGroup fileManageGroup1 = new FileManageGroup();// 创建对象
        List<FileManageGroup> groupList = fileManageGroupMapper.querytFileGroup(fileManageGroup);// 查询子节点
        for (FileManageGroup group : // 遍历子节点
        groupList) {
            Long id1 = group.getId();// 获取id
            fileManageGroup1.setParentId(id1);
            List<FileManageGroup> groupList1 = fileManageGroupMapper.querytFileGroup(fileManageGroup1);// 查询子节点
            if (groupList1 != null && groupList1.size() != 0) {// 判断集合是否为空
                group.setListGroup(this.digui(fileManageGroup1));
            } else {
                return groupList;
            }
        }
        return groupList;
    }
}
