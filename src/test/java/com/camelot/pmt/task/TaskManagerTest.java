package com.camelot.pmt.task;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.UserService;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.service.TaskFileService;
import com.camelot.pmt.task.service.TaskManagerService;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    private TaskFileService taskFileService;

    @Autowired
    private UserService userService;

    @Test
    public void testQueryAllTasks() throws Exception {
        /*
         * ExecuteResult<PageInfo<Task>> pageInfoExecuteResult =
         * taskManagerService.queryAllTask(1, 10); PageInfo<Task> result =
         * pageInfoExecuteResult.getResult(); List<Task> list = result.getList(); for
         * (Task task : list) { System.out.println(task); }
         */
    }

    @Test
    public void testInsertTask() throws Exception {
        /*
         * Task task = new Task(); User user = new User(); user.setUserId("2");
         * task.setBeassignUser(user); task.setTaskType("需求"); task.setTaskName("哼哼任务");
         * task.setEstimateStartTime(new Date()); task.setEstimateEndTime(new Date());
         * task.setTaskDescribe("这是任务描述2"); ExecuteResult<String> stringExecuteResult =
         * taskManagerService.insertTask(task, null);
         */
    }

    @Test
    public void testQueryTaskById() throws Exception {
        /*
         * ExecuteResult<Map<String, Object>> mapExecuteResult =
         * taskManagerService.queryTaskById((long) 24); // ExecuteResult<Map<String,
         * Object>> mapExecuteResult = taskManagerService.queryTaskById((long) 2);
         * Map<String, Object> result = mapExecuteResult.getResult(); Task task = (Task)
         * result.get("Task");
         * 
         * for (Map.Entry<String, Object> entry : result.entrySet()) {
         * System.out.println(entry.getKey() + ":" + entry.getValue()); }
         */
    }

    @Test
    public void testInsertTaskFile() throws Exception {
        /*
         * TaskFile taskFile = new TaskFile(); taskFile.setSourceId((long) 20);
         * taskFile.setAttachmentSource("任务"); taskFile.setAttachmentUrl("d:/abc.txt");
         * taskFile.setAttachmentTile("abc.txt"); taskFileService.insert(taskFile);
         */
    }

    @Test
    public void testQueryTaskByTask() throws Exception {
        /*
         * Task task = new Task(); // User beAssginUser = new User(); //
         * beAssginUser.setUsername("lyh"); // task.setBeassignUser(beAssginUser); //
         * task.setTaskName("第一个"); ExecuteResult<PageInfo<Task>> pageInfoExecuteResult
         * = taskManagerService.queryTaskByTask(task, 1, 10); PageInfo<Task> result1 =
         * pageInfoExecuteResult.getResult(); List<Task> list = result1.getList(); for
         * (Task task1 : list) { System.out.println(task1); }
         */
    }

    @Test
    public void testQueryUserByUsername() {
        /*
         * ExecuteResult<List<User>> lv = (ExecuteResult<List<User>>)
         * userService.queryUsersByUserName("风"); List<User> result = lv.getResult();
         * for (User user : result) { System.out.println(user.getUserId()); }
         */
    }
}
