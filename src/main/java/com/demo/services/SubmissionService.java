package com.demo.services;

import com.demo.BaseDomain.TaskBase;
import com.demo.BaseDomain.UserBase;
import com.demo.domains.Submission;
import com.demo.domains.Task;
import com.demo.domains.Tutorial;
import com.demo.domains.User;
import com.demo.repositories.SubmissionRepository;
import com.demo.repositories.TaskRepository;
import com.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vitya on 13.11.2016.
 */
public class SubmissionService {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    SubmissionRepository submissionRepository;

    public Submission getSubmissionById(Long id){
        return submissionRepository.findOne(id);
    }

    public Set<Submission> getAllSubmissionForUserForTask(User user, Task task){
        return submissionRepository.findByUserAndTask(user, task);
    }

    public void addSubmission(Submission submission){
        submissionRepository.save(submission);
    }

    public void addSubmission(UserBase userBase, TaskBase taskBase, Integer result){
        User user = userService.findUserById(userBase.getId());
        Task task = taskService.findTaskById(taskBase.getId());
        submissionRepository.save(new Submission(result,user, task));
    }

    public Integer getPointsForUserForTask(User user, Task task){
        Set<Submission> submissionsSet = submissionRepository.findByUserAndTask(user, task);
        if (submissionsSet.isEmpty()) {
            return 0;
        }

        return Collections.max(submissionsSet, new Comparator<Submission>() {
            @Override
            public int compare(Submission o1, Submission o2) {
                return o1.getScore() > o2.getScore()? 1 : 0;
            }
        }).getScore();
    }

    public Integer getPointsForTutorialByUser(Tutorial tutorial, User user){
        int pointsForAllTasks = 0;
        for (Task task: tutorial.getTasks()){
            pointsForAllTasks += this.getPointsForUserForTask(user, task);
        }
        return pointsForAllTasks;
    }


    public Set<Task> getSolvingTaskInTutorialForUser(User user, Tutorial tutorial){
        HashSet<Task> taskHashSet = new HashSet<>();
        for (Task task: tutorial.getTasks()){
            if (task.getMaxScore() == this.getPointsForUserForTask(user, task)){
                taskHashSet.add(task);
            }
        }
        return taskHashSet;
    }

    public Set<Task> getSolvingTaskForUser(User user){
        HashSet<Task> taskHashSet = new HashSet<>();
//        for(Task task: user.getTasks()){
//            if(task.getMaxScore() == this.getPointsForUserForTask(user, task)){
//                taskHashSet.add(task);
//            }
//        }
        return taskHashSet;
    }
}
