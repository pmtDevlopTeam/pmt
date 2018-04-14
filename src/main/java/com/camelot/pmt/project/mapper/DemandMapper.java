package com.camelot.pmt.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandWithBLOBs;

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
    int insert(DemandWithBLOBs record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(DemandWithBLOBs record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    DemandWithBLOBs selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(DemandWithBLOBs record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(DemandWithBLOBs record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(Demand record);

    /**
     * 查询需求分页
     * 
     * @param demandWithBLOBs
     * @return
     */
    List<DemandWithBLOBs> findAllByPage(@Param(value = "pager") Pager pager,
            @Param(value = "demandWithBLOBs") DemandWithBLOBs demandWithBLOBs);

    Long queryCount(DemandWithBLOBs record);
}