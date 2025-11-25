package com.example.TaskManagement.DTO;

import com.example.TaskManagement.Enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *  This is DTO Data Transfer Object whose job is carry data between layer like controller -> service. This usually has
 *  only getters and setters. Think of it as parcel. It just holds the data. DTO defines what data is exposed to the client
 *  Best practices while designing DTOs -
 *  1.Only expose necessary fields
 *  2.Separate DTOs for Request(what client sends) and Response(what API returns)
 *  If you don;t use DTO you'd expose entity class directly in your API endpoint. Entity is directly mapped to database
 *  if you return it from controller , spring boot will serialize it to JSON and send it to client
 *  Why it not ideal to return entity
 *  Leaky Abstraction - any changes to db like column rename, add column leaks to your API
 *  Security risk - entities often contains sensitive fields like password
 *  Less Flexible - without DTO , you can't reshape data for client combine fields, computed values. For example - you may want to
 *  return full name which is created by joining firstName and LastName
 *  Validation Complexity - entities are not designed to do request validation like @NotNull, @NotBlank
 *  Entities represent your data model(map directly to database tables), but DTOs represent your API contract
 */
//@Getter
//@Setter
public class TaskRequest {
    @NotBlank(message = "Task name is required")
    @Size(max = 100, message= "Task name must be at most 100 characters")
    private String taskName;

    @NotBlank(message = "Task description is required")
    @Size(max=500, message = "Task description must be at most 500 characters ")
    private String description;

    private TaskStatus status; // Eg: "Created", "In-Progress", "Done"

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
