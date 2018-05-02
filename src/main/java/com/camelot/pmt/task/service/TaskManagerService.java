package com.camelot.pmt.task.service;

import com.camelot.pmt.task.model.Task;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 任务管理模块接口类
 *
 * @author zlh
 * @date 2018/4/9 16:25
 */
public interface TaskManagerService {

    /**
     * 新增任务
     *
     * @author zlh
     * @param task
     *            插入的数据
     * @date 9:10 2018/4/12
     * @return boolean
     */
    boolean addTask(Task task);

    /**
     * 根据id删除任务
     *
     * @author zlh
     * @param id
     *            需要删除的任务的id，isDeleteAll 是否删除子任务
     * @date 17:24 2018/4/12
     * @return boolean
     */
    boolean deleteTaskById(Long id);

    /**
     * 编辑任务
     *
     * @author zlh
     * @param task
     *            任务修改内容
     * @date 17:05 2018/4/13
     * @return boolean
     */
    boolean updateTaskByTask(Task task);

    /**
     * 编辑需求是否变更
     *
     * @author zlh
     * @param task
     *            任务修改内容
     * @date 17:37 2018/4/13
     * @return boolean
     */
    boolean updateDemandChangeByTask(Task task);

    /**
     * 任务延期
     *
     * @author zlh
     * @param task
     *            需要修改的任务数据
     * @date 10:18 2018/4/12
     * @return boolean
     */
    boolean updateEstimateStartTimeById(Task task);

    /**
     * 认领
     *
     * @author zlh
     * @param id
     *            需要修改的任务id
     * @date 11:36 2018/4/12
     * @return boolean
     */
    boolean updateBeAssignUserById(Long id);

    /**
     * 根据任务id查询任务详情
     *
     * @author zlh
     * @param id
     *            任务id
     * @return ExecuteResult<Map<String, Object>> String:数据的类型
     *         Task（任务信息）和TaskFile（附件信息）
     * @date 17:08 2018/4/12
     */
    Map<String, Object> queryTaskById(Long id);

    /**
     * 查询所有任务列表
     *
     * @author zlh
     * @date 16:54 2018/4/9
     * @return PageInfo<Task>
     */
    Map<String, List<Task>> queryAllTask();

    /**
     * 根据条件查询任务
     *
     * @author zlh
     * @param task
     *            模糊查询的条件
     * @return PageInfo<Task>
     */
    Map<String, List<Task>> queryTaskByTask(Task task);

    /**
     * 查询所有状态为正在进行的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    PageInfo<Task> queryTaskByStatusRunning(int page, int rows, Long id);

    /**
     * 查询所有状态为待办的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    PageInfo<Task> queryTaskByStatusPending(int page, int rows, Long id);

    /**
     * 查询所有状态为已办的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    PageInfo<Task> queryTaskByStatusAlready(int page, int rows, Long id);

    /**
     * 查询所有状态为关闭的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    PageInfo<Task> queryTaskByStatusClose(int page, int rows, Long id);

    /**
     * 查询所有可认领的任务
     *
     * @author zlh
     * @return List<Task>
     */
    List<Task> queryTaskByBeAssignIsNull();

    /**
     * 组合条件查询所有状态为正在进行的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    PageInfo<Task> queryTaskStatusRunningByTask(Task task, int page, int rows);

    /**
     * 组合条件查询所有状态为待办的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    PageInfo<Task> queryTaskStatusPendingByTask(Task task, String status, int page, int rows);

    /**
     * 组合条件查询所有状态为已办的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    PageInfo<Task> queryTaskStatusAlreadyByTask(Task task, int page, int rows);

    /**
     * 组合条件查询所有状态为关闭的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    PageInfo<Task> queryTaskStatusCloseByTask(Task task, int page, int rows);

}
