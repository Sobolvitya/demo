package com.demo.controllers.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.BaseDomain.TaskBase;
import com.demo.service.SecurityService;
import com.demo.services.HelpData.SubmissionData;
import com.demo.services.SubmissionProcessingService;
import com.demo.services.SubmissionService;
import com.demo.services.TaskService;


@RestController
@RequestMapping(value = "/controller/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    SubmissionProcessingService submissionProcessingService;

    @Autowired
    SubmissionService submissionService;

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<TaskBase>> getTasks() {
        return new ResponseEntity<>(taskService.findAllTasks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTask(@PathVariable Long id){
        TaskBase taskBase = taskService.findTaskBaseById(id);
        if (taskBase == null) {
            return new ResponseEntity<>("Task does not exist", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(taskBase, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/start/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> startTask(@PathVariable Long id, @RequestBody SubmissionData data){
        submissionProcessingService.compileProgramm(data, id);
        Integer res = submissionProcessingService.runProgramm();
        submissionService.addSubmission(securityService.getAuthenticatedUser(),taskService.findTaskBaseById(id),res);
        return new ResponseEntity<Object>(data, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskBase>> findTaskByName(@PathVariable String name) {
        return new ResponseEntity<>(taskService.findTasksByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public ResponseEntity<?> addTask(@RequestBody TaskBase taskBase){
        boolean isSuccess = taskService.addNewTask(taskBase);
        if (isSuccess){
            return new ResponseEntity<>(taskBase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/removeTask/{id}", method = RequestMethod.DELETE)
    public void removeTask(@PathVariable("id") Long id) {
        taskService.removeTask(id);
    }

    @RequestMapping(value = "/updateTask/{id}", method = RequestMethod.PUT)
    public void updateTask(@PathVariable("id") Long id, @RequestBody TaskBase taskBase) {
        taskService.updateTask(id, taskBase);
    }
}
