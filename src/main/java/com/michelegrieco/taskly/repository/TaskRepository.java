package com.michelegrieco.taskly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michelegrieco.taskly.model.Task;
import com.michelegrieco.taskly.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByUser(User user);
}
