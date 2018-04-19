package com.camelot.pmt.project.service;

import java.util.List;
import java.util.Map;

import com.camelot.pmt.project.model.ProjectUser;
import com.camelot.pmt.project.model.ProjectUserSearchVO;
import com.camelot.pmt.project.model.ProjectUserShow;

public interface ProjectUserService {


    /**
     * 项目添加成员
     * 
     * @param pu
     */
    void addUserSelective(ProjectUser pu);

    /**
     * 确认成员进入项目
     * 
     * @param pu
     * @return
     */
    void confirmUser(ProjectUser pu);
    
    /**
     * 项目关闭，将成员清除出项目（暂时不用）
     * 
     * @param projectId
     * @param userId
     * @param userStatus
     * @return
     */
    int clearUser(Long projectId, String userId, String userStatus);
    
    /**
     * 将人员移出项目，状态改为暂离
     * 
     * @param map
     */
    void clearUser(Map<String, Object> map);
    
    /**
     * 查询项目成员
     * 
     * @param projectId
     * @return
     */
    List<ProjectUserShow> searchUserByProjectId(Long projectId);
    

    /**
     * 条件查询
     * 
     * @param vo
     * @return
     */
    List<ProjectUserShow> searchProUserByCondition(ProjectUserSearchVO vo);

    /**
     * 查询总数
     * 
     * @param vo
     * @return
     */
    int count(ProjectUserSearchVO vo);

    /**
     * 获取用户信息
     * @return
     */
    public String getLoginUser();

}
