package com.crud.tasks.service;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestDbService {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskController taskController;

    @Test
    public void shouldGetAllTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "name", "description"));

        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> theList = dbService.getAllTasks();

        //Then
        assertEquals(1, theList.size());
    }

    @Test
    public void shouldGetTask() {
        //Given
        Task task = new Task(1L, "name", "description");
        long t = 1L;
        when(taskRepository.findById(t)).thenReturn(Optional.ofNullable(task));

        //When
        Optional<Task> theList = dbService.getTask(t);
        long o = theList.get().getId();

        //Then
        assertEquals(1L, o);
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "name", "description");

        when(taskRepository.save(any())).thenReturn(task);

        //When
        Task theTask = dbService.saveTask(task);
        long id = theTask.getId();

        //Then
        assertEquals(1L, id);
        assertEquals("name", theTask.getTitle());
    }

    @Test
    public void shouldDeleteTask() {
        //Given
        Task task = new Task(1L, "name", "description");
        long taskId = task.getId();

        //When
        taskController.deleteTask(taskId);

        //Then
        verify(taskController, times(1)).deleteTask(taskId);
    }
}
