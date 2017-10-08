package com.demo.services;

import com.demo.BaseDomain.TaskBase;
import com.demo.BaseDomain.UserBase;
import com.demo.domains.Task;
import com.demo.domains.User;
import com.demo.repositories.TaskRepository;
import com.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static com.demo.BaseDomain.UserBase.createUserBaseFromUser;
import static com.demo.BaseDomain.UserBase.getUserforDBFromUserBase;

/**
 * Created by Vitya on 13.11.2016.
 */
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    public UserBase findUserBaseById(Long userId){
        User user = userRepository.findOne(userId);
        return user != null
                ? createUserBaseFromUser(user)
                : null;
    }

    public User findUserById(Long userId){
        return userRepository.findOne(userId);
    }

    public UserBase findUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user != null
                ? createUserBaseFromUser(user)
                : null;
    }

    public boolean addUser(UserBase userBase){
        if (userRepository.findByEmail(userBase.getEmail())!=null)
            return false;
        userRepository.save(getUserforDBFromUserBase(userBase));
        return true;
    }

    public void addTaskToUser(TaskBase taskBase, UserBase userBase){
        User user = userRepository.findOne(userBase.getId());
        Task task = taskRepository.findOne(taskBase.getId());

    }


}
