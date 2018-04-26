package com.camelot.pmt.task.mapper;

import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskFile;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TaskMapper {

    /**
     * 查询正在进行的任务，根据时间和优先级进行排序 myp
     */
    List<Task> queryTaskRunning(Task task);

    /**
     * 将任务进行关闭操作 myp
     */
    int updateRunningToClose(Long id);

    /**
     * 将正在进行的任务进行完成操作 myp
     */
    int updateRunningToAlready(Long id);

    /**
     * 点击完成时，修改实际完成时间和实际工时 myp
     */
    int updateInfact_hourAndActual_end_time(Task task);

    /**
     * 点击完成时，添加附件 myp
     */
    int addAttachment(TaskFile taskFile);

    /**
     * 根据任务id查询所有的次id的子任务 myp
     */
    List<Task> queryByPId(Long pid);

    Task queryTaskAllById(Long id);

    /**
     * 根据id查询任务明细 myp
     */
    Task selectTaskById(Long id);

    /**
     * 新增任务
     *
     * @author zlh
     * @param task
     *            插入任务的数据
     * @date 9:07 2018/4/12
     */
    int addTask(Task task);

    /**
     * 根据任务删除id
     *
     * @author zlh
     * @param id
     *             需要删除的任务的id
     * @date 17:22 2018/4/12
     */
    int deleteTaskById(Long id);

    /**
     * 根据任务id修改任务
     *
     * @author zlh
     * @param task
     *            需要修改的任务数据
     * @date 10:18 2018/4/12
     */
    int updateTaskById(Task task);

    /**
     * 查询所有任务列表
     *
     * @author zlh
     * @date 16:54 2018/4/9
     */
    List<Task> queryAllTask();

    /**
     * 根据条件查询任务
     *
     * @author zlh
     * @param task
     *            模糊查询的条件
     * @return Task
     */
    List<Task> queryTaskByTask(@Param("task") Task task, @Param("ids") String[] ids);

    /**
     * 根据任务id查询任务详情
     *
     * @author zlh
     * @param id
     *            任务id
     * @date 17:08 2018/4/12
     */
    Task queryTaskById(Long id);

    /**
     * 查询下一个任务序列编号
     *
     * @author zlh
     * @date 14:43 2018/4/25
     * @return String
     */
    String querySequence();

    /**
     * 查询所有状态为正在进行的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    List<Task> queryTaskByStatusRunning(Long id);

    /**
     * 查询所有状态为待办的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    List<Task> queryTaskByStatusPending(Long id);

    /**
     * 查询所有状态为已办的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    List<Task> queryTaskByStatusAlready(Long id);

    /**
     * 查询所有状态为关闭的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    List<Task> queryTaskByStatusClose(Long id);

    /**
     * 查询延期任务个数 @Title: queryCount @Description: TODO @param @return @return
     * Long @throws
     */
    Long queryCount();

    /**
     * 查询延期任务列表+分页+排序+时间正序+优先级倒序 @Title: queryOverdueTask @Description:
     * TODO @param @param page @param @return @return List<Task> @throws
     */
    List<Map<String, Object>> queryOverdueTask(Task task);

    /**
     * @author: gxl @Title: insertTaskNodeId @Description: TODO(新增任务) @param @param
     *          task @param @return 设定文件 @return int 返回类型 @throws
     */
    int insertTaskNodeById(Task task);

    /**
     * @author: gxl @Title: updateTaskNodeId @Description: TODO(修改任务) @param @param
     *          task @param @return 设定文件 @return int 返回类型 @throws
     */
    int updateTaskNodeById(Task task);

    /**
     * @author: gxl @Title: queryTaskNodeById @Description: TODO(查询任务) @param @param
     *          id @param @return 设定文件 @return Task 返回类型 @throws
     */
    Task queryTaskNodeById(Long id);

    /**
     * @author: gxl @Title: deleteTaskNodeById @Description:
     *          TODO(这里用一句话描述这个方法的作用) @param @param id @param @return 设定文件 @return
     *          int 返回类型 @throws
     */
    int deleteTaskNodeById(Long id);

    /**
     * @author: gxl @Title: queryMyTaskTreeByTaskId @Description:
     *          TODO(查询taskId下的一级子节点) @param @param id status
     *          beassignUserId @param @return 设定文件 @return List<Task> 返回类型 @throws
     */
    List<Task> queryMyTaskListNodeByParentId(@Param("id") Long id, @Param("status") String status,
            @Param("beassignUserId") Long beassignUserId);

    /**
     * @author: gxl @Title: queryTaskListNodeByParentId @Description:
     *          TODO(查询taskId下的一级子节点) @param @param id status @param @return
     *          设定文件 @return List<Task> 返回类型 @throws
     */
    List<Task> queryTaskListNodeByParentId(@Param("id") Long id, @Param("status") String status);

    /**
     * @author: gxl @Title: queryAllTaskList @Description:
     *          TODO(查询整个任务表) @param @return 设定文件 @return List<Task> 返回类型 @throws
     */
    List<Task> queryAllTaskList(Task task);

    /**
     *
     * @Title: queryMyPendingTaskList @Description:
     *         TODO(查询我的待办Task任务列表) @param @param task @param @return 设定文件 @return
     *         List<Task> 返回类型 @throws
     */
    List<Task> queryMyPendingTaskList(Task task);

    /**
     * @author: gxl @Title: queryTopTaskNameList @Description:
     *          TODO(查询顶级我的待办任务) @param @return 设定文件 @return List<Task> 返回类型 @throws
     */
    List<Task> queryTopTaskNameList(@Param("status") String status, @Param("userId") Long userId);

    /**
     * @author: gxl @Title: updateTaskPendingToDelay @Description:
     *          TODO(我的待办任务转为延期,会将该节点及节点下的所有子节点变为延期状态) @param @param taskId
     *          taskType @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    void updateTaskPendingToDelay(@Param("id") Long id, @Param("status") String status,
            @Param("delayDescribe") String delayDescribe, @Param("estimateStartTime") Date estimateStartTime);

    /**
     * @author: gxl @Title: updateTaskStatus @Description:
     *          TODO(修改任务状态) @param @param taskId status @param @return 设定文件 @return
     *          int 返回类型 @throws
     */
    void updateTaskStatus(@Param("id") Long id, @Param("status") String status,@Param("modifyUserId") String modifyUserId,@Param("modifyTime") Date modifyTime);

    /**
     * @author: gxl @Title: taskParentId @Description:
     *          TODO(设置父Id为null) @param @param id @param @return 设定文件 @return int
     *          返回类型 @throws
     */
    void updateTaskParentIdIsNull(@Param("id") Long id);

    /**
     * @author: gxl @Title: updateTaskToAssign @Description:
     *          TODO(更新指派人和被指派人标识号) @param @param id @param @param
     *          assignUserId @param @param beassignUserId @param @return
     *          设定文件 @return int 返回类型 @throws
     */
    void updateTaskToAssign(@Param("id") Long id, @Param("assignUserId") Long assignUserId,
            @Param("beassignUserId") Long beassignUserId);

    /**
     * @Title: updateTaskPending @Description: TODO(修改待办任务) @param @param
     *         task @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    void updateTaskPending(@Param("id") Long id, @Param("taskDescribe") String taskDescribe);

    /**
     * @author: gxl @Title: queryParentTaskNodeById @Description:
     *          TODO(查询根据任务Id查询父级任务对象) @param @param taskParentId @param @return
     *          设定文件 @return Task 返回类型 @throws
     */
    Task queryParentTaskNodeById(Long id);

    /**
     *
     * @Title: updateTaskAlreadyToRunning @Description: TODO(重做) @param id
     *         status @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    int updateTaskAlreadyToRunning(@Param("id") Long id , @Param("status") String status);

    /**
     *
     * @Title: updateTaskPendingToRuning @Description:
     *         TODO(我的待办任务转为正在进行) @param @param taskId @param @return 设定文件 @return
     *         int 返回类型 @throws
     */
    void updateTaskToClose(Long id, String status);

    /**
     * 查询延期任务信息详情 @Title: queryOverdueTaskDetailByTaskId @Description:
     * TODO @param @param taskId @param @return @return TaskDetail @throws
     */
    Map<String, Object> queryOverdueTaskDetailByTaskId(String taskId);

    /**
     * 添加延期信息与预定开始时间 @Title: insertOverduMessage @Description: TODO @param @param
     * task @param @return @return Integer @throws
     */
    int insertOverduMessage(Task task);

    /**
     * 根据userId查询个人是否有延期任务 @Title: queryOverdueTaskUserId @Description:
     * TODO @param @param userId @param @return @return int @throws
     */
    int queryOverdueTaskUserId(String userId);

    /**
     * 根据userId,project 查询出未完成任务的个数 @Title: queryUnfinishedTask @Description:
     * TODO @param @param projectId,userId @param @return @return int @throws
     */
    int queryUnfinishedTask(@Param("projectId") Long projectId, @Param("userId") String userId);

    /**
     * 根据demandId 查询任务 @Title: queryUnfinishedTask @Description: TODO @param @param
     * demandId @param @return @return List<Task> @throws
     */
    List<Task> queryTaskByDemandId(long demandId);

    /**
     * 根据任务Id修改状态 @Title: updateTaskOverdueStatus @Description: TODO @param @param
     * taskId @param @return @return int @throws
     */
    int updateTaskOverdueStatus(String taskId);

    /**
     * 根据任务Id修改状态 @Title: updateTaskOverdueStatus @Description: TODO @param @param
     * taskId @param @return @return int @throws
     */
    int updateTaskToTest(@Param("id") Long id, @Param("beassignUserId") String beassignUserId,@Param("taskType")
                         String taskType ,@Param("status") String Status);

    /**
     * 根据任务Id查询需求ID @Title: updateTaskOverdueStatus @Description: TODO @param @param
     * taskId @param @return @return int @throws
     */
    Long queryTaskByTaskId(Long id);

    /**
     * 根据需求Id查询当前需求的测试人员ID @Title: updateTaskOverdueStatus @Description:
     * TODO @param @param taskId @param @return @return int @throws
     */
    String queryTaskToTestByDemandId(@Param("demandId") Long demandId,@Param("taskType")String taskType);

    /**
     * 查询延期提前列表 @Title: queryleaddeferredTaskRemindersList @Description:
     * TODO @param @param leadtime @param @return @return
     * List<Map<String,Object>> @throws
     */
    List<Map<String, Object>> queryleaddeferredTaskRemindersList(Integer leadtime);

    /**
     * 查询延期延后列表 @Title: querydelaytimedeferredTaskRemindersList @Description:
     * TODO @param @param delaytime @param @return @return
     * List<Map<String,Object>> @throws
     */
    List<Map<String, Object>> querydelaytimedeferredTaskRemindersList(Integer delaytime);

    /**
     * 项目关闭时，更新任务状态
     *
     * @param projectId
     * @param status
     * @param actualEndTime
     * @param modifyUserId
     * @param modifyTime
     * @return
     */
    int updateByProjectId(@Param("projectId") Long projectId, @Param("status") String status,
            @Param("actualEndTime") Date actualEndTime, @Param("modifyUserId") String modifyUserId,
            @Param("modifyTime") Date modifyTime);

    /**
     * 延时提前列表 @Title: queryleaddelayedTaskReminderList @Description:
     * TODO @param @param leadtime @param @return @return
     * List<Map<String,Object>> @throws
     */
    List<Map<String, Object>> queryleaddelayedTaskReminderList(Integer leadtime);

    /**
     * 延时延后列表 @Title: querydelaydelayedTaskReminderList @Description:
     * TODO @param @param delaytime @param @return @return
     * List<Map<String,Object>> @throws
     */
    List<Map<String, Object>> querydelaydelayedTaskReminderList(Integer delaytime);

    /**
     * 查询出我的任务 @Title: queryMyAllTask @Description: TODO @param @param Task
     * task @param @return @return List<Task> @throws
     */
    List<Task> queryMyAllTask(Task task);

    /**
     * 查询出我的任务 -- 流转到测试的开发任务 @Title: queryMyAlreadyTask @Description:
     * TODO @param @param Task task @param @return @return List<Task> @throws
     */
    List<Task> queryMyAlreadyTask(Task task);

    /**
     * 通过项目ID查询延时列表 @Title: querydelayedTaskReminderList @Description:
     * TODO @param @param projectId @param @return @return
     * List<Map<String,Object>> @throws
     */
    List<Map<String, Object>> querydelayedTaskReminderList(String projectId);

    /**
     * 通过项目ID查询延期列表 @Title: querydeferredTaskRemindersList @Description:
     * TODO @param @param projectId @param @return @return
     * List<Map<String,Object>> @throws
     */
    List<Map<String, Object>> querydeferredTaskRemindersList(String projectId);

    /**
     * 查询出我的已办任务
     * @Title: listTaskAlready
     * @Description: TODO
     * @param @param Task task
     * @param @return
     * @return List<Task>
     * @throws
     */
    List<Task> listTaskAlready(Task task);
}