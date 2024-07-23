package com.Task.Service.Development.controller;

import com.Task.Service.Development.model.Task;
import com.Task.Service.Development.model.TaskStatus;
import com.Task.Service.Development.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class HomeController {

    @GetMapping("/tasks")
    public ResponseEntity<String> getAssignedUsersTask()
             {



        return new ResponseEntity<>("welcome to task service", HttpStatus.OK);

    }
}
