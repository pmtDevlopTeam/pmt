package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.VersionCitingHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.camelot.pmt.project.mapper
 * @ClassName: VersionCitingHistoryMapper
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-20 15:12
 */
@Repository
public interface VersionCitingHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VersionCitingHistory record);

    int insertSelective(VersionCitingHistory record);

    VersionCitingHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VersionCitingHistory record);

    int updateByPrimaryKey(VersionCitingHistory record);

    List<VersionCitingHistory> queryVersionCitingHistoryByParms(VersionCitingHistory versionCitingHistory);
}
