package com.camelot.pmt.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.project.mapper.ProjectUserMapper;
import com.camelot.pmt.project.model.ProjectUser;
import com.camelot.pmt.project.model.ProjectUserShow;
import com.camelot.pmt.project.service.ProjectUserService;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    /**
     * 确认成员进入项目
     */
    @Override
    public void confirmUser(ProjectUser pu) {
        projectUserMapper.confirmUser(pu);
    }

    @Override
    public void insertSelective(ProjectUser pu) {
        projectUserMapper.insertSelective(pu);
    }

    /**
     * 查询项目成员
     */
    @Override
    public List<ProjectUserShow> searchUserByProjectId(Long projectId) {
        return projectUserMapper.searchUserByProjectId(projectId);
    }

}
