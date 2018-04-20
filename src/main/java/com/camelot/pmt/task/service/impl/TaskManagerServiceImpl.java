package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.RoleToUserService;
import com.camelot.pmt.platform.service.UserService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.service.TaskFileService;
import com.camelot.pmt.task.service.TaskManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zlh
 * @date 2018/4/9 16:27
 */
@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManagerServiceImpl.class);

    @Autowired
    private TaskFileService taskFileService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private RoleToUserService roleToUserService;

    /**
     * 新增任务
     *
     * @author zlh
     * @param task
     * @param file
     * @date 9:10 2018/4/12
     * @return boolean
     */
    @Override
    public boolean insertTask(Task task, MultipartFile file) {
        try {
            // check参数
            if (task == null) {
            }
            // 默认状态下任务状态为未开始 0为未开始的状态码
            task.setStatus("0");
            User user = new User();
            /*这个是根据当前登录用户查询的用户userid*/
            String userId = "2";
            user.setUserId(userId);
            task.setCreateUser(user);
            int insertTaskResult = taskMapper.insertTask(task);
            // 如果任务有附件，上传附件
            if (file != null) {
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                String filePath = "D:/upload/";
                File targetFile = new File(filePath);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                FileOutputStream out = new FileOutputStream(filePath+fileName);
                out.write(bytes);
                out.flush();
                out.close();

                TaskFile taskFile = new TaskFile();
                // 附件名称
                taskFile.setAttachmentTile(fileName);
                // 附件路径
                taskFile.setAttachmentUrl(filePath);
                // 附件来源
                taskFile.setAttachmentSource("任务");
                // 来源id
                taskFile.setSourceId(task.getId());

                boolean insertTaskFileResult = taskFileService.insert(taskFile);
                return (insertTaskFileResult && insertTaskResult == 1) ? true : false;
            }
            return (insertTaskResult==1) ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id删除任务
     *
     * @author zlh
     * @param id 需要删除的任务的id，isDeleteAll 是否删除子任务
     * @date 17:24 2018/4/12
     * @return boolean
     */
    @Override
    public boolean deleteTaskById(Long id) {
        try {
            // check参数
            if (id == null) {
                throw new RuntimeException("参数错误");
            }
            // 检查权限
            Task task = taskMapper.queryTaskById(id);
            String createUserName = task.getCreateUser().getUsername();
            String status = task.getStatus();
            User user = (User) ShiroUtils.getSessionAttribute("user");
            // 只有创建人本人可以操作
            if (!user.getUsername().equals(createUserName)) {
                throw new RuntimeException("没有权限");
            }
            // 已经指派的任务只能关闭不能删除
            if (task.getBeassignUser() != null) {
                throw new RuntimeException("已指派的任务不能删除");
            }
            // 已经开始的任务不能删除
            if (!"0".equals(status)) {
                throw new RuntimeException("已开始的任务不能删除");
            }
            // 删除任务
            int deleteTaskByIdResult = taskMapper.deleteTaskById(id);

            return deleteTaskByIdResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 编辑任务
     *
     * @author zlh
     * @param  task 任务修改内容
     * @date 17:05 2018/4/13
     */
    @Override
    public boolean updateTaskByTask(Task task) {
        try {
            // check参数
            if (task == null) {
                throw new RuntimeException("参数错误");
            }
            // 检查权限
            Task task2 = taskMapper.queryTaskById(task.getId());
            String createUserName = task2.getCreateUser().getUsername();
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (!user.getUsername().equals(createUserName)) {
                throw new RuntimeException("没有权限");
            }
            int updateTaskByIdResult = taskMapper.updateTaskById(task);
            return  (updateTaskByIdResult==1) ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 编辑需求是否变更
     *
     * @author zlh
     * @param task 任务修改内容
     * @date 17:37 2018/4/13
     */
    @Override
    public boolean updateDemandChangeByTask(Task task) {
        try {
            // check参数
            if (task == null) {
                throw new RuntimeException("参数错误");
            }
            int updateTaskByIdResult = taskMapper.updateTaskById(task);
            return updateTaskByIdResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 任务延期
     *
     * @author zlh
     * @param task 需要修改的任务数据
     * @date 10:18 2018/4/12
     * @return boolean
     */
    @Override
    public boolean updateEstimateStartTimeById(Task task) {
        try {
            // check参数
            if (task == null) {
                throw new RuntimeException("参数错误");
            }

            //检查权限
            Task task2 = taskMapper.queryTaskById(task.getId());
            String createUserName = task2.getCreateUser().getUsername();
            String beAssignUserName = task2.getBeassignUser().getUsername();
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (!user.getUsername().equals(createUserName)
                    && !user.getUsername().equals(beAssignUserName)) {
                throw new RuntimeException("没有权限");
            }

            // 业务操作
            int updateTaskByIdResult = taskMapper.updateTaskById(task);
            return updateTaskByIdResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 指派
     *
     * @author zlh
     * @param  id 需要修改的任务id
     * @param userId 负责人的id
     * @date 11:36 2018/4/12
     * @return boolean
     */
    @Override
    public boolean updateBeAssignUserById(Long id, String userId) {
        try {
            // check参数
            if (id == null && userId == null || "".equals(userId)) {
                throw new RuntimeException("参数错误");
            }

            // 检测权限
            Task task = taskMapper.queryTaskById(id);
            String createUserName = task.getCreateUser().getUsername();
            String beAssignUsername = task.getBeassignUser().getUsername();
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (!user.getUsername().equals(createUserName) && !user.getUsername().equals(beAssignUsername)
                    && !"获取当前登录用户的角色".equals("项目经理")) {
                throw new RuntimeException("没有权限");
            }

            task.getBeassignUser().setUserId(userId);
            int updateTaskByIdResult = taskMapper.updateTaskById(task);
            return updateTaskByIdResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据任务id查询任务详情
     *
     * @author zlh
     * @param id 任务id
     * @return ExecuteResult<Map<String, Object>> String:数据的类型 Task（任务信息）和TaskFile（附件信息）
     * @date 17:08 2018/4/12
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryTaskById(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // check参数
            if (id == null) {
                throw new RuntimeException("参数错误");
            }
            Task task = taskMapper.queryTaskById(id);
            // 如果类型是需求的会有附件
            if ("需求".equals(task.getTaskType())) {
                TaskFile taskFile = new TaskFile();
                // 来源id
                taskFile.setSourceId(id);
                // 任务来源
                taskFile.setAttachmentSource("任务");

                // 添加附件信息到map
                map.put("TaskFile", taskFileService.queryByTaskFile(taskFile));
            }
            // 添加任务信息到map
            map.put("Task", task);
            return map;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有任务列表
     *
     * @author zlh
     * @param page  当前页
     * @param rows  一页有几行
     * @date 16:54 2018/4/9
     * @return PageInfo<Task>
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageInfo<Task> queryAllTask(Integer page, Integer rows) {
        try {
            PageHelper.startPage(page, rows);
            List<Task> tasks = taskMapper.queryAllTask();
            PageInfo<Task> pageInfo = new PageInfo<>(tasks);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据条件查询任务
     *
     * @author zlh
     * @param task 模糊查询的条件
     * @param page  当前页
     * @param rows  一页有几行
     * @return PageInfo<Task>
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageInfo<Task> queryTaskByTask(Task task, Integer page, Integer rows) {
        try {
            // 检查参数
            if (task == null) {
                throw new RuntimeException("参数错误");
            }
            String[] ids = null;
            if (task.getBeassignUser() != null) {
                // 如果负责人条件非空，则根据username查询userId
                List<User> users = userService.queryUsersByUserName(task.getBeassignUser().getUsername());
                // 赋值给string数组传给DAO层
                ids = new String[users.size()];
                for (int i = 0;i<users.size();i++) {
                    ids[i] = users.get(i).getUserId();
                }
            }
//            PageHelper.startPage(page, rows);(分页加上会报错，不知道为什么)
            List<Task> tasks = taskMapper.queryTaskByTask(task, ids);
            PageInfo<Task> pageInfo = new PageInfo<>(tasks);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
