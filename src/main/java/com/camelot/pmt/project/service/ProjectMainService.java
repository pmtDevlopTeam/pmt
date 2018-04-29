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
     * 新建项目时，保存数据
     * 
     * @param projectName
     * @param projectStatus
     * @param projectDesc
     * @return
     */
    int addProject(String projectName, String projectStatus, String projectDesc);

    /**
     * 分页查询所有
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
     * 按主键id对项目进行编辑
     * 
     * @param id
     * @param userId
     * @param projectName
     * @param projectStatus
     * @param projectDesc
     * @param startTime
     * @param endTime
     * @param budgetaryHours
     * @param projectVisible
     * @return
     */
    int updateByPrimaryKeySelective(Long id, String userId, String projectName, String projectStatus,
            String projectDesc, Date startTime, Date endTime, Integer budgetaryHours, String projectVisible);

    /**
     * 挂起项目 只有开始的项目才可以挂起
     * 
     * @param id
     * @param projectStatus
     * @return
     */
    int updateByIdSuspension(Long id, String projectStatus);

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

    /**
     * 查询所有项目（包括个人私有的+公开的项目）
     * 
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<ProjectMain> queryAllPublicAndPrivate(Integer currentPage, Integer pageSize);

    /**
     * 根据用户id,查询每个项目成员参加的项目
     * 
     * @return
     */
    List<ProjectMain> queryByUserIdPersonal();

    /**
     * 查询所有公开项目
     * 
     * @return
     */
    List<ProjectMain> queryAllByPublic();

    /**
     * 根据截止时间倒叙查询（包括个人私有的+公开的项目）
     * 
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<ProjectMain> queryAllOrderByEndTime(Integer currentPage, Integer pageSize);

    /**
     * 按项目状态分类查询（包括个人私有的+公开的项目）
     * 
     * @param projectStatus
     * @return
     */
    List<ProjectMain> queryAllByProjectStatus(String projectStatus);

}
