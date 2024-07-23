package com.task.task.user.service.repository;

import com.task.task.user.service.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);
}
