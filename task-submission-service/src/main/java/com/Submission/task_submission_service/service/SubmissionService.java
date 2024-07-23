package com.Submission.task_submission_service.service;

import com.Submission.task_submission_service.modal.Submission;

import java.util.List;

public interface SubmissionService {

    Submission submitTask(Long taskId,String githubLink,Long userId,String jwt)throws Exception;

    Submission getTaskSubmissionById(Long submissionId)throws Exception;

    List<Submission>getAllTaskSubmission();

    List<Submission>getTaskSubmissionsByTaskId(Long taskId);

    Submission acceptDeclineSubmission(Long id,String status)throws Exception;
}

