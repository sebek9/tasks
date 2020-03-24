package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestTaskMapper {

    @InjectMocks
    private TaskMapper taskMapper;


    @Test
    public void testMapToTask(){
        //Given
        TaskDto taskDto1=new TaskDto(1L,"Test_task", "Test task");
        //When
        Task task=taskMapper.mapToTask(taskDto1);
        //Then
        //Then
        assertEquals("Test_task",task.getTitle());
        assertEquals("Test task",task.getContent());
    }

    @Test
    public void testMapToTaskDto(){
        //Given
        Task task1=new Task(1L,"Test_task", "Test task");
        //When
        TaskDto taskDto=taskMapper.mapToTaskDto(task1);
        //Then
        assertEquals("Test_task",taskDto.getTitle());
        assertEquals("Test task",taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList(){
        //Given
        List<TaskDto> dtoList = new ArrayList<>();
        dtoList.add(new TaskDto(1L, "test1", "test1"));
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test2", "test2"));

        //When
        List<TaskDto> theTaskDto = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, theTaskDto.size());
    }


}








