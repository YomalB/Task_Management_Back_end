package com.task.task.user.service.service;

import com.task.task.user.service.modal.User;

import java.util.List;

public interface UserService {
    public User getUserProfile(String jwt);

    public List<User>getAllUsers();

}
