/**
 * @Package: com.camelot.pmt.project.service.impl 
 * @author: jh 
 * @date: 2018年4月12日 下午5:17:15 
 */
package com.camelot.pmt.project.service.impl;

import org.springframework.stereotype.Service;

import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.common.CommonsUtil;
import com.camelot.pmt.project.model.Warning;
import com.camelot.pmt.project.service.ProjectWarningService;

/**
 * @ClassName: ProjectWarningServiceImpl
 * @Description: 项目开启预警
 * @author lixiaokang
 * @date 2018年4月12日 下午5:17:15
 */
@Service
public class ProjectWarningServiceImpl implements ProjectWarningService {

    /**
     * 开启项目预警-------待定
     */
    @Override
    public ExecuteResult<String> startProjectWarning(String param) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        Warning projectWarning = CommonsUtil.jsonToObject(param, Warning.class);
        Long id = projectWarning.getId();
        System.err.println(id);
        result.setResultMessage("成功");
        return result;
    }

}
