package com.camelot.pmt.project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.mapper.VersionMapper;
import com.camelot.pmt.project.model.Version;
import com.camelot.pmt.project.service.VersionService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Package: com.camelot.pmt.project.service.impl
 * @ClassName: VersionServiceImpl
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-13 11:00
 */
@Service
@Slf4j
public class VersionServiceImpl implements VersionService{
    @Autowired
    private  VersionMapper versionMapper;
    /**
      * @Description: 添加版本信息
      * @param:  version
      * @author: xueyj
      * @date: 2018/4/13 11:05
      */
    public JSONObject insertVersonInfo(Long projectId, String userId, Version version){
        // 获取当前版本的类型与版本编号，用于自动生成版本编号
        String versionType = version.getVersionType();
        // 根据项目id，版本类型查询对应版本编号
        String versionCode = versionMapper.getVetsionCode(projectId,versionType);
        // 设置版本编号
        version.setVersion(getVersionCode(versionCode,versionType));
        // 项目id
        version.setProjectId(projectId);
        // 添加人id
        version.setCreateUserId(userId);
        // 修改人id
        version.setModifyUserId(userId);
        int i = versionMapper.insertSelective(version);
        if (i > 0) {
            return  ApiResponse.success("新增版本成功！");
        }else{
            return  ApiResponse.error("新增版本失败！");
        }
    }

    /**
      * @Description: 根据指定id查询版本信息
      * @param: version
      * @return: version1
      * @author: xueyj
      * @date: 2018/4/13 11:19
      */
    public JSONObject getVersionInfo(Long versionId){
        Version version1 = versionMapper.selectByPrimaryKey(versionId);
         return  ApiResponse.success(version1);
    }

    /**
     * TODO : 待实现
     * @param versionId
     * @return
     */
    public JSONObject getVersionListInfo(Long versionId){
        Version version = versionMapper.selectByPrimaryKey(versionId);
        return  ApiResponse.success(version);
    }
    /**
     * 根据不同的版本状态自动生成不同的版本编号
     * @param versionType
     * @return
     */
    private String getVersionCode(String versionCode, String versionType){
        if ("开发".equals(versionType)) {
            // 若版本code为空则设置默认值，否则根据规则进行递增
            if (versionCode == null) {
                versionCode="1.0.0";
                return versionCode;
            }else{
                versionCode = generateVersionCode(versionCode);
            }
        }
        if ("测试".equals(versionType)) {
            // 若版本code为空则设置默认值，否则根据规则进行递增
            if (versionCode == null) {
                versionCode="0.0.1";
                return versionCode;
            }else{
                versionCode = generateVersionCode(versionCode);
            }
        }
        return versionCode;
    }

    /**
     * 根据已有versionCode生成递增后的code
     * @param versionCode
     * @return
     */
    private String generateVersionCode(String versionCode){
        if (versionCode != null) {
            String[] split = versionCode.split("\\.");
            int maxVersion = Integer.parseInt(split[0]);
            int medVersion = Integer.parseInt(split[1]);
            int minVersion = Integer.parseInt(split[2]);
            int tempMinVersion = minVersion+1;
            // 若小版本号超过一位数，则往前递增，一次类推
            if (tempMinVersion ==10){
                int tempMedVersion = medVersion+1;
                if (tempMedVersion == 10){
                    int tempMaxVersion= maxVersion+1;
                    versionCode= tempMaxVersion+"\\."+0+"\\."+0;
                }else{
                    versionCode= maxVersion+"\\."+tempMedVersion+"\\."+0;
                }
            }else{
                versionCode= maxVersion+"\\."+medVersion+"\\."+tempMinVersion;
            }
        }
        return versionCode;
    }

}
