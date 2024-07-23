package com.Task.Service.Development.repository;

import com.Task.Service.Development.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    public List<Task>findByAssignedUserId(Long userId);

}
