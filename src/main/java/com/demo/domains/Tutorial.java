package com.demo.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vokidah on 12.11.16.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tutorial")
public class Tutorial implements Serializable{
    @Id
    @GeneratedValue
    Long id;

    String name;

    Integer level;

    @OneToMany(mappedBy = "tutorial", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "tutorial-task")
    Set<Task> tasks;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tutorials")
    @JsonBackReference(value = "tutorial-user")
    Set<User> users;


}
