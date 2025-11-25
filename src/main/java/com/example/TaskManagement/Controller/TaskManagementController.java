package com.example.TaskManagement.Controller;

import com.example.TaskManagement.DTO.TaskRequest;
import com.example.TaskManagement.Enums.TaskStatus;
import com.example.TaskManagement.Service.TaskManagementService;
import com.example.TaskManagement.model.Task;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
//@AllArgsConstructor
public class TaskManagementController {
    private static final Logger logger = LoggerFactory.getLogger(TaskManagementController.class);
    public final TaskManagementService taskManagementService;

    public TaskManagementController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest newTask) {
        logger.info("Creating task with name {}", newTask.getTaskName());
        Task createdTask = taskManagementService.createTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskManagementService.getTask(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,  @Valid @RequestBody TaskRequest newTask) {
        return taskManagementService.updateTask(id, newTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        taskManagementService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping()
    public Page<Task> getTasks(@RequestParam(required = false) TaskStatus taskStatus, Pageable pageable) {
        return  taskManagementService.getTasks(taskStatus, pageable);
    }

}
