package com.camelot.pmt.project.service;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.model.Version;
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
     * @param projectId
     * @param userId
     * @param versionVo
     * @return
     */
    JSONObject insertVersonInfo(Long projectId, String userId, VersionVo versionVo);

    /**
      * @Description: 根据项目id查询版本信息
      * @param:
      * @return:
      * @author: xueyj
      * @date: 2018/4/13 19:14
      */
    JSONObject getVersionListInfo(Long projectId);

    /**
      * @Description: 根据versionid查询version信息
      * @param:
      * @return:
      * @author: xueyj
      * @date: 2018/4/13 19:14
      */
    JSONObject getVersionInfoById(Long versionId);

    /**
      * @Description: 根据指定id修改version信息
      * @param:
      * @return:
      * @author: xueyj
      * @date: 2018/4/13 19:15
      */
    JSONObject updateVersonInfo(Long projectId,String userId, VersionVo versionVo);
    /**
      * @Description: 逻辑删除版本信息
      * @param:
      * @return:
      * @author: xueyj
      * @date: 2018/4/13 19:14
      */
    JSONObject deleteVersionInfoById(String userId,Long versionId);
}
