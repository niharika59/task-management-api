package com.example.TaskManagement;

import com.example.TaskManagement.DTO.TaskRequest;
import com.example.TaskManagement.Enums.TaskStatus;
import com.example.TaskManagement.Exception.TaskNotFoundException;
import com.example.TaskManagement.Service.TaskManagementService;
import com.example.TaskManagement.model.Task;
import com.example.TaskManagement.model.TaskManagementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    TaskManagementRepository taskManagementRepository;

    @InjectMocks
    TaskManagementService taskManagementService;

    @Test
    void createTask_shouldSaveAndReturnTask() {
        TaskRequest taskRequest = new TaskRequest();

        taskRequest.setTaskName("taskName");
        taskRequest.setDescription("description");

        Task savedTask = new Task();
        savedTask.setTaskId(1L);
        savedTask.setTaskName("taskName");
        savedTask.setDescription("description");
        savedTask.setStatus(TaskStatus.STARTED);
        savedTask.setCreatedOn(LocalDateTime.now());

        when(taskManagementRepository.save(any(Task.class))).thenReturn(savedTask);

        Task createdTask = taskManagementService.createTask(taskRequest);

        assertNotNull(createdTask);
        assertEquals("taskName", createdTask.getTaskName());
        assertEquals("description", createdTask.getDescription());
        verify(taskManagementRepository, times(1)).save(any(Task.class));

    }

    @Test
    void getTask_shouldReturnTask() {

        Task savedTask = new Task();
        savedTask.setTaskId(1L);
        savedTask.setTaskName("taskName");
        savedTask.setDescription("description");
        savedTask.setStatus(TaskStatus.STARTED);
        savedTask.setCreatedOn(LocalDateTime.now());

        when(taskManagementRepository.findById(anyLong())).thenReturn(Optional.of(savedTask));

        Task responseTask= taskManagementService.getTask(1L);

        assertNotNull(responseTask);
        assertEquals("taskName", responseTask.getTaskName());
        assertEquals("description", responseTask.getDescription());

    }

    @Test
    void getTasks_shouldReturnTasks() {


        Task savedTask1 = new Task();
        savedTask1.setTaskId(1L);
        savedTask1.setTaskName("taskName");
        savedTask1.setDescription("description");
        savedTask1.setStatus(TaskStatus.STARTED);
        savedTask1.setCreatedOn(LocalDateTime.now());

        Task savedTask2 = new Task();
        savedTask2.setTaskId(2L);
        savedTask2.setTaskName("taskName1");
        savedTask2.setDescription("description2");
        savedTask2.setStatus(TaskStatus.COMPLETED);
        savedTask2.setCreatedOn(LocalDateTime.now());

        List<Task> savedTasks = List.of(savedTask1, savedTask2);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Task> page = new PageImpl<>(savedTasks, pageable, savedTasks.size());

        when(taskManagementRepository.findAll(pageable)).thenReturn(page);

        Page<Task> responseTasks= taskManagementService.getTasks(null,pageable);

        assertNotNull(responseTasks);
        assertEquals(2, responseTasks.getTotalElements());
        assertEquals("taskName", responseTasks.getContent().get(0).getTaskName());
        assertEquals("taskName1", responseTasks.getContent().get(1).getTaskName());
    }

    @Test
    void getTasks_byStatus(){
        Task savedTask1 = new Task();
        savedTask1.setTaskId(1L);
        savedTask1.setTaskName("taskName");
        savedTask1.setDescription("description");
        savedTask1.setStatus(TaskStatus.STARTED);
        savedTask1.setCreatedOn(LocalDateTime.now());

        Task savedTask2 = new Task();
        savedTask2.setTaskId(2L);
        savedTask2.setTaskName("taskName1");
        savedTask2.setDescription("description2");
        savedTask2.setStatus(TaskStatus.COMPLETED);
        savedTask2.setCreatedOn(LocalDateTime.now());

        List<Task> savedTasks = List.of(savedTask2);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Task> page = new PageImpl<>(savedTasks, pageable, savedTasks.size());
        when(taskManagementRepository.findByStatus(TaskStatus.COMPLETED,pageable)).thenReturn(page);

        Page<Task> responseTasks= taskManagementService.getTasks(TaskStatus.COMPLETED,pageable);
        assertNotNull(responseTasks);
        assertEquals(1, responseTasks.getTotalElements());
        assertEquals("taskName1", responseTasks.getContent().get(0).getTaskName());
    }

    @Test
    void updateTask_shouldUpdateTask() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTaskName("UpdatedTaskName");
        taskRequest.setDescription("UpdatedDescription");

        Task savedTask = new Task();
        savedTask.setTaskId(1L);
        savedTask.setTaskName("OldTaskName");
        savedTask.setDescription("OldDescription");
        savedTask.setStatus(TaskStatus.STARTED);
        savedTask.setCreatedOn(LocalDateTime.now());

        when(taskManagementRepository.findById(anyLong())).thenReturn(Optional.of(savedTask));

        Task updatedTask = taskManagementService.updateTask(1L, taskRequest);

        assertNotNull(updatedTask);
        assertEquals("UpdatedTaskName", updatedTask.getTaskName());
        assertEquals("UpdatedDescription", updatedTask.getDescription());
        assertEquals(savedTask.getTaskId(), updatedTask.getTaskId());
        assertNotNull(updatedTask.getUpdatedOn());
        verify(taskManagementRepository, times(1)).save(any(Task.class));
    }

    @Test
    void deleteTask_shouldDeleteTask() {

        when(taskManagementRepository.existsById(anyLong())).thenReturn(true);

        taskManagementService.deleteTask(1L);

        verify(taskManagementRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteTask_taskNotFound_shouldThrowException() {

        assertThrows(TaskNotFoundException.class, () -> taskManagementService.deleteTask(1L));

        verify(taskManagementRepository, times(0)).deleteById(anyLong());
    }
}
