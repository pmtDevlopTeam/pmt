package com.camelot.pmt.project.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.project.mapper.VersionMapper;
import com.camelot.pmt.project.model.Version;
import com.camelot.pmt.project.model.VersionVo;
import com.camelot.pmt.project.service.VersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

/**
 * @Package: com.camelot.pmt.project.service.impl
 * @ClassName: VersionServiceImpl
 * @Description: 版本控制service实现类
 * @author: xueyj
 * @date: 2018-04-13 11:00
 */
@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionMapper versionMapper;

    /**
     * @Description: 添加版本信息
     * @param: version
     * @author: xueyj
     * @date: 2018/4/13 11:05
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addVersion(Long projectId, String userId, VersionVo versionVo) {
        Date dateTime = new Date();
        // 获取当前版本的类型与版本编号，用于自动生成版本编号
        String versionType = versionVo.getVersionType();
        Version version = new Version();
        // 设置版本名称
        version.setVersionName(versionVo.getVersionName());
        version.setVersionType(versionType);
        // 设置版本起止时间
        version.setStartTime(versionVo.getStartTime());
        version.setEndTime(versionVo.getEndTime());
        version.setRemarks(versionVo.getRemarks());
        // 设置版本编号
        version.setVersion(getVersionCode(projectId, versionType));
        // 项目id
        version.setProjectId(projectId);
        // 添加人id
        version.setCreateUserId(userId);
        // 修改人id
        version.setModifyUserId(userId);
        // 版本状态。0：正常使用；-1;逻辑删除
        version.setVersionStatus(0);
        version.setCreateTime(dateTime);
        version.setModifyTime(dateTime);
        return versionMapper.insert(version) == 1 ? true : false;
    }

    /**
     * @Description: 逻辑删除版本信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:16
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteVersionById(String userId, Long versionId) {
        Version version = versionMapper.selectByPrimaryKey(versionId);
        Date dateTime = new Date();
        // 设置版本修改人信息
        version.setModifyUserId(userId);
        version.setModifyTime(dateTime);
        // 设置版本状态
        version.setVersionStatus(-1);
        return versionMapper.updateByPrimaryKeySelective(version) == 1 ? true : false;
    }

    /**
     * @Description: 根据指定id查询版本信息
     * @param: version
     * @return: version1
     * @author: xueyj
     * @date: 2018/4/13 11:19
     */
    @Override
    public VersionVo queryVersionInfoById(Long versionId) {
        return assembleProductListVo(versionMapper.selectByPrimaryKey(versionId));
    }

    /**
     * @Description: 根据指定id修改version信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 18:49
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateVersonInfo(String userId, Version version) {
        Date dateTime = new Date();
        // 设置版本修改人及修改时间信息
        version.setModifyUserId(userId);
        version.setModifyTime(dateTime);
        return versionMapper.updateByPrimaryKeySelective(version) == 1 ? true : false;
        // 1.判断版本类型是否修正，若修正则根据相应的规则重新生成版本编号
        // Version version = versionMapper.selectByPrimaryKey(versionVo.getId());
        /*
         * String updateVersionType = versionVo.getVersionType(); String oldVersionType
         * = version.getVersionType(); if (!oldVersionType.equals(updateVersionType)){
         * // 获取当前版本的类型与版本编号，用于自动生成版本编号 String versionType = versionVo.getVersionType();
         * // 设置版本编号 version.setVersion(getVersionCode(projectId,versionType)); }
         */
        // version.setVersionName(versionVo.getVersionName());
        // version.setStartTime(versionVo.getStartTime());
        // version.setEndTime(versionVo.getEndTime());
        // version.setRemarks(versionVo.getRemarks());
        /*
         * if (i > 0) { return ApiResponse.success("修改版本成功！"); }else{ return
         * ApiResponse.error("修改版本失败！"); }
         */
    }

    /**
     * @Description: 根据项目id查询所有版本信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 18:30
     */
    @Override
    public List<VersionVo> queryVerListByProId(Long projectId, VersionVo versionVo) {
        List<Version> versionList = versionMapper.selectVersionListByProIdAndPram(projectId, versionVo);
        List<VersionVo> versionVoList = Lists.newArrayList();
        for (Version versionItem : versionList) {
            VersionVo versionVoInfo = assembleProductListVo(versionItem);
            versionVoList.add(versionVoInfo);
        }
        return versionVoList;
    }

    /**
     * @Description: 根据项目id,分页查询所有版本信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/17 10:26
     */
    @Override
    public PageInfo queryVerListByPageAndProId(int pageNum, int pageSize, Long projectId, VersionVo versionVo) {
        /*
         * pageHelper使用三部曲 1.启动pageHelper分页 startPage -- start 2.填充自己的sql（查询逻辑）
         * 3.pageHelper的收尾
         */
        // 初始化分页信息
        PageHelper.startPage(pageNum, pageSize);
        // 查询产品list
        List<Version> versionList = versionMapper.selectVersionListByProIdAndPram(projectId, versionVo);
        List<VersionVo> versionVoList = Lists.newArrayList();
        for (Version versionItem : versionList) {
            VersionVo versionVoInfo = assembleProductListVo(versionItem);
            versionVoList.add(versionVoInfo);
        }
        // pageHelper的收尾
        PageInfo pageResult = new PageInfo(versionList);
        pageResult.setList(versionVoList);
        return pageResult;
    }

    /**
     * 根据不同的版本状态自动生成不同的版本编号
     *
     * @param versionType
     * @return
     */
    private String getVersionCode(Long projectId, String versionType) {
        // 初始化版本编号位null；
        String versionCode = null;
        // 根据项目id，版本类型查询对应版本编号
        List<Version> versionList = versionMapper.queryListByProIdAndVerType(projectId, versionType);
        if (versionList.size() > 0) {
            // 获取最后添加版本信息的版本编号
            versionCode = versionList.get(0).getVersion();
        }
        if (("type01".equals(versionType))) {
            // 若版本code为空则设置默认值，否则根据规则进行递增
            if (versionCode == null) {
                versionCode = "1.0.0";
                return versionCode;
            } else {
                versionCode = generateVersionCode(versionCode);
            }
        }
        if ("type02".equals(versionType)) {
            // 若版本code为空则设置默认值，否则根据规则进行递增
            if (versionCode == null) {
                versionCode = "0.0.1";
                return versionCode;
            } else {
                versionCode = generateVersionCode(versionCode);
            }
        }
        return versionCode;
    }

    /**
     * 根据已有versionCode生成递增后的code
     *
     * @param versionCode
     * @return
     */
    private String generateVersionCode(String versionCode) {
        if (versionCode != null) {
            String[] split = versionCode.split("\\.");
            int maxVersion = Integer.parseInt(split[0]);
            int medVersion = Integer.parseInt(split[1]);
            int minVersion = Integer.parseInt(split[2]);
            int tempMinVersion = minVersion + 1;
            // 若小版本号超过一位数，则往前递增，一次类推
            if (tempMinVersion == 10) {
                int tempMedVersion = medVersion + 1;
                if (tempMedVersion == 10) {
                    int tempMaxVersion = maxVersion + 1;
                    versionCode = tempMaxVersion + "." + 0 + "." + 0;
                } else {
                    versionCode = maxVersion + "." + tempMedVersion + "." + 0;
                }
            } else {
                versionCode = maxVersion + "." + medVersion + "." + tempMinVersion;
            }
        }
        return versionCode;
    }

    /**
     * 构建返回版本基本信息
     *
     * @param version
     * @return
     */
    private VersionVo assembleProductListVo(Version version) {
        VersionVo versionVo = new VersionVo();
        // id
        versionVo.setId(version.getId());
        // 版本名称
        versionVo.setVersionName(version.getVersionName());
        // 版本类型
        versionVo.setVersionType(version.getVersionType());
        // 起止时间
        versionVo.setStartTime(version.getStartTime());
        versionVo.setEndTime(version.getEndTime());
        // 备注
        versionVo.setRemarks(version.getRemarks());
        return versionVo;
    }
}