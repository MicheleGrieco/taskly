package com.michelegrieco.taskly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelegrieco.taskly.model.Task;
import com.michelegrieco.taskly.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    /** Service layer for task operations. */
    private TaskService taskService;

    @GetMapping
    /**
     * Retrieve all tasks.
     * @return List of all tasks.
     */
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    /**
     * Retrieve a task by its ID.
     * @param id Task ID.
     * @return Task with the specified ID or 404 if not found.
     */
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    /**
     * Create a new task.
     * @param task Task to be created.
     * @return Created task.
     */
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    /**
     * Update an existing task.
     * @param id Task ID.
     * @param updatedTask Updated task data.
     * @return Updated task or 404 if not found.
     */
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    /**
     * Delete a task by its ID.
     * @param id Task ID.
     * @return 204 No Content if deleted, 404 if not found.
     */
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
