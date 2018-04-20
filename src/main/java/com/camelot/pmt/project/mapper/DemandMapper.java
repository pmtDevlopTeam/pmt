package com.camelot.pmt.project.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.project.model.Demand;

public interface DemandMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(Demand record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(Demand record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    Demand selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(Demand record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(Demand record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(Demand record);

    /**
     * 查询需求分页
     * 
     * @param demand
     * @return
     */
    List<Demand> queryByPage(@Param(value = "demand") Demand demand);

    // Long queryCount(@Param(value = "demand") Demand demand);

    /**
     * 根据pid查询所有需求记录
     * 
     * @param id
     * @return
     */
    List<Demand> selectByPId(Long id);

    /**
     * 根据需求id查询所有引用用例
     * 
     * @param demandId
     * @return
     */
    Long findDemandUseCase(Long demandId);

    /**
     * 根据需求id查询所有bug引用
     * 
     * @param demandId
     * @return
     */
    Long fingDemandBug(Long demandId);

    /**
     * 根据需求id查询所有任务引用
     * 
     * @param demandId
     * @return
     */
    Long findDemandTask(Long demandId);

    /**
     * 批量删除需求
     * 
     * @param list
     * @return
     */
    int deleteByList(List<Long> list);

    /**
     * 关闭项目时，根据项目id修改需求状态
     *
     * @param projectId
     * @param demandStatus
     * @param closeReason
     * @param modifyUserId
     * @param modifyTime
     * @return
     */
    int updateByProjectId(@Param("projectId") Long projectId, @Param("demandStatus") String demandStatus,
            @Param("closeReason") String closeReason, @Param("modifyUserId") String modifyUserId,
            @Param("modifyTime") Date modifyTime);

    /**
     * 根据需求id查询影响需求变更的任务信息
     *
     * @param demandId
     * @return
     */
    List<Map<String, Object>> queryDemandTaskQuoteById(Long demandId);

    /**
     * 根据需求id查询影响需求变更的用例信息
     *
     * @param demandId
     * @return
     */
    List<Map<String, Object>> queryDemandUseCaseQuoteById(Long demandId);

    /**
     * 根据需求id查询影响变更需求的bug信息
     *
     * @param demandId
     * @return
     */
    List<Map<String, Object>> queryDemandBugQuoteById(Long demandId);

}