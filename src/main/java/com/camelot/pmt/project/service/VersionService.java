package com.camelot.pmt.project.service;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.project.model.VersionVo;

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
    JSONObject addVersion(Long projectId, String userId, VersionVo versionVo);

    /**
     * @Description: 逻辑删除版本信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:14
     */
    JSONObject deleteVersionById(String userId, Long versionId);

    /**
     * @Description: 根据versionid查询version信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:14
     */
    JSONObject queryVersionInfoById(Long versionId);

    /**
     * @Description: 根据指定id修改version信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:15
     */
    JSONObject updateVersonInfo(Long projectId, String userId, VersionVo versionVo);

    /**
     * @Description: 根据项目id查询版本信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:14
     */
    JSONObject queryVerListByProId(Long projectId);

    /**
     * @Description: 根据项目id,分页查询版本信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/17 10:34
     */
    JSONObject queryVerListByPageAndProId(int pageNum, int pageSize, Long projectId);

    /**
     * @Description: 根据项目id、版本类型，查询versionList
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/17 9:46
     */
    JSONObject queryVerListByProIdAndVerType(Long projectId, String versionType);

    /**
     * @Description: 根据项目id、版本类型，分页查询versionList
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/17 10:34
     */
    JSONObject queryVerListByPageAndProIdAndVerType(int pageNum, int pageSize, Long projectId, String versionType);
}
