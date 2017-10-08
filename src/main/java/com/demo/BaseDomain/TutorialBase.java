package com.demo.BaseDomain;

import com.demo.domains.Tutorial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Vitya on 19.11.2016.
 */
@AllArgsConstructor
@Data
@Builder
public class TutorialBase {
    Long id;

    String name;

    Integer level;

    public static TutorialBase buildTutorialBaseFromTutorial(Tutorial tutorial){
        return TutorialBase.builder()
                .id(tutorial.getId())
                .name(tutorial.getName())
                .level(tutorial.getLevel())
                .build();
    }

    public static Tutorial getTutorialForDBFromTutorialBase(TutorialBase tutorialBase){
        Tutorial tutorial = new Tutorial();
        tutorial.setLevel(tutorialBase.getLevel());
        tutorial.setName(tutorialBase.getName());
        return tutorial;
    }
}
