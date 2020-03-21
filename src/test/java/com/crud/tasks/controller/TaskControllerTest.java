package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;

import org.mockito.ArgumentMatchers;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Test_task", "Zadanie_1"));
        tasks.add(new Task(2L, "Test_task_2", "Zadanie_2"));

        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(new TaskDto(1L, "Test_task", "Zadanie_1"));
        tasksDto.add(new TaskDto(2L, "Test_task_2", "Zadanie_2"));

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        //when & then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test_task")))
                .andExpect(jsonPath("$[0].content", is("Zadanie_1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test_task_2")))
                .andExpect(jsonPath("$[1].content", is("Zadanie_2")));

        verify(service, times(1)).getAllTasks();
        verify(taskMapper, times(1)).mapToTaskDtoList(tasks);
    }
    @Test
    public void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "Test_task", "Zadanie_1");
        TaskDto taskDto2 = new TaskDto(2L, "Test_task_2", "Zadanie_2");
        List<TaskDto> taskList = new ArrayList<>();
        taskList.add(taskDto1);
        taskList.add(taskDto2);

        Task task = new Task();

        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto1);
        when(service.getTask(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.ofNullable(task));

        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test_task")))
                .andExpect(jsonPath("$.content", is("Zadanie_1")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        long id = 1L;

        service.deleteTask(id);

        verify(service, times(1)).deleteTask(id);
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(5L, "Test_task", "Zadanie_1");
        Task task = new Task();

        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(service.saveTask(task)).thenReturn(any());
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").content(jsonContent).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is("Test_task")))
                .andExpect(jsonPath("$.content", is("Zadanie_1")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "task200", "test200");
        TaskDto taskDto = new TaskDto(1L, "task200", "test200");

        taskMapper.mapToTask(taskDto);
        service.saveTask(task);

        verify(taskMapper, times(1)).mapToTask(taskDto);
        verify(service, times(1)).saveTask(task);
    }
    }

