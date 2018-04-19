package com.camelot.pmt.project.service;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.project.model.Version;
import com.camelot.pmt.project.model.VersionVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Package: com.camelot.pmt.project.service
 * @ClassName: VersionService
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-13 10:59
 */
public interface VersionService {
    /**
     * 新增版本信息
     * 
     * @param projectId
     * @param userId
     * @param versionVo
     * @return
     */
    boolean addVersion(Long projectId, String userId, VersionVo versionVo);

    /**
     * @Description: 逻辑删除版本信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:14
     */
    boolean updateVersionByIdAndParms(String versionStatus,String userId, Long versionId);

    /**
     * @Description: 根据versionid查询version信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:14
     */
    VersionVo queryVersionInfoById(Long versionId);

    /**
     * @Description: 根据指定id修改version信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:15
     */
    boolean updateVersonInfo(String userId, Version version);

    /**
     * @Description: 根据项目id查询版本信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:14
     */
    List<VersionVo> queryVerListByProId(Long projectId, VersionVo versionVo);

    /**
     * @Description: 根据项目id,分页查询版本信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/17 10:34
     */
    PageInfo queryVerListByPageAndProId(int pageNum, int pageSize, Long projectId, VersionVo versionVo);
}
