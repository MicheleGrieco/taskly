package com.michelegrieco.taskly.exception;

public class TaskNotFoundException extends RuntimeException {

    /**
     * Constructor for TaskNotFoundException.
     * @param id
     */
    public TaskNotFoundException(Long id) {
        super("Task with id " + id + "not found.");
    }
}
