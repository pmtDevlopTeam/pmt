package com.camelot.pmt.project.service;

import java.util.Date;
import java.util.List;

import com.camelot.pmt.project.model.ProjectMain;

/**
 * 
 * @author qiaodj
 * @date 2018年4月14日
 */
public interface ProjectMainService {
    /**
     * 根据项目主键查询
     * 
     * @param id
     * @return
     */
    ProjectMain queryByPrimaryKey(Long id);

    /**
     * 保存立项时相关联表数据
     * 
     * @param userId
     * @param projectName
     * @param projectStatus
     * @param projectDesc
     * @param startTime
     * @param endTime
     * @return
     */
    int addProject(String userId, String projectName, String projectStatus, Date startTime, Date endTime,
            String projectDesc);

    /**
     * 分页查询
     * 
     * @param pageSize
     * @param currentPage
     * @return
     */
    List<ProjectMain> queryAllByPage(Integer pageSize, Integer currentPage);

    /**
     * 按状态分类查询
     * 
     * @param projectStatus
     * @return
     */
    List<ProjectMain> queryByProjectStatus(String projectStatus);

    /**
     * 按负责人id查询
     * 
     * @param userId
     * @return
     */
    List<ProjectMain> queryByUserId(String userId);

    /**
     * 按创建人id查询
     * 
     * @param createUserId
     * @return
     */
    List<ProjectMain> queryByCreateUserId(String createUserId);

    /**
     * 按修改人id查询
     * 
     * @param modifyUserId
     * @return
     */
    List<ProjectMain> queryByModifyUserId(String modifyUserId);

    /**
     * 按主键id进行更新
     * 
     * @param id
     * @param userId
     * @param projectName
     * @param projectStatus
     * @param projectDesc
     * @param startTime
     * @param endTime
     * @return
     */
    int updateByPrimaryKeySelective(Long id, String userId, String projectName, String projectStatus,
            String projectDesc, Date startTime, Date endTime);

    /**
     * 根据id删除项目
     * 
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 关闭项目时，更新相关表
     * 
     * @param id
     * @param projectStatus
     * @param userStatus
     * @param demandStatus
     * @param closeReason
     * @param status
     * @param caseStatus
     * @return
     */
    int updateByProjectById(Long id, String projectStatus, String userStatus, String demandStatus, String closeReason,
            String status, String caseStatus);

}
