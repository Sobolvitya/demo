package com.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.domains.Task;

/**
 * Created by Vitya on 12.11.2016.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByName (String name);
}
