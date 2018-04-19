package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

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
     * @author: zlh
     * @param:  taskManager 插入任务的数据
     * @date: 9:10 2018/4/12
     */
    ExecuteResult<String> insertTask(Task task, MultipartFile file);

    /**
     * 根据id删除任务
     *
     * @author: zlh
     * @param: id 需要删除的任务的id，isDeleteAll 是否删除子任务
     * @date: 17:24 2018/4/12
     */
    ExecuteResult<String> deleteTaskById(Long id);

    /**
     * 编辑任务
     *
     * @author: zlh
     * @param:  taskManager 任务修改内容
     * @description:
     * @date: 17:05 2018/4/13
     */
    ExecuteResult<String> updateTaskByTask(Task task);

    /**
     * 编辑需求是否变更
     *
     * @author: zlh
     * @param: taskManager 任务修改内容
     * @description:
     * @date: 17:37 2018/4/13
     */
    ExecuteResult<String> updateDemandChangeByTask(Task task);

    /**
     * 任务延期
     *
     * @author: zlh
     * @param: task 需要修改的任务数据
     * @date: 10:18 2018/4/12
     */
    ExecuteResult<String> updateEstimateStartTimeById(Task task);

    /**
     * 指派
     *
     * @author: zlh
     * @param:  taskManager 需要修改的任务数据
     * @date: 11:36 2018/4/12
     */
    ExecuteResult<String> updateBeAssignUserById(Long id, String userId, boolean isAssignAll);

    /**
     * 根据任务id查询任务详情
     *
     * @author: zlh
     * @param: id 任务id
     * @return ExecuteResult<Map<String, Object>> String:数据的类型 Task（任务信息）和TaskFile（附件信息）
     *  Object：对应的数据
     * @date: 17:08 2018/4/12
     */
    ExecuteResult<Map<String, Object>> queryTaskById(Long id);

    /**
     * 查询所有任务列表
     *
     * @author: zlh
     * @param: page  当前页
     * @param: rows  一页有几行
     * @date: 16:54 2018/4/9
     */
    ExecuteResult<PageInfo<Task>> queryAllTask(Integer page, Integer rows);

    /**
     * 根据条件查询任务
     *
     * @author: zlh
     * @param task 模糊查询的条件
     * @param: page  当前页
     * @param: rows  一页有几行
     * @return
     */
    ExecuteResult<PageInfo<Task>> queryTaskByTask(Task task, Integer page, Integer rows);


}
