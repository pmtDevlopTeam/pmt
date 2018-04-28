package com.camelot.pmt.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.project.model.RemindContentChild;

public interface RemindContentChildMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(RemindContentChild record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(RemindContentChild record);

    /**
     *
     * @mbggenerated
     */
    RemindContentChild selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RemindContentChild record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RemindContentChild record);

    int deleteByProjectId(Long projectId);

    List<RemindContentChild> queryByContentId(Long id);

    int deleteByContentId(List<RemindContentChild> remindContentChildList);

    /**
     * 根据项目id、提醒内容id查询子内容信息
     * 
     * @param projectId
     * @param contentId
     * @return
     */
    List<RemindContentChild> queryByProjectIdAndByContentId(@Param("projectId") Long projectId,
            @Param("contentId") Long contentId);
}