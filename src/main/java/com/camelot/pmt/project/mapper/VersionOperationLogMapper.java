package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.VersionOperationLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.camelot.pmt.project.mapper
 * @ClassName: versionOperationLogMapper
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-20 15:12
 */
@Repository
public interface VersionOperationLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VersionOperationLog record);

    int insertSelective(VersionOperationLog record);

    VersionOperationLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VersionOperationLog record);

    int updateByPrimaryKey(VersionOperationLog record);

    List<VersionOperationLog> queryversionOperationLogByParms(VersionOperationLog versionOperationLog);
}