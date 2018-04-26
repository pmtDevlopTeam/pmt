package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.task.mapper.TaskFileMapper;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.service.TaskFileService;
import com.camelot.pmt.task.utils.Constant;
import com.camelot.pmt.task.utils.Constant.AttachmentSource;
import com.camelot.pmt.task.utils.UploadUtils;
import org.apache.commons.lang3.StringUtils;
import com.camelot.pmt.task.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

/**
 * 附件相关接口
 *
 * @author zlh
 * @date 2018/4/17 10:24
 */
@Service
@Transactional
public class TaskFileServiceImpl implements TaskFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskFileServiceImpl.class);

    @Autowired
    private TaskFileMapper taskFileMapper;

    /**
     * 插入需求类型任务的附件元信息
     *
     * @param taskFile
     *            参数
     * @return boolean
     * @author zlh
     * @date 10:21 2018/4/17
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public boolean insert(TaskFile taskFile) {
        try {
            // check参数
            if (taskFile == null) {
                throw new RuntimeException("参数错误");
            }
            int insertResult = taskFileMapper.insert(taskFile);
            return insertResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据附件来源和来源id查询附件元信息
     *
     * @param taskFile
     *            查询需要的参数
     * @return TaskFile
     * @author zlh
     * @date 17:03 2018/4/17
     */
    @Override
    public TaskFile queryByTaskFile(TaskFile taskFile) {
        try {
            // check参数
            if (taskFile == null) {
                throw new RuntimeException("参数错误");
            }
            return taskFileMapper.queryByTaskFile(taskFile);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @Title: update @Description: TODO(修改附件上传) @param @param taskId @param @return
     *         设定文件 @return JSONObject 返回类型 @throws
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public boolean addOrupdate(Long id, TaskFile taskFile, MultipartFile file) {
        try {
            // check参数
            if (id == null || taskFile == null || file == null) {
                throw new RuntimeException("数据参数不能为空");
            }
            int result = 0;
            TaskFile taskObj = taskFileMapper.queryByTaskFile(taskFile);
            // 判断file是否存在
            if (taskObj == null) {
                taskFile.setAttachmentUrl(Constant.localPath);
                taskFile.setAttachmentSource(AttachmentSource.TASK.getValue());
                taskFile.setAttachmentTile(file.getOriginalFilename());
                taskFile.setSourceId(id);
                taskFileMapper.insert(taskFile);
            } else {
                String oldPath = taskObj.getAttachmentUrl() + "\\" + taskObj.getAttachmentTile();
                // 删除原文件
                UploadUtils.deleteFile(oldPath);
                taskObj.setAttachmentTile(file.getOriginalFilename());
                taskObj.setAttachmentUrl(Constant.localPath);
            }
            // 上传新文件
            String path = UploadUtils.uploadFile(file);
            if (!StringUtils.isEmpty(path)) {
                result = taskFileMapper.update(taskObj);
            }
            return result == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 附件下载
     *
     * @param taskFile
     *            参数
     * @author zlh
     * @date 17:03 2018/4/17
     */
    @Override
    public boolean download(TaskFile taskFile, HttpServletResponse response) {
        try {
            String fileName = taskFile.getAttachmentTile();
            String path = taskFile.getAttachmentUrl();
            if (StringUtils.isEmpty(fileName) && StringUtils.isEmpty(path)) {
                throw new RuntimeException("参数错误");
            }
            FileUtils.downloadFile(response, path, fileName);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("下载失败");
        }
    }
}
