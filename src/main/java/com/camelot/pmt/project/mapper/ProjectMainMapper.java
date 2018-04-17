package com.camelot.pmt.project.mapper;

import java.util.Date;
import java.util.List;

import com.camelot.pmt.common.Pager;
import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.project.model.ProjectMain;

public interface ProjectMainMapper {

    /**
     * 关闭时，按主键id更新数据
     * 
     * @param userId
     * @return
     */
    int updateById(@Param("id") Long id, @Param("projectStatus") String projectStatus,
            @Param("modifyUserId") String modifyUserId, @Param("modifyTime") Date modifyTime);

    /**
     * 按修改人Id查询
     * 
     * @param userId
     * @return
     */
    List<ProjectMain> findByModifyUserId(String modifyUserId);

    /**
     * 按创建人Id查询
     * 
     * @param userId
     * @return
     */
    List<ProjectMain> findByCreateUserId(String createUserId);

    /**
     * 按负责人Id查询
     * 
     * @param userId
     * @return
     */
    List<ProjectMain> findByUserId(String userId);

    /**
     * 按状态分类查询展示
     * 
     * @param projectStatus
     * @return
     */
    List<ProjectMain> findByProjectStatus(String projectStatus);

    /**
     * 分页查询
     * 
     * @param projectStatus
     * @return
     */
    List<ProjectMain> findAllByPage(@Param(value = "page") Pager<?> page);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(ProjectMain record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(ProjectMain record);

    /**
     * 根据项目id查询详情
     * 
     * @mbggenerated 2018-04-13
     */
    ProjectMain selectByPrimaryKey(Long id);

    /**
     * 根据按主键id更新数据
     * 
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(@Param("id") Long id, @Param("userId") String userId,
            @Param("modifyUserId") String modifyUserId, @Param("modifyTime") Date modifyTime,
            @Param("projectNum") String projectNum, @Param("projectName") String projectName,
            @Param("projectStatus") String projectStatus, @Param("projectDesc") String projectDesc,
            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(ProjectMain record);

    /**
     * 
     * 
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(ProjectMain record);

    /**
     * 查询所有数量
     * 
     * @return
     */
    Long findAll();
}