package com.camelot.pmt.project.service;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.model.Version;

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
     * @param version
     * @return
     */
    JSONObject insertVersonInfo(Long projectId, String userId, Version version);
}
