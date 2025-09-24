package com.michelegrieco.taskly.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michelegrieco.taskly.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
