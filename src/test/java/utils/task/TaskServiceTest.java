package utils.task;

import org.junit.Before;
import org.junit.Test;
import simplebot.utils.task.TaskService;

import static org.junit.Assert.*;

public class TaskServiceTest {
    TaskService taskService;
    @Before
    public void setUp() throws Exception {
        taskService = new TaskService();
        taskService.read();
    }

    @Test
    public void addTask() {
        taskService.addTask("");
        taskService.addTask("123");

        assertNotNull(taskService.toString());
        assertNotEquals("", taskService.toString());
    }

    @Test
    public void deleteTask() {
        taskService.deleteTask("1");
    }

}