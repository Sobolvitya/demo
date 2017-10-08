package com.demo.domains;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class User implements Serializable {
    @Id
    @GeneratedValue
    Long id;

    String fullName;

    String email;

    String passHash;

    int level;

    java.sql.Date registrationDate;

    String type;

    //TODO need to be done in another way

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference(value = "user-submission")
    Set<Submission> submissions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_tutorial",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "tutorial_id", referencedColumnName = "id", nullable = true))
    @JsonManagedReference(value = "user-tutorial")
    Set<Tutorial> tutorials;




}

