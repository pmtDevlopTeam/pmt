package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.Version;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VersionMapper {
    /**
     *
     * @mbggenerated 2018-04-16
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-16
     */
    int insert(Version record);

    /**
     *
     * @mbggenerated 2018-04-16
     */
    int insertSelective(Version record);

    /**
     *
     * @mbggenerated 2018-04-16
     */
    Version selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-16
     */
    int updateByPrimaryKeySelective(Version record);

    /**
     *
     * @mbggenerated 2018-04-16
     */
    int updateByPrimaryKeyWithBLOBs(Version record);

    /**
     *
     * @mbggenerated 2018-04-16
     */
    int updateByPrimaryKey(Version record);

    List<Version> queryListByProIdAndVerType(@Param("projectId") Long projectId, @Param("versionType") String versionType);

    List<Version> selectVersionListByProId(Long projectId);
}