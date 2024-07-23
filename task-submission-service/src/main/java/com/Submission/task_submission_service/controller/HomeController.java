package com.Submission.task_submission_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

    @GetMapping("submission")
    public ResponseEntity<String>homeController(){
        return new ResponseEntity<>("Welcome to submission service", HttpStatus.OK);
    }

}
