package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.Version;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(Version record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(Version record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    Version selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(Version record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(Version record);

    /**
     * 根据项目id查询当前项目下的版本信息
     * @param projectId
     * @return
     */
    List<Version> selectVersionListByProId(Long projectId);

    /**
     * 根据项目id，版本类型查询版本信息
     * @param projectId
     * @param versionType
     * @return
     */
    List<Version> queryListByProIdAndVerType(@Param("projectId") Long projectId,@Param("versionType") String versionType);
}