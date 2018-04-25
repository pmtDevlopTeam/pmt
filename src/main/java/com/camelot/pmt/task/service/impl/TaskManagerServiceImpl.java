package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.RoleToUserService;
import com.camelot.pmt.platform.service.UserService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskFileService;
import com.camelot.pmt.task.service.TaskLogService;
import com.camelot.pmt.task.service.TaskManagerService;
import com.camelot.pmt.task.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zlh
 * @date 2018/4/9 16:27
 */
@Service
@Transactional
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

    @Autowired
    private TaskLogService taskLogService;

    /**
     * 新增任务
     *
     * @param task
     * @param file
     * @return boolean
     * @author zlh
     * @date 9:10 2018/4/12
     */
    @Override
    public boolean addTask(Task task) {
        try {
            // check参数
            if (task == null) {
                throw new RuntimeException("参数错误");
            }
            String num = taskMapper.querySequence();
            task.setTaskNum(num);
            // 默认状态
            task.setStatus("0");
            // 根据当前登录用户查询的用户userid
            User user = (User)ShiroUtils.getSessionAttribute("user");
            String userId = user.getUserId();
            user.setUserId(userId);
            task.setCreateUser(user);
            int insertTaskResult = taskMapper.addTask(task);
            taskLogService.insertTaskLog(task.getId(),Constant.TaskLogOperationButton.CREATETASK.getValue(),"新增了任务");
            return (insertTaskResult == 1) ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id删除任务
     *
     * @param id
     *            需要删除的任务的id，isDeleteAll 是否删除子任务
     * @return boolean
     * @author zlh
     * @date 17:24 2018/4/12
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
            if (!user.getUsername().equals(createUserName)) {
                throw new RuntimeException("没有权限");
            }
            if (task.getBeassignUser() != null) {
                throw new RuntimeException("已指派的任务不能删除");
            }
            if (!"0".equals(status)) {
                throw new RuntimeException("已开始的任务不能删除");
            }
            // 删除任务
            int deleteTaskByIdResult = taskMapper.deleteTaskById(id);
            taskLogService.insertTaskLog(id,Constant.TaskLogOperationButton.DELETETASK.getValue(),"删除了任务");
            return deleteTaskByIdResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 编辑任务
     *
     * @param task
     *            任务修改内容
     * @author zlh
     * @date 17:05 2018/4/13
     */
    @Override
    public boolean updateTaskByTask(Task task) {
        try {
            // check参数
            if (task == null) {
                throw new RuntimeException("参数错误");
            }

            int updateTaskByIdResult = taskMapper.updateTaskById(task);
            taskLogService.insertTaskLog(task.getId(),Constant.TaskLogOperationButton.UPDATETASK.getValue(),"修改了任务");
            return (updateTaskByIdResult == 1) ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 编辑需求是否变更
     *
     * @param task
     *            任务修改内容
     * @author zlh
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
            taskLogService.insertTaskLog(task.getId(),Constant.TaskLogOperationButton.UPDATETASK.getValue(),"变更了需求");
            return updateTaskByIdResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 任务延期
     *
     * @param task
     *            需要修改的任务数据
     * @return boolean
     * @author zlh
     * @date 10:18 2018/4/12
     */
    @Override
    public boolean updateEstimateStartTimeById(Task task) {
        try {
            // check参数
            if (task == null) {
                throw new RuntimeException("参数错误");
            }

            // 检查权限
            Task task2 = taskMapper.queryTaskById(task.getId());
            String createUserName = task2.getCreateUser().getUsername();
            String beAssignUserName = task2.getBeassignUser().getUsername();
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (!user.getUsername().equals(createUserName) && !user.getUsername().equals(beAssignUserName)) {
                throw new RuntimeException("没有权限");
            }

            // 业务操作
            int updateTaskByIdResult = taskMapper.updateTaskById(task);
            taskLogService.insertTaskLog(task.getId(),Constant.TaskLogOperationButton.UPDATETASK.getValue(),"更改了预期完成时间");
            return updateTaskByIdResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 认领
     *
     * @param id
     *            需要修改的任务id
     * @return boolean
     * @author zlh
     * @date 11:36 2018/4/12
     */
    @Override
    public boolean updateBeAssignUserById(Long id) {
        try {
            // check参数
            if (id == null) {
                throw new RuntimeException("参数错误");
            }

            Task task = new Task();
            task.setId(id);
            User user = (User) ShiroUtils.getSessionAttribute("user");
            task.setBeassignUser(user);
            int updateTaskByIdResult = taskMapper.updateTaskById(task);
            taskLogService.insertTaskLog(task.getId(),Constant.TaskLogOperationButton.UPDATETASK.getValue(),"认领了任务");
            return updateTaskByIdResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据任务id查询任务详情
     *
     * @param id
     *            任务id
     * @author zlh
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
            // 添加任务信息到map
            Task task = taskMapper.queryTaskById(id);
            map.put("Task", task);

            // 附件
            TaskFile taskFile = new TaskFile();
            taskFile.setSourceId(id);
            taskFile.setAttachmentSource("任务");
            // 添加附件信息到map
            map.put("TaskFile", taskFileService.queryByTaskFile(taskFile));
            List<TaskLog> logs = taskLogService.queryTaskLogList(id);
            // 添加日志信息到map
            map.put("log", logs);

            return map;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有任务列表
     *
     * @param page
     *            当前页
     * @param rows
     *            一页有几行
     * @return PageInfo<Task>
     * @author zlh
     * @date 16:54 2018/4/9
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Map<String, List<Task>> queryAllTask() {
        Map<String, List<Task>> map = new HashMap<>();
        List<Task> pending = null;
        List<Task> running = null;
        List<Task> already = null;
        List<Task> close = null;
        try {
            List<Task> tasks = taskMapper.queryAllTask();
            for (Task task : tasks) {
                String status = task.getStatus();
                if ("0".equals(status)) {
                    pending = new ArrayList<>();
                    pending.add(task);
                }
                if ("1".equals(status)) {
                    running = new ArrayList<>();
                    running.add(task);
                }
                if ("2".equals(status)) {
                    already = new ArrayList<>();
                    already.add(task);
                }
                if ("3".equals(status)) {
                    close = new ArrayList<>();
                    close.add(task);
                }
            }
            map.put("pending",pending);
            map.put("running",running);
            map.put("already",already);
            map.put("close",close);
            return map;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据条件查询任务
     *
     * @param task 模糊查询的条件
     * @return PageInfo<Task>
     * @author zlh
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Map<String, List<Task>> queryTaskByTask(Task task) {
        Map<String, List<Task>> map = new HashMap<>();
        List<Task> pending = null;
        List<Task> running = null;
        List<Task> already = null;
        List<Task> close = null;
        try {
            // 检查参数
            if (task == null) {
                throw new RuntimeException("参数错误");
            }

            String[] ids = null;
            // 如果负责人条件非空，则根据username查询userId
            if (task.getBeassignUser() != null) {
                List<User> users = userService.queryUsersByUserName(task.getBeassignUser().getUsername());
                if (users.isEmpty()) {
                    return null;
                }
                ids = new String[users.size()];
                for (int i = 0; i < users.size(); i++) {
                    ids[i] = users.get(i).getUserId();
                }
            }

            List<Task> tasks = taskMapper.queryTaskByTask(task, ids);
            for (Task task1 : tasks) {
                String status = task1.getStatus();
                if ("0".equals(status)) {
                    pending = new ArrayList<>();
                    pending.add(task1);
                }
                if ("1".equals(status)) {
                    running = new ArrayList<>();
                    running.add(task1);
                }
                if ("2".equals(status)) {
                    already = new ArrayList<>();
                    already.add(task1);
                }
                if ("3".equals(status)) {
                    close = new ArrayList<>();
                    close.add(task1);
                }
            }
            map.put("pending",pending);
            map.put("running",running);
            map.put("already",already);
            map.put("close",close);
            return map;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
