package com.camelot.pmt.project.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.project.model.ProjectUser;
import com.camelot.pmt.project.model.ProjectUserSearchVO;
import com.camelot.pmt.project.model.ProjectUserShow;

public interface ProjectUserMapper {
    /**
     * 根据项目id更新项目成员状态
     * 
     * @param projectId
     * @param realOutTime
     * @param userStatus
     * @param modifyUserId
     * @param modifyTime
     * @return
     */
    int updateUserStatusByProjectId(@Param("projectId") Long projectId, @Param("realOutTime") Date realOutTime,
            @Param("userStatus") String userStatus, @Param("modifyUserId") String modifyUserId,
            @Param("modifyTime") Date modifyTime);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(ProjectUser record);

    /**
     * 项目添加成员
     * 
     * @mbggenerated 2018-04-13
     */
    int addUserSelective(ProjectUser record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    ProjectUser selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(ProjectUser record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(ProjectUser record);

    /**
     * 确认成员进入项目
     * 
     * @param pu
     * @return
     */
    void confirmUser(ProjectUser pu);

    /**
     * 查询成员
     * 
     * @param projectId
     * @return
     */
    List<ProjectUserShow> searchUserByProjectId(Long projectId);

    /**
     * 成员条件查询
     * 
     * @param vo
     * @return
     */
    List<ProjectUserShow> searchUserByCondition(ProjectUserSearchVO vo);

    int count(ProjectUserSearchVO vo);

    int clearUserAll(@Param("projectId") Long projectId, @Param("userId") String userId,
            @Param("userStatus") String userStatus);

    /**
     * 将人员状态修改为暂离
     * 
     * @param map
     */
    void clearUser(Map<String, Object> map);

    // ----------------------附加-------------------
    // 关于将成员移出项目
    /**
     * 查询task表，根据所有的id进行批量操作，如果成功则返回查询的条数
     * 
     * @param map
     * @return
     */
    int checkTask(Map<String, Object> map);

    /**
     * 查询task表，根据所有的id进行批量操作，如果成功则返回查询的条数
     * 
     * @param map
     * @return
     */
    int checkBug(Map<String, Object> map);

}