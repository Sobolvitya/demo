package com.demo.services;

import com.demo.BaseDomain.TutorialBase;
import com.demo.domains.Task;
import com.demo.domains.Tutorial;
import com.demo.repositories.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.demo.BaseDomain.TaskBase.buildTaskBaseFromTask;
import static com.demo.BaseDomain.TutorialBase.buildTutorialBaseFromTutorial;

/**
 * Created by Vitya on 13.11.2016.
 */
public class TutorialService {
    @Autowired
    TutorialRepository tutorialRepository;

    public List<TutorialBase> findAllTutorials() {
        List<Tutorial> tutorials = (List)tutorialRepository.findAll();
        return tutorials.stream().map((Tutorial tutorial) -> buildTutorialBaseFromTutorial(tutorial)).collect(Collectors.toList());
    }
    public TutorialBase findTutorialById(Long tutorialId){
        Tutorial tutorial = tutorialRepository.findOne(tutorialId);
        return (tutorial != null)
                ? buildTutorialBaseFromTutorial(tutorial)
                : null;
    }

    public boolean addNewTutorial(TutorialBase tutorialBase){
        tutorialRepository.save(TutorialBase.getTutorialForDBFromTutorialBase(tutorialBase));
        return true;

    }

    public Set<Task> getAllTaskInTutorial(Long tutorialId){
        return tutorialRepository.findOne(tutorialId).getTasks();
    }
}
