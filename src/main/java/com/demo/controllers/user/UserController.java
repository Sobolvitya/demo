package com.demo.controllers.user;

import com.demo.BaseDomain.UserBase;
import com.demo.service.SecurityService;
import com.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/controller/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?>  getUser(@PathVariable Long id) {
        UserBase userBase = userService.findUserBaseById(id);
        if (userBase == null){
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userBase, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentUser() {
        UserBase userBase = securityService.getAuthenticatedUser();
        return new ResponseEntity<Object>(userBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> addUserDB(@RequestBody UserBase user){
        boolean isSuccess = userService.addUser(user);
        if (isSuccess){
            securityService.autologin(user.getEmail(), user.getPassHash());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody UserBase user){
        boolean ans = securityService.loginUser(user.getEmail(), user.getPassHash());
        if (!ans)
            return new ResponseEntity<>("Password incorrect or user does not exist", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
