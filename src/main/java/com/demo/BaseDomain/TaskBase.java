package com.demo.BaseDomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.demo.domains.Task;

/**
 * Created by Vitya on 19.11.2016.
 */
@Data
@AllArgsConstructor
@Builder
public class TaskBase {
    Long id;

    String name;

    Integer level;

    Integer maxScore;

    String description; // get from Mongo

    public static Task buildTaskForDBFromTaskBase(TaskBase taskBase){
        Task task = new Task();
        task.setName(taskBase.getName());
        task.setLevel(taskBase.getLevel());
        task.setMaxScore(taskBase.maxScore);
        return task;
    }

    public static TaskBase buildTaskBaseFromTask(Task task, String description){
        return TaskBase.builder()
                .id(task.getId())
                .level(task.getLevel())
                .name(task.getName())
                .maxScore(task.getMaxScore())
                .description(description)
                .build();
    }


}
