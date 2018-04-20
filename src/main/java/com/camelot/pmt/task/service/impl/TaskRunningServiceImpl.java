package com.camelot.pmt.task.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskRunningService;
import com.camelot.pmt.task.utils.Constant;
import com.camelot.pmt.task.utils.DateUtils;
import com.camelot.pmt.task.utils.RRException;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author muyuanpei
 * @date 2018/4/10
 */

@Service
public class TaskRunningServiceImpl implements TaskRunningService{

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;


    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRunningServiceImpl.class);

    /**
     * @Title: updateRunningToClose
     * @Description: TODO(根据用户id获取正在进行的任务列表)
     * @param int page , int rows, String id
     * @return JSONObject    返回类型
     * @auth myp
     */
    @Override
    public ExecuteResult<PageInfo<Task>> queryTaskRunning(int page , int rows, String id) {
        ExecuteResult<PageInfo<Task>> result = new ExecuteResult<PageInfo<Task>>();
        //利用PageHelper进行分页
        PageHelper.startPage(page, rows);
        //根据用户id查询全部的正在进行的任务
        List<Task> list = taskMapper.queryTaskRunning(id);
        //分页之后的结果集
        PageInfo<Task> clist = new PageInfo<Task>(list);
        //返回结果集
        result.setResult(clist);
        return result;
    }

    /**
     * @Title: updateRunningToClose
     * @Description: TODO(我的任务转为关闭)
     * @param taskId
     * @return JSONObject    返回类型
     * @auth myp
     */
    @Override
    public ExecuteResult<String> updateRunningToClose(Long id) {
        ExecuteResult<String> result=new ExecuteResult<>();
        try{
            //遍历此任务下是否有引用--->查询所有任务父id为id的记录
            List<Task> taskList = taskMapper.queryByPId(id);
            List<Long> list = new ArrayList<Long>();
            if(taskList.size()>0){
                for (Task task : taskList) {
                    List<Task> tempList = taskMapper.queryByPId(task.getId());
                    if(tempList.size()>0){
                        System.out.println(tempList.size());
                        for (Task task2 : tempList) {
                            List<Task> tempList2 = taskMapper.queryByPId(task2.getId());
                            if(tempList2.size()>0){
                                for (Task task3 : tempList2) {
                                    list.add(task3.getId());
                                }
                                list.add(task2.getId());
                            }else{
                                //说明没有子任务
                                list.add(task2.getId());
                            }
                        }
                        list.add(task.getId());
                    }else{
                        //说明没有子任务
                        list.add(task.getId());
                    }
                }
                list.add(id);
            }else{
                //说明没有子任务
                list.add(id);
            }
            taskMapper.updateRunningToClose(list);
            result.setResult("任务关闭成功");
            for (Long taskid : list) {
                addTaskLog(taskid,"关闭");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @Title: runningtoclose
     * @Description: TODO(我的正在进行的任务转为已完成)
     * @param taskId
     * @return JSONObject    返回类型
     * @auth myp
     */
    @Override
    public ExecuteResult<String> updateRunningToAlready(Task ptask, TaskFile taskFile) {
        ExecuteResult<String> result=new ExecuteResult<>();
        try{
            taskFile.setSourceId(ptask.getId());
            taskFile.setAttachmentSource("任务");
            //遍历此任务下是否有引用--->查询所有任务父id为id的记录
            List<Task> taskList = taskMapper.queryByPId(ptask.getId());
            List<Long> list = new ArrayList<Long>();
            if(taskList.size()>0){
                for (Task task : taskList) {
                    List<Task> tempList = taskMapper.queryByPId(task.getId());
                    if(tempList.size()>0){
                        System.out.println(tempList.size());
                        for (Task task2 : tempList) {
                            List<Task> tempList2 = taskMapper.queryByPId(task2.getId());
                            if(tempList2.size()>0){
                                for (Task task3 : tempList2) {
                                    list.add(task3.getId());
                                }
                                list.add(task2.getId());
                            }else{
                                //说明没有子任务
                                list.add(task2.getId());
                            }
                        }
                        list.add(task.getId());
                    }else{
                        //说明没有子任务,直接修改此任务的状态即可
                        list.add(task.getId());
                    }
                }
                //查询出该节点下的所有任务
                List<Task> list1 = taskMapper.queryRunningToAlready(list);
                List<String> booleans = new ArrayList<String>();
                for (Task taskalready : list1) {
                    //判断该节点下的所有子任务的状态是否都已完成或者关闭
                    if(taskalready.getStatus().equals("2") || taskalready.getStatus().equals("3")){
                        booleans.add("1");
                    }
                }
                if(list1.size() == booleans.size()){
                    taskMapper.updateRunningToAlready(ptask.getId());
                    result.setResult("任务完成");
                    taskMapper.updateInfact_hourAndActual_end_time(ptask);
                    taskMapper.addAttachment(taskFile);
                    addTaskLog(ptask.getId(), "完成");
                }else{
                    result.setResult("还有子任务未完成！");
                }
            }else{
                //说明没有子任务,直接修改此任务的状态即可
                taskMapper.updateRunningToAlready(ptask.getId());
                result.setResult("任务完成");
                taskMapper.updateInfact_hourAndActual_end_time(ptask);
                taskMapper.addAttachment(taskFile);
                addTaskLog(ptask.getId(),"完成");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    //操作成功后添加操作记录
    private void addTaskLog(Long id, String peration){
        Task taskAll = taskMapper.queryTaskAllById(id);
        TaskLog taskLog = new TaskLog();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        taskLog.setTaskId(taskAll.getId());
        taskLog.setUserId(taskAll.getBeassignUser().getUserId());
        taskLog.setOperationButton(peration);
        taskLog.setOperationTime(date);
        taskLog.setOperationDescribe(dateFormat.format(date)+"\t"+taskAll.getBeassignUser().getUsername()+"\t"+peration);
        taskLogMapper.insertTaskLog(taskLog);
    }


    /**
     * @Title: runningtoclose
     * @Description: TODO(根据id获取单个任务明细)
     * @param id
     * @return JSONObject    返回类型
     * @auth myp
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

}
