package com.camelot.pmt.task;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
}
