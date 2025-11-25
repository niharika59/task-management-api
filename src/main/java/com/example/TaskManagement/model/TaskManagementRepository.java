package com.example.TaskManagement.model;

import com.example.TaskManagement.Enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This is db helper
// Spring boot automatically generates save(), findById(), findAll(), deleteById()
// update()
@Repository
// - JpaRepository<T, ID> is a generic interface where:
//- T = the entity type (Task in your case).
//- ID = the type of the primary key field
// Since your Task entity has an id field of type Long, you specify Long here

public interface TaskManagementRepository extends JpaRepository<Task,Long> {

    // Add a method for filtering by status
    Page<Task> findByStatus(TaskStatus status, Pageable pageable);
}
