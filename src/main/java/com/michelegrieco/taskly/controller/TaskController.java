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

import com.michelegrieco.taskly.dto.TaskDTO;
import com.michelegrieco.taskly.dto.TaskMapper;
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
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks()
            .stream().map(TaskMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    /**
     * Retrieve a task by its ID.
     * @param id Task ID.
     * @return Task with the specified ID or 404 if not found.
     */
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id); // Throws TaskNotFoundException if not found
        return ResponseEntity.ok(TaskMapper.toDTO(task));
    }

    @PostMapping
    /**
     * Create a new task.
     * @param task Task to be created.
     * @return Created task.
     */
    public TaskDTO createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Task task = TaskMapper.toEntity(taskDTO);
        Task created = taskService.createTask(task);
        return TaskMapper.toDTO(created);
    }

    @PutMapping("/{id}")
    /**
     * Update an existing task.
     * @param id Task ID.
     * @param updatedTask Updated task data.
     * @return Updated task or 404 if not found.
     */
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO updatedTaskDTO) {
        Task updatedTask = TaskMapper.toEntity(updatedTaskDTO);
        Task result = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(TaskMapper.toDTO(result));
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
