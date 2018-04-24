package com.camelot.pmt.task.service;

import org.springframework.web.multipart.MultipartFile;
import com.camelot.pmt.common.ExecuteResult;
import javax.servlet.http.HttpServletResponse;
import com.camelot.pmt.task.model.TaskFile;

/**
 * @author zlh
 * @date 2018/4/17 10:20
 */
public interface TaskFileService {

    /**
     * 插入需求类型任务的附件元信息
     *
     * @author zlh
     * @param taskFile
     *            参数
     * @date 10:21 2018/4/17
     * @return boolean
     */
    boolean insert(TaskFile taskFile);

    /**
     * 
     * @Title: update @Description: TODO(文件修改) @param @param taskFile @param @return
     * 设定文件 @return boolean 返回类型 @throws
     */
    boolean addOrupdate(Long id, TaskFile taskFile, MultipartFile file);

    /**
     * 根据附件来源和来源id查询附件元信息
     *
     * @author zlh
     * @param taskFile
     *            查询需要的参数
     * @date 17:03 2018/4/17
     * @return TaskFile
     */
    TaskFile queryByTaskFile(TaskFile taskFile);

    /**
     * 附件下载
     *
     * @author zlh
     * @param taskFile
     *            参数
     * @date 17:03 2018/4/17
     * @return boolean
     */
    boolean download(TaskFile taskFile, HttpServletResponse response);
}
