package com.camelot.pmt.task.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
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
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    /**
     * <p>
     * Description:[根据用户id获取正在进行的任务列表]
     * <p>
     *
     * @return ExecuteResult<PageInfo<Map<String, Object>>>
     */
    @Override
    public ExecuteResult<PageInfo<Map<String, Object>>> queryoverdueTaskRunning(int page , int rows, String id) {
        ExecuteResult<PageInfo<Map<String, Object>>> result = new ExecuteResult<PageInfo<Map<String, Object>>>();
        //利用PageHelper进行分页
        PageHelper.startPage(page, rows);
        //根据用户id查询全部的正在进行的任务
        List<Map<String, Object>> list = taskMapper.listTaskRunning(id);
        System.out.println(list.size());
        //分页之后的结果集
        PageInfo<Map<String, Object>> clist = new PageInfo<Map<String, Object>>(list);
        //返回结果集
        result.setResult(clist);
        return result;
    }

    /**
     *
     * @Title: runningtoclose
     * @Description: TODO(我的任务转为关闭)
     * @param @param taskId
     * @param @return    设定文件
     * @return JSONObject    返回类型
     * @throws
     */
    @Override
    public ExecuteResult<String> runningtoclose(Long id) {
        ExecuteResult<String> result=new ExecuteResult<>();
        try{
            //遍历此任务下是否有引用--->查询所有任务父id为id的记录
            List<Task> taskList = taskMapper.selectByPId(id);
            List<Long> list = new ArrayList<Long>();
            if(taskList.size()>0){
                for (Task task : taskList) {
                    List<Task> tempList = taskMapper.selectByPId(task.getId());
                    if(tempList.size()>0){
                        System.out.println(tempList.size());
                        for (Task task2 : tempList) {
                            List<Task> tempList2 = taskMapper.selectByPId(task2.getId());
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
            taskMapper.runningtoclose(list);
            result.setResult("任务关闭成功");
        }catch (Exception e){
            throw new RuntimeException(e);

        }
        return result;
    }

    /**
     *
     * @Title: runningtoclose
     * @Description: TODO(我的正在进行的任务转为已完成)
     * @param @param taskId
     * @param @return    设定文件
     * @return JSONObject    返回类型
     * @throws
     */
    @Override
    public ExecuteResult<String> runningtoalready(Long id) {
        ExecuteResult<String> result=new ExecuteResult<>();
        try{
            //遍历此任务下是否有引用--->查询所有任务父id为id的记录
            List<Task> taskList = taskMapper.selectByPId(id);
            List<Long> list = new ArrayList<Long>();
            if(taskList.size()>0){
                for (Task task : taskList) {
                    List<Task> tempList = taskMapper.selectByPId(task.getId());
                    if(tempList.size()>0){
                        System.out.println(tempList.size());
                        for (Task task2 : tempList) {
                            List<Task> tempList2 = taskMapper.selectByPId(task2.getId());
                            if(tempList2.size()>0){
                                for (Task task3 : tempList2) {
                                    list.add(task3.getId());
                                }
                            }else{
                                //说明没有子任务
                                list.add(task2.getId());
                            }
                        }
                    }else{
                        //说明没有子任务
                        list.add(task.getId());
                    }
                }
            }else{
                //说明没有子任务
                list.add(id);
            }
            //查询出该节点下的所有任务
            List<Task> list1 = taskMapper.runningtoalready(list);
            List<Boolean> booleans = new ArrayList<>();
            for (Task taskalready : list1) {
                //判断该节点下的所有任务的状态是否都已完成或者关闭
                if(taskalready.getStatus().equals("3") || taskalready.getStatus().equals("4")){
                    booleans.add(true);
                }
            }
            if(list1.size() == booleans.size()){
                result.setResult("任务已完成");
            }else{
                result.setResult("还有子任务未完成！");
            }
        }catch (Exception e){
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
}
