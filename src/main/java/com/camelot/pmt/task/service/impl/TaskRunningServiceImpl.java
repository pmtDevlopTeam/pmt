package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskRunningService;
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

/**
 * @author muyuanpei
 * @date 2018/4/10    15:20
 */

@Service
public class TaskRunningServiceImpl implements TaskRunningService{

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;


    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRunningServiceImpl.class);


    public ExecuteResult<DataGrid<Map<String, Object>>> queryoverdueTaskRunning(Pager page, String id) {
        ExecuteResult<DataGrid<Map<String, Object>>> result = new ExecuteResult<DataGrid<Map<String, Object>>>();
        try {
            List<Map<String, Object>> list = taskMapper.listTaskRunning(page, id);
            // 如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
                DataGrid<Map<String, Object>> dg = new DataGrid<Map<String, Object>>();
                result.setResult(dg);
                return result;
            }
            DataGrid<Map<String, Object>> dg = new DataGrid<Map<String, Object>>();
            dg.setRows(list);
            // 查询总条数
            Long total = taskMapper.queryRunningCount();
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * <p>
     * Description:[根据id获取单个任务明细]
     * <p>
     *
     * @return ExecuteResult<Task>
     */
    @Override
    public ExecuteResult<Task> queryTaskById(Long id) {
        ExecuteResult<Task> result = new ExecuteResult<Task>();
        try {
            if (!id.equals("") && !id.equals("0")) {
                Task queryResult = taskMapper.selectTaskById(id);
                result.setResult(queryResult);
                return result;
            }
            result.addErrorMessage("查询失败！");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * <p>
     * Description:[添加历史记录]
     * <p>
     *
     * @return ExecuteResult<long>
     */
    @Override
    public ExecuteResult<Long> saveHistoryLog(TaskLog taskLog) {
        ExecuteResult<Long> result = new ExecuteResult<Long>();
        try {
            Long updateStatusResult = taskMapper.saveHistoryLog(taskLog);
            result.setResult(updateStatusResult);
            return result;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @Title: updateTaskPendingToRuning
     * @Description: TODO(我的任务转为关闭)
     * @param @param taskId taskStatus
     * @param @return    设定文件
     * @return JSONObject    返回类型
     * @throws
     */
    @Override
    public ExecuteResult<String> updateTaskToClose(Long id,String taskStatus) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(id==null){
                result.addErrorMessage("传入的任务Id有误!");
                return result;
            }
                //根据id更新任务状态为关闭
                //sql:update task set t.status = #{taskType,jdbcType=VARCHAR} where t.id = #{id,jdbcType=BIGINT}
                taskMapper.updateTaskToClose(id,taskStatus);
                //查询taskId下的所有子节点
                //select * from task t where t.task_parent_id = #{id}
                Task parentTaskNodes = taskMapper.queryParentTaskNodeById(id);
                //判断是否有父节点
                if(parentTaskNodes!=null){
                    //递归
                    return updateTaskToClose(parentTaskNodes.getId(),parentTaskNodes.getStatus());
                }
            result.setResult("关闭任务成功！");
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RRException(e.getMessage(),e);
        }
        return result;
    }

    /**
     *
     * @Title: updateTaskPendingToDelay
     * @Description: TODO(我的已完成任务转为正在进行,会将该节点及节点下的所有子节点变为正在进行(不包括关闭的任务))
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
