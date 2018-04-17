package com.camelot.pmt.task.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author zlh
 * @date 2018/4/9 16:25
 */
public interface TaskManagerService {

    /**
     * @author: zlh
     * @param:
     * @description: 查询所有任务列表
     * @date: 16:54 2018/4/9
     */
    ExecuteResult<List<Task>> queryAllTask();

    /**
     * @author: zlh
     * @param task 模糊查询的条件
     * @description: 根据条件查询任务222
     * @return
     */
    ExecuteResult<List<Task>> queryTaskByTask(Task task);

    /**
     * @author: zlh
     * @param:  taskManager 插入任务的数据
     * @description: 新增任务
     * @date: 9:10 2018/4/12
     */
    ExecuteResult<String> insertTask(Task task, MultipartFile file);

    /**
     * @author: zlh
     * @param: task 需要修改的任务数据
     * @description: 任务延期
     * @date: 10:18 2018/4/12
     */
    ExecuteResult<String> updateEstimateStartTimeById(Task task);

    /**
     * @author: zlh
     * @param:  taskManager 需要修改的任务数据
     * @description: 指派
     * @date: 11:36 2018/4/12
     */
    ExecuteResult<String> updateBeAssignUserById(Long id, String userId, boolean isAssignAll);

    /**
     * @author: zlh
     * @param: id 任务id
     * @description: 根据任务id查询任务详情
     * @return ExecuteResult<Map<String, Object>> String:数据的类型 Task（任务信息）和TaskFile（附件信息）
     *  Object：对应的数据
     * @date: 17:08 2018/4/12
     */
    ExecuteResult<Map<String, Object>> queryTaskById(Long id);

    /**
     * @author: zlh
     * @param: id 需要删除的任务的id，isDeleteAll 是否删除子任务
     * @description: 根据id删除任务
     * @date: 17:24 2018/4/12
     */
    ExecuteResult<String> deleteTaskById(Long id);

    /**
     * @author: zlh
     * @param:  taskManager 任务修改内容
     * @description: 编辑任务
     * @date: 17:05 2018/4/13
     */
    ExecuteResult<String> updateTaskByTask(Task task);

    /**
     * @author: zlh
     * @param: taskManager 任务修改内容
     * @description: 需求是否变更
     * @date: 17:37 2018/4/13
     */
    ExecuteResult<String> updateDemandChangeByTask(Task task);
}
