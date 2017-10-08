package com.demo.services;

import static com.demo.BaseDomain.TaskBase.buildTaskBaseFromTask;
import static com.demo.BaseDomain.TaskBase.buildTaskForDBFromTaskBase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.BaseDomain.TaskBase;
import com.demo.Mongo.TaskDescription;
import com.demo.Mongo.TaskDescriptionRepository;
import com.demo.domains.Task;
import com.demo.repositories.TaskRepository;

public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskDescriptionRepository taskDescriptionRepository;

    public List<TaskBase> findAllTasks() {
        List<Task> tasks = (List)taskRepository.findAll();

        return tasks.stream()
                .map((
                        Task task) ->
                        buildTaskBaseFromTask(task, taskDescriptionRepository.findOne(
                                String.valueOf(task.getId())).getDescription())).collect(Collectors.toList());
    }

    public TaskBase findTaskBaseById(Long taskId){
        Task task = taskRepository.findOne(taskId);
        String description = taskDescriptionRepository.findOne(String.valueOf(taskId)).getDescription();
        return task != null
                ? buildTaskBaseFromTask(task, description)
                : null;
    }

    public List<TaskBase> findTasksByName(String name) {
        List<Task> tasks = (List)taskRepository.findByName(name);

        return tasks.stream()
                .map((
                        Task task) ->
                        buildTaskBaseFromTask(task, taskDescriptionRepository.findOne(
                                String.valueOf(task.getId())).getDescription())).collect(Collectors.toList());
    }

    public Task findTaskById(Long taskId){
        return taskRepository.findOne(taskId);
    }

    public boolean addNewTask(TaskBase taskBase){
        Task task = taskRepository.save(buildTaskForDBFromTaskBase(taskBase));
        taskDescriptionRepository.insert(new TaskDescription(String.valueOf(task.getId()), taskBase.getDescription()));
        return true;
    }

    public void removeTask(Long taskId) {
        Task task = taskRepository.findOne(taskId);
        taskRepository.delete(task);
        taskDescriptionRepository.delete(String.valueOf(taskId));
    }

    public void updateTask(Long taskId, TaskBase taskBase) {
        Task task = taskRepository.findOne(taskId);
        task.setName(taskBase.getName());
        task.setLevel(taskBase.getLevel());
        task.setMaxScore(taskBase.getMaxScore());
        taskRepository.save(task);
        taskDescriptionRepository.save(new TaskDescription(String.valueOf(taskId), taskBase.getDescription()));
    }
}
