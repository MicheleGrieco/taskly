package com.michelegrieco.taskly.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelegrieco.taskly.exception.TaskNotFoundException;
import com.michelegrieco.taskly.model.Task;
import com.michelegrieco.taskly.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    /** Task repository for database operations. */
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Get all tasks.
     * @return List of tasks.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Get a task by its ID.
     * @param id Task ID.
     * @return Optional containing the task if found, or empty if not found.
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Create a new task.
     * @param task Task to be created.
     * @return Created task.
     */
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    /**
     * Update an existing task.
     * @param id Task ID.
     * @param updatedTask Task data to update.
     * @return Optional containing the updated task if found, or empty if not found.
     */
    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());
        return taskRepository.save(task);
    }

    /**
     * Delete a task by its ID.
     * @param id Task ID.
     * @return true if the task was deleted, false if not found.
     */
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
