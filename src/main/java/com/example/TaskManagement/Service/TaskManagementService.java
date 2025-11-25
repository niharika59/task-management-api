package com.example.TaskManagement.Service;

import com.example.TaskManagement.DTO.TaskRequest;
import com.example.TaskManagement.Enums.TaskStatus;
import com.example.TaskManagement.Exception.TaskNotFoundException;
import com.example.TaskManagement.model.TaskManagementRepository;
import com.example.TaskManagement.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class TaskManagementService {
    public final TaskManagementRepository taskManagementRepository;

    public TaskManagementService(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    public Task createTask(TaskRequest newTask) {
        Task task = new Task();
        task.setDescription(newTask.getDescription());
        task.setTaskName(newTask.getTaskName());
        task.setCreatedOn(LocalDateTime.now());
        task.setStatus(newTask.getStatus());
        return taskManagementRepository.save(task);
    }

    public Task getTask(Long taskId) {
        return taskManagementRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
    }

    public Page<Task> getTasks(TaskStatus taskStatus, Pageable pageable) {
        if(taskStatus != null){
            return taskManagementRepository.findByStatus(taskStatus, pageable);
        }
        return taskManagementRepository.findAll(pageable);
    }

    public void deleteTask(Long taskId) {
        if(!taskManagementRepository.existsById(taskId)){
            throw new TaskNotFoundException("Task not found with id: " + taskId);
        }
         taskManagementRepository.deleteById(taskId);
    }

    public Task updateTask(Long taskId, TaskRequest newTask) {
        Task existingTask =  taskManagementRepository.findById(taskId).orElseThrow(() ->
                new TaskNotFoundException("Task not found with id: " + taskId));
        System.out.println("existingTaskBeforeUpdate:"+existingTask.getTaskName());
        existingTask.setDescription(newTask.getDescription());
        existingTask.setTaskName(newTask.getTaskName());
        existingTask.setUpdatedOn(LocalDateTime.now());
        existingTask.setStatus(newTask.getStatus());

        taskManagementRepository.save(existingTask);
        return existingTask;
    }
}
