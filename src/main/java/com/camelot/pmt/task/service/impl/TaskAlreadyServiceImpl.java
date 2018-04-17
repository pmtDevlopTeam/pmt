package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskAlreadyService;
import com.camelot.pmt.task.utils.Constant;
import com.camelot.pmt.task.utils.DateUtils;
import com.camelot.pmt.task.utils.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TaskAlreadyServiceImpl implements TaskAlreadyService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskAlreadyServiceImpl.class);


    /**
     * @author: zsf
     * @param:
     * @description: 查询已完成任务
     * @date: 16:54 2018/4/9
     */
    public ExecuteResult<DataGrid<Map<String, Object>>> queryoverdueTaskAlready(Pager page, Long id) {
        ExecuteResult<DataGrid<Map<String, Object>>> result = new ExecuteResult<DataGrid<Map<String, Object>>>();
        try {
            List<Map<String, Object>> list = taskMapper.listTaskAlready(page, id);
            // 如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
                DataGrid<Map<String, Object>> dg = new DataGrid<Map<String, Object>>();
                result.setResult(dg);
                return result;
            }
            DataGrid<Map<String, Object>> dg = new DataGrid<Map<String, Object>>();
            dg.setRows(list);
            // 查询总条数
            Long total = taskMapper.queryAlreadyCount();
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @author: zsf
     * @param:
     * @description: 查询未完成任务的个数
     * @date: 10:17 2018/4/17
     */
    @Override
    public int queryUnfinishedTask(long projectId, String userId) {

        return taskMapper.queryUnfinishedTask(projectId, userId);
    }

    /**
     * @author: zsf
     * @param:
     * @description: 根据需求ID 查任务列表
     * @date: 10:17 2018/4/17
     */
    @Override
    public ExecuteResult<List<Task>> queryTaskByDemandId(long demandId) {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            List<Task> taskList = taskMapper.queryTaskByDemandId(demandId);
            result.setResult(taskList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     *
     * @Title: updateTaskPendingToDelay
     * @Description: TODO重做(我的已完成任务转为正在进行,会将该节点及节点下的所有子节点变为正在进行(不包括关闭的任务))
     * @param @param taskId taskStatus
     * @param @return    设定文件
     * @return JSONObject    返回类型
     * @throws
     */
    @Override
    public ExecuteResult<String> updateTaskAlreadyToRunning(Long id,String taskStatus,String delayDescribe,Date estimateStartTime) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(id==null || StringUtils.isEmpty(delayDescribe) || estimateStartTime == null){
                result.addErrorMessage("传入的参数有误!");
                return result;
            }
            //判断状态是否为已完成，如果是已完成更新为正在进行
            if(Constant.TaskStatus.ALREADY.getValue().equals(taskStatus)){
                //格式化日期格式为yyyy-mm-dd HH:mm:ss,根据id更新待办任务状态为延期
                //sql:update task set t.status = #{taskType,jdbcType=VARCHAR},t.delay_describe = #{delayDescribe,jdbcType=VARCHAR},t.estimate_start_time = #{estimateStartTime,jdbcType=TIMESTAMP} where t.id = #{id,jdbcType=BIGINT}
                if(!Constant.TaskStatus.CLOSE.getValue().equals(taskStatus)){
                    taskMapper.updateTaskAlreadyToRunning(id,taskStatus,delayDescribe, DateUtils.format(estimateStartTime,DateUtils.DATE_TIME_PATTERN));
                }
                //查询taskId下的所有子节点
                //select * from task t where <if test="id != null" >t.task_parent_id = #{id}</if> <if test="taskType != null" > and t.task_type = #{taskType,jdbcType=BIGINT} </if>
                List<Task> childTaskNodes = taskMapper.queryTaskListNodeByParentId(id,null);
                //遍历子节点
                if(childTaskNodes!=null && childTaskNodes.size()>0){
                    for(Task child : childTaskNodes){
                        //递归
                        //sql需要修改
                        //<if test="taskType != null" > and t.task_type = #{taskType,jdbcType=BIGINT} </if>
                        //<if test="beassignUserId != null" > and t.beassign_user_id = #{beassignUserId,jdbcType=BIGINT} </if>
                        //非关闭需要改为重做
                        updateTaskAlreadyToRunning(child.getId(),taskStatus,delayDescribe,estimateStartTime);
                    }
                }
            }
            result.setResult("重做任务成功！");
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
        }
        return result;
    }

}
