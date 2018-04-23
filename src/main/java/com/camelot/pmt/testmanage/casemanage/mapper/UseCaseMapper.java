package com.camelot.pmt.testmanage.casemanage.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface UseCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UseCase record);

    int insertSelective(UseCase record);

    UseCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UseCase record);

    int updateByPrimaryKey(UseCase record);

    int updateUserCaseDelFlag(Long id);

    int insertBatch(List<UseCase> list);

    List<UseCase> queryAllUserCaseList(Map<String, Object> condition);

    /**
     * 项目关闭时，更新用例状态
     *
     * @param projectId
     * @param caseStatus
     * @param modifyUserId
     * @param modifyTime
     * @return
     */
    int updateByProjectId(@Param("projectId") Long projectId, @Param("caseStatus") String caseStatus,
            @Param("modifyUserId") String modifyUserId, @Param("modifyTime") Date modifyTime);
}