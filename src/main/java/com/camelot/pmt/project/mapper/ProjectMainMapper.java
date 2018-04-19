package com.camelot.pmt.project.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.project.model.ProjectMain;

public interface ProjectMainMapper {

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
     * 分页查询
     * 
     * @param projectMain
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
     * @param projectNum
     * @param projectName
     * @param projectStatus
     * @param projectDesc
     * @param startTime
     * @param endTime
     * @return
     */
    int updateByPrimaryKeySelective(@Param("id") Long id, @Param("userId") String userId,
            @Param("modifyUserId") String modifyUserId, @Param("modifyTime") Date modifyTime,
            @Param("projectName") String projectName, @Param("projectStatus") String projectStatus,
            @Param("projectDesc") String projectDesc, @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 获取数据库项目编号最大值
     * 
     * @return
     */
    String getMaxProjectNum();
}