package com.camelot.pmt.task;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zlh
 * @date 2018/4/17 18:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerTest {
    @Autowired
    private TaskManagerService taskManagerService;

    @Test
    public void testQueryAllTasks() throws Exception {
        ExecuteResult<List<Task>> listExecuteResult = taskManagerService.queryAllTask();
        List<Task> result = listExecuteResult.getResult();

        for (Task task : result) {
            System.out.println(task);
        }
    }
    @Test
    public void testInsertTask() throws Exception {
        Task task = new Task();
        User user = new User();
        user.setUserId("2");
        task.setBeassignUser(user);
        task.setTaskType("任务");
        task.setTaskName("乱七八糟任务");
        task.setEstimateStartTime(new Date());
        task.setEstimateEndTime(new Date());
        task.setTaskDescribe("这是任务描述");
        ExecuteResult<String> stringExecuteResult = taskManagerService.insertTask(task, null);
    }
    @Test
    public void testQueryTaskById() throws Exception {
        ExecuteResult<Map<String, Object>> mapExecuteResult = taskManagerService.queryTaskById((long) 2);
        Map<String, Object> result = mapExecuteResult.getResult();
        Task task = (Task) result.get("Task");
        System.out.println(task);
    }
}
