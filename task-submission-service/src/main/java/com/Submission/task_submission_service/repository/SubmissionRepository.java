package com.Submission.task_submission_service.repository;

import com.Submission.task_submission_service.modal.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission,Long> {

    List<Submission>findByTaskId(Long taskId);

}
