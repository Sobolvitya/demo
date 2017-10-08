package com.demo.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import com.demo.domains.Task;
import com.demo.domains.User;

import java.io.Serializable;

/**
 * Created by vokidah on 12.11.16.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="submission")
public class Submission implements Serializable {
    @Id
    @GeneratedValue
    Long id;

    Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "submission-user")
    User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference(value = "submission-task")
    Task task;

    public Submission(Integer score, User user, Task task){
        this.score = score;
        this.user = user;
        this.task = task;
    }



}

