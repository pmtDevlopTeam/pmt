package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.service.TaskFileService;
import com.camelot.pmt.task.service.TaskManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zlh
 * @date 2018/4/9 16:27
 */
@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManagerServiceImpl.class);

    @Autowired
    private TaskFileService taskFileService;

    @Autowired
    private TaskMapper taskMapper;

    /**
     * @author: zlh
     * @param:
     * @description: 查询所有任务
     * @date: 16:54 2018/4/9
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ExecuteResult<List<Task>> queryAllTask() {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            List<Task> tasks = taskMapper.queryAllTask();
            result.setResult(tasks);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @param task 模糊查询的条件
     * @author: zlh
     * @description: 根据条件查询任务
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ExecuteResult<List<Task>> queryTaskByTask(Task task) {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            // 检查参数
            if (task == null) {
                result.addErrorMessage("参数有误");
                return result;
            }
            String[] ids = null;
            if (!StringUtils.isEmpty(task.getBeassignUser().getUsername())) {
                 /*如果负责人条件非空，则根据username查询userId*/
                 // 赋值给string数组传给DAO层
                 ids = null;
            }
            List<Task> tasks = taskMapper.queryTaskByTask(task, ids);
            result.setResult(tasks);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zlh
     * @param: taskManager 插入任务的数据
     * @description: 新增任务
     * @date: 9:10 2018/4/12
     */
    @Override
    public ExecuteResult<String> insertTask(Task task, MultipartFile file) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (task == null) {
                result.addErrorMessage("传入信息有误");
                return result;
            }
            // 如果任务类型为需求任务，上传附件
            if ("需求".equals(task.getTaskType())) {
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                /*写出到指定位置*/

                TaskFile taskFile = new TaskFile();
                // 附件名称
                taskFile.setAttachmentTile(fileName);
                // 附件路径
                taskFile.setAttachmentUrl("文件存储路径url");
                // 附件来源
                taskFile.setAttachmentSource("任务");
                // 来源id
                taskFile.setSourceId(task.getId());

                taskFileService.insert(taskFile);
            }

            // 默认状态下任务状态为未开始 0为未开始的状态码
            task.setStatus("0");
            int insertTask = taskMapper.insertTask(task);
            result.setResult("插入成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zlh
     * @param: taskManager 需要修改的任务数据
     * @description: 修改任务-任务延期
     * 权限：任务负责人和任务创建人操作
     * @date: 10:18 2018/4/12
     */
    @Override
    public ExecuteResult<String> updateEstimateStartTimeById(Task task) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (task == null) {
                result.addErrorMessage("传入信息有误");
                return result;
            }

            //检查权限
            Task task2 = taskMapper.queryTaskById(task.getId());
            String createUserName = task2.getCreateUser().getUsername();
            String beAssignUserName = task2.getBeassignUser().getUsername();
            if (!"当前登录用户name".equals(createUserName)
                    && !"当前登录用户name".equals(beAssignUserName)) {
                /*return 没有权限*/
            }

            // 业务操作
            int updateTaskById = taskMapper.updateTaskById(task);
            result.setResult("修改成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zlh
     * @param: id 需要指派的任务id，userId 负责人id, isAssignAll 是否一并指派子任务
     * @description: 给任务添加负责人——指派
     * 只能指派自己创建的或者负责人自己的任务
     * 项目经理可以指派所有人任务
     * @date: 11:36 2018/4/12
     */
    @Override
    public ExecuteResult<String> updateBeAssignUserById(Long id, String userId, boolean isAssignAll) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (id == null && userId == null) {
                result.addErrorMessage("参数传入有误");
                return result;
            }

            // 检测权限
            Task task = taskMapper.queryTaskById(id);
            String createUserName = task.getCreateUser().getUsername();
            String beAssignUsername = task.getBeassignUser().getUsername();
            if (!"当前登录用户name".equals(createUserName) && !"当前登录用户name".equals(beAssignUsername)
                    && !"当前登录用户角色".equals("项目经理")) {
                /*return 没有权限*/
            }

            // 指派父任务
            task.getBeassignUser().setUserId(userId);
            taskMapper.updateTaskById(task);
            // 一并指派子任务
            if (isAssignAll) {
                // 根据父id查询所有的子任务id
                List<Long> ids = taskMapper.querySubTaskIdByParantId(id);
                // 如果未查询到子任务则返回
                if (ids.isEmpty()) {
                    return result;
                }
                // 遍历所有子任务进行指派
                for (Long subId : ids) {
                    // 递归查询子任务是否还有子任务
                    updateBeAssignUserById(subId, userId, isAssignAll);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zlh
     * @param: id 任务id
     * @description: 根据任务id查询任务详情
     * @return ExecuteResult<Map<String, Object>> String:数据的类型 Task（任务信息）和TaskFile（附件信息）
     *  Object：对应的数据
     * @date: 17:08 2018/4/12
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ExecuteResult<Map<String, Object>> queryTaskById(Long id) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // check参数
            if (id == null) {
                result.addErrorMessage("参数传入有误");
                return result;
            }
            Task task = taskMapper.queryTaskById(id);
            // 如果类型是需求的会有附件
            if ("需求".equals(task.getTaskType())) {
                TaskFile taskFile = new TaskFile();
                // 来源id
                taskFile.setSourceId(id);
                // 任务来源
                taskFile.setAttachmentSource("任务");
                ExecuteResult<TaskFile> taskFileExecuteResult = taskFileService.queryByTaskFile(taskFile);
                TaskFile taskFileResult = taskFileExecuteResult.getResult();
                // 添加附件信息到map
                map.put("TaskFile", taskFileResult);
            }
            // 添加任务信息到map
            map.put("Task", task);
            result.setResult(map);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zlh
     * @param: id 需要删除的任务的id，isDeleteAll 是否删除子任务
     * @description: 根据id删除任务（只能删除自己新建的且没有开始的任务，已经指派的任务只能关闭不能删除）
     * @date: 17:24 2018/4/12
     */
    @Override
    public ExecuteResult<String> deleteTaskById(Long id) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (id == null) {
                result.addErrorMessage("参数传入有误");
                return result;
            }

            // 检查权限
            Task task = taskMapper.queryTaskById(id);
            String createUserName = task.getCreateUser().getUsername();
            String status = task.getStatus();
            if (!"当前登录用户名".equals(createUserName)) {
                /*return 没有权限*/
            }
            // 已经指派的任务只能关闭不能删除
            if (task.getBeassignUser() != null) {
                /*return 不能删除已经指派的任务*/
            }
            // 已经开始的任务不能删除
            if ("开始任务状态码".equals(status)) {
                /*return 已经开始的任务不能删除*/
            }

            // 删除父任务
            taskMapper.deleteTaskById(id);
            // 根据父id查询所有的子任务id
            List<Long> ids = taskMapper.querySubTaskIdByParantId(id);
            if (ids.isEmpty()) {
                return result;
            }
            // 递归删除所有子任务
            for (Long subId : ids) {
                deleteTaskById(subId);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zlh
     * @param: taskManager 任务修改内容
     * @description: 编辑任务
     * 权限：只有任务的创建人可以进行编辑；
     * @date: 17:05 2018/4/13
     */
    @Override
    public ExecuteResult<String> updateTaskByTask(Task task) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (task == null) {
                result.addErrorMessage("参数传入有误");
                return result;
            }

            // 检查权限
            Task task2 = taskMapper.queryTaskById(task.getId());
            String createUserName = task2.getCreateUser().getUsername();
            if (!"当前登录用户名".equals(createUserName)) {
                /*return 没有权限*/
            }
            taskMapper.updateTaskById(task);
            result.setResult("修改成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zlh
     * @param: taskManager 任务修改内容
     * @description: 需求是否变更
     * @date: 17:37 2018/4/13
     */
    @Override
    public ExecuteResult<String> updateDemandChangeByTask(Task task) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (task == null) {
                result.addErrorMessage("参数传入有误");
                return result;
            }
            taskMapper.updateTaskById(task);
            result.setResult("修改成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
