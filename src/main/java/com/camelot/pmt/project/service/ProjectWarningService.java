/**
 * @Package: com.camelot.pmt.project.service 
 * @author: jh 
 * @date: 2018年4月12日 下午5:16:34 
 */
package com.camelot.pmt.project.service;

import com.camelot.pmt.common.ExecuteResult;

/**
 * @ClassName: ProjectWarningService
 * @Description: TODO
 * @author lixiaokang
 * @date 2018年4月12日 下午5:16:34
 */
public interface ProjectWarningService {

    /**
     * @param param
     * @return
     */
    ExecuteResult<String> startProjectWarning(String param);
}
