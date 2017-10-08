package com.demo.repositories;

import com.demo.domains.Tutorial;
import com.demo.domains.User;
import org.springframework.data.repository.*;

import java.util.List;
import java.util.Set;


public interface UserRepository extends CrudRepository <User, Long> {

    User findByEmail(String email);

}

