package com.demo.controllers.tutorial;

import com.demo.BaseDomain.TaskBase;
import com.demo.BaseDomain.TutorialBase;
import com.demo.domains.Tutorial;
import com.demo.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Vitya on 20.11.2016.
 */
@RestController
@RequestMapping(value = "/controller/tutorial")
public class TutorialController {
    @Autowired
    TutorialService tutorialService;

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public ResponseEntity<List<TutorialBase>> getTasks() {
        return new ResponseEntity<>(tutorialService.findAllTutorials(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTutorial(@PathVariable Long id) {
        TutorialBase tutorialBase = tutorialService.findTutorialById(id);
        if (tutorialBase != null){
            return new ResponseEntity<Object>(tutorialBase, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/addTutorial", method = RequestMethod.POST)
    public ResponseEntity<?> addTutorial(@RequestBody TutorialBase tutorialBase) {
        boolean isSuccess = tutorialService.addNewTutorial(tutorialBase);
        if (isSuccess) {
            return new ResponseEntity<Object>("Tutorial add successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }
}
