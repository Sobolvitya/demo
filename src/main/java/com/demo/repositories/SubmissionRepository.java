package com.demo.repositories;

import com.demo.domains.Submission;
import com.demo.domains.Task;
import com.demo.domains.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by vokidah on 12.11.16.
 */
public interface SubmissionRepository extends CrudRepository<Submission, Long> {

    Set<Submission> findByUserAndTask(User user, Task task);
}
