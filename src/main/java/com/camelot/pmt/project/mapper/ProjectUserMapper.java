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
     * 通过主键删除
     * 
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据
     * 
     * @param record
     * @return
     */
    int insert(ProjectUser record);

    /**
     * 项目添加成员
     * 
     * @param record
     * @return
     */
    int addUserSelective(ProjectUser record);

    /**
     * 通过主键查询
     * 
     * @param id
     * @return
     */
    ProjectUser selectByPrimaryKey(Long id);

    /**
     * 修改，可选择性修改
     * 
     * @param record
     * @return
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

    /**
     * 查询成员数量
     * 
     * @param vo
     * @return
     */
    int count(ProjectUserSearchVO vo);

    /**
     * 项目关闭清除所有成员，暂时不用
     * 
     * @param projectId
     * @param userId
     * @param userStatus
     * @return
     */
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