package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuit {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"title","content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assertions.assertThat(task).isEqualToComparingFieldByField(taskDto);
    }

    @Test
    public void testMapToTaskDto(){
        //Given
        Task task = new Task(1L,"title","content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assertions.assertThat(taskDto).isEqualToComparingFieldByField(task);
    }

    @Test
    public void testMapToTaskDtoList(){
        //Given
        Task task1 = new Task(1L,"title","content");
        Task task2 = new Task(2L,"title2","content2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(2, taskDtos.size());
        assertEquals("title", taskDtos.get(0).getTitle());
        assertEquals("content", taskDtos.get(0).getContent());
        assertEquals("title2", taskDtos.get(1).getTitle());
        assertEquals("content2", taskDtos.get(1).getContent());
    }

}
