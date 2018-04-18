package com.camelot.pmt.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.project.mapper.ProjectUserMapper;
import com.camelot.pmt.project.model.ProjectUser;
import com.camelot.pmt.project.model.ProjectUserSearchVO;
import com.camelot.pmt.project.model.ProjectUserShow;
import com.camelot.pmt.project.service.ProjectUserService;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Override
    public void addUserSelective(ProjectUser pu) {
    	projectUserMapper.addUserSelective(pu);
    }
    
    @Override
    public void confirmUser(ProjectUser pu) {
        projectUserMapper.confirmUser(pu);
    }

    @Override
    public int clearUser(Long projectId, String userId, String userStatus) {
    	return projectUserMapper.clearUserAll(projectId, userId, userStatus);
    }
    
    @Override
    public void clearUser(Map<String, Object> map) {
    	projectUserMapper.clearUser(map);
    	
    }

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
    

	public String getLoginUser() {
		User user = (User) ShiroUtils.getSessionAttribute("user");
		if (user != null) {
			return user.getUserId();
		}
		return "";
	}


}
