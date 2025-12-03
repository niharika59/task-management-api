package com.example.TaskManagement.model;

import com.example.TaskManagement.Enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity // Tells Spring Boot to create a table for this class in db
@Table(name = "task") // Optional -sets the table name manually
// if not provided table name will be task
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id // designates id as the PK
    // indicates that the db will auto-generate  the PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @NotBlank(message = "Name is mandatory")
    private String taskName;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @Enumerated(EnumType.STRING) // Status should be persisted as String
    private TaskStatus status;

    private String createdBy;
    private LocalDateTime createdOn;
    private String updatedBy;
    private LocalDateTime updatedOn;
}
