package com.camelot.pmt.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.model.ProjectMain;

public interface ProjectMainMapper {

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
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(ProjectMain record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(ProjectMain record);

    /**
     * 根据主键进行更新
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