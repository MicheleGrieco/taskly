package com.michelegrieco.taskly.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskDTO {

    private Long id;

    @NotBlank(message="Title cannot be blank")
    @Size(max=100, message="Size must be 100 char max")
    private String title;

    @Size(max=500, message="Size must be 500 char max")
    private String description;

    private boolean completed;

    //Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
