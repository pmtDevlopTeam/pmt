package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.TaskManager;
import com.camelot.pmt.task.service.TaskManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zlh
 * @date 2018/4/9 16:27
 */
@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManagerServiceImpl.class);

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
    public ExecuteResult<List<TaskManager>> queryAllTask() {
        ExecuteResult<List<TaskManager>> result = new ExecuteResult<List<TaskManager>>();
        try {
            List<TaskManager> taskManagers = taskMapper.queryAllTask();
            result.setResult(taskManagers);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @param taskManager 模糊查询的条件
     * @author: zlh
     * @description: 根据条件查询任务
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ExecuteResult<List<TaskManager>> queryTaskByTask(TaskManager taskManager) {
        ExecuteResult<List<TaskManager>> result = new ExecuteResult<List<TaskManager>>();
        try {
            // 检查参数
            if (taskManager == null) {
                result.addErrorMessage("参数有误");
                return result;
            }
            List<TaskManager> taskManagers = taskMapper.queryTaskByTask(taskManager);
            result.setResult(taskManagers);
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
    public ExecuteResult<String> insertTask(TaskManager taskManager) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            if (taskManager == null) {
                result.addErrorMessage("传入信息有误");
                return result;
            }
            // 默认状态下任务状态为未开始
            taskManager.setStatus("未开始的状态码");

            int insertTask = taskMapper.insertTask(taskManager);
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
    public ExecuteResult<String> updateEstimateStartTimeById(TaskManager taskManager) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (taskManager == null) {
                result.addErrorMessage("传入信息有误");
                return result;
            }

            //检查权限
            TaskManager taskManager2 = taskMapper.queryTaskById(taskManager.getId());
            String createUserName = taskManager2.getCreateUser().getUsername();
            String beAssignUserName = taskManager2.getBeassignUser().getUsername();
            if (!"当前登录用户name".equals(createUserName)
                    && !"当前登录用户name".equals(beAssignUserName)) {
                /*return 没有权限*/
            }

            // 业务操作
            int updateTaskById = taskMapper.updateTaskById(taskManager);
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
            TaskManager taskManager = taskMapper.queryTaskById(id);
            String createUserName = taskManager.getCreateUser().getUsername();
            String beAssignUsername = taskManager.getBeassignUser().getUsername();
            if (!"当前登录用户name".equals(createUserName) && !"当前登录用户name".equals(beAssignUsername)
                    && !"当前登录用户角色".equals("项目经理")) {
                /*return 没有权限*/
            }

            // 指派父任务
            taskManager.getBeassignUser().setUserId(userId);
            taskMapper.updateTaskById(taskManager);
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
     * @date: 17:08 2018/4/12
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ExecuteResult<TaskManager> queryTaskById(Long id) {
        ExecuteResult<TaskManager> result = new ExecuteResult<TaskManager>();
        try {
            // check参数
            if (id == null) {
                result.addErrorMessage("参数传入有误");
                return result;
            }
            result.setResult(taskMapper.queryTaskById(id));
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
            TaskManager taskManager = taskMapper.queryTaskById(id);
            String createUserName = taskManager.getCreateUser().getUsername();
            String status = taskManager.getStatus();
            if (!"当前登录用户名".equals(createUserName)) {
                /*return 没有权限*/
            }
            // 已经指派的任务只能关闭不能删除
            if (taskManager.getBeassignUser() != null) {
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
    public ExecuteResult<String> updateTaskByTask(TaskManager taskManager) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (taskManager == null) {
                result.addErrorMessage("参数传入有误");
                return result;
            }

            // 检查权限
            TaskManager taskManager2 = taskMapper.queryTaskById(taskManager.getId());
            String createUserName = taskManager2.getCreateUser().getUsername();
            if (!"当前登录用户名".equals(createUserName)) {
                /*return 没有权限*/
            }
            taskMapper.updateTaskById(taskManager);
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
    public ExecuteResult<String> updateDemandChangeByTask(TaskManager taskManager) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // check参数
            if (taskManager == null) {
                result.addErrorMessage("参数传入有误");
                return result;
            }
            taskMapper.updateTaskById(taskManager);
            result.setResult("修改成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
