package com.camelot.pmt.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.project.mapper.ProjectUserMapper;
import com.camelot.pmt.project.model.ProjectUser;
import com.camelot.pmt.project.model.ProjectUserSearchVO;
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
    public void addUserSelective(ProjectUser pu) {
        projectUserMapper.addUserSelective(pu);
    }

    /**
     * 查询项目成员
     */
    @Override
    public List<ProjectUserShow> searchUserByProjectId(Long projectId) {
        return projectUserMapper.searchUserByProjectId(projectId);
    }

    @Override
    public List<ProjectUserShow> searchProUserByCondition(ProjectUserSearchVO vo) {
        return projectUserMapper.searchUserByCondition(vo);
    }

    @Override
    public int count(ProjectUserSearchVO vo) {
        return projectUserMapper.count(vo);
    }

	@Override
	public int clearUser(Long projectId, String userId, String userStatus) {
		return projectUserMapper.clearUserAll(projectId, userId, userStatus);
	}

	@Override
	public void clearUser(Map<String, Object> map) {
		projectUserMapper.clearUser(map);
		
	}

}
