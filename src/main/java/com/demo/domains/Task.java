package com.demo.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vitya on 12.11.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="task")
public class Task implements Serializable{
    @Id
    @GeneratedValue
    Long id;

    String name;

    Integer level;

    //String type;

    Integer maxScore;

    @ManyToOne
    @JoinColumn(name = "tutorial_id")
    @JsonBackReference(value = "task-tutorial")
    private Tutorial tutorial;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "task-submission")
    Set<Submission> submissions;


    
}
