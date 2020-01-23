package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {
    @Autowired
    private DbService dbService;

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task("title","test");

        //When
        dbService.saveTask(task);

        //Then
        long id = task.getId();
        Optional<Task> readTask = dbService.getTask(id);
        assertTrue(readTask.isPresent());

        //CleanUp
        dbService.deleteTask(id);
    }

    @Test
    public void testGetAllTasks() {
        //Given
        Task task = new Task("title","test");

        //When
        int tasksSize = dbService.getAllTasks().size();
        dbService.saveTask(task);
        List<Task> tasks = dbService.getAllTasks();

        //Then
        long id = task.getId();
        assertEquals(tasksSize + 1, dbService.getAllTasks().size());
        assertEquals("title", tasks.get(tasksSize).getTitle());
        assertEquals("test", tasks.get(tasksSize).getContent());

        //CleanUp
        dbService.deleteTask(id);
    }
}
