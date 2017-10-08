package com.demo.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.demo.domains.Tutorial;
import com.demo.domains.User;

/**
 * Created by vokidah on 12.11.16.
 */
public interface TutorialRepository extends CrudRepository<Tutorial, Long> {

    Set<Tutorial> findByUsers(List<User> users);

}
