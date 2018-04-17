package com.camelot.pmt.project.mapper;

import java.util.List;

import com.camelot.pmt.common.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.camelot.pmt.project.model.Version;
import com.camelot.pmt.project.model.VersionVo;

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

    List<Version> queryListByProIdAndVerType(@Param("projectId") Long projectId,
            @Param("versionType") String versionType);

    List<Version> selectVersionListByProId(Long projectId);

    List<VersionVo> findAllByPage(@Param(value = "page") Pager<?> page);

    List<Version> selectList();
}