package com.camelot.pmt.project.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.project.model.ProjectMain;

public interface ProjectMainMapper {
    /**
     * 查询所有公开项目
     * 
     * @return
     */
    List<ProjectMain> queryAllByPublic();

    /**
     * 根据用户id,查询每个项目成员参加的项目
     * 
     * @param userId
     * @return
     */
    List<ProjectMain> queryByUserIdPersonal(String userId);

    /**
     * 关闭项目时，更新相关表
     * 
     * @param id
     * @param projectStatus
     * @param modifyUserId
     * @param modifyTime
     * @return
     */
    int updateById(@Param("id") Long id, @Param("projectStatus") String projectStatus,
            @Param("modifyUserId") String modifyUserId, @Param("modifyTime") Date modifyTime);

    /**
     * 按修改人Id查询
     * 
     * @param modifyUserId
     * @return
     */
    List<ProjectMain> queryByModifyUserId(String modifyUserId);

    /**
     * 按创建人Id查询
     * 
     * @param createUserId
     * @return
     */
    List<ProjectMain> queryByCreateUserId(String createUserId);

    /**
     * 按负责人Id查询
     * 
     * @param userId
     * @return
     */
    List<ProjectMain> queryByUserId(String userId);

    /**
     * 按状态分类查询展示
     * 
     * @param projectStatus
     * @return
     */
    List<ProjectMain> queryByProjectStatus(String projectStatus);

    /**
     * 分页查询所有（包括个人私有的+公开的项目）
     * 
     * @return
     */
    List<ProjectMain> queryAllPublicAndPrivate(String userId);

    /**
     * 分页查询所有
     * 
     * @return
     */
    List<ProjectMain> queryAllByPage();

    /**
     * 根据id 删除项目
     * 
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据
     * 
     * @param projectMain
     * @return
     */
    int addProject(ProjectMain projectMain);

    /**
     * 根据项目id查询详情
     * 
     * @param id
     * @return
     */
    ProjectMain queryByPrimaryKey(Long id);

    /**
     * 根据按主键id更新数据
     * 
     * @param id
     * @param userId
     * @param modifyUserId
     * @param modifyTime
     * @param projectName
     * @param projectStatus
     * @param projectDesc
     * @param startTime
     * @param endTime
     * @param projectVisible
     * @return
     */
    int updateByPrimaryKeySelective(@Param("id") Long id, @Param("userId") String userId,
            @Param("modifyUserId") String modifyUserId, @Param("modifyTime") Date modifyTime,
            @Param("projectName") String projectName, @Param("projectStatus") String projectStatus,
            @Param("projectDesc") String projectDesc, @Param("startTime") Date startTime,
            @Param("endTime") Date endTime, @Param("projectVisible") String projectVisible);

    /**
     * 获取数据库项目编号最大值
     * 
     * @return
     */
    String getMaxProjectNum();

    /**
     * 挂起项目，只有开始的项目才可以挂起
     * 
     * @param id
     * @param modifyUserId
     * @param modifyTime
     * @param projectStatus
     * @return
     */
    int updateByIdSuspension(@Param("id") Long id, @Param("modifyUserId") String modifyUserId,
            @Param("modifyTime") Date modifyTime, @Param("projectStatus") String projectStatus);
}