package com.demo.BaseDomain;

import com.demo.domains.Submission;
import com.demo.domains.Task;
import com.demo.domains.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.demo.BaseDomain.TaskBase.buildTaskForDBFromTaskBase;
import static com.demo.BaseDomain.UserBase.getUserforDBFromUserBase;

/**
 * Created by Vitya on 19.11.2016.
 */
@AllArgsConstructor
@Data
@Builder
public class SubmissionBase {
    Long id;

    Integer score;

    Long userId;

    Long taskId;

    public static SubmissionBase buildSubmissionBaseFromSubmission(Submission submission){
        return SubmissionBase.builder()
                .id(submission.getId())
                .score(submission.getScore())
                .userId(submission.getUser().getId())
                .taskId(submission.getTask().getId())
                .build();
    }

    public static Submission getSubmissionFromSubmissionBase(SubmissionBase submissionBase, UserBase userBase, TaskBase taskBase){
        Submission submission = new Submission();
        submission.setScore(submissionBase.getScore());
        submission.setUser(getUserforDBFromUserBase(userBase));
        submission.setTask(buildTaskForDBFromTaskBase(taskBase));
        return submission;
    }
}
