package com.camelot.pmt.project.mapper;

import java.util.List;

import com.camelot.pmt.common.Pager;
import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.project.model.DemandOperate;

public interface DemandOperateMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    DemandOperate selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(DemandOperate record);

    /**
     * 根据条件查询关联的记录
     * 
     * @param demandOperate
     * @return
     */
    List<DemandOperate> findAllByPage(@Param(value = "pager") Pager<?> pager,
            @Param(value = "demandOperate") DemandOperate demandOperate);

    Long queryCount(@Param(value = "demandOperate") DemandOperate demandOperate);
}