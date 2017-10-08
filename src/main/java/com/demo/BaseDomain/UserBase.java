package com.demo.BaseDomain;

import com.demo.domains.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Vitya on 19.11.2016.
 */
@AllArgsConstructor
@Data
@Builder
public class UserBase{
    Long id;

    String fullName;

    String email;

    String passHash;

    int level;

    Date registrationDate;

    String type;

    public static UserBase createUserBaseFromUser(User user){
        return UserBase.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .passHash(user.getPassHash())
                .level(user.getLevel())
                .registrationDate(user.getRegistrationDate())
                .type(user.getType())
                .build();
    }

    public static User getUserforDBFromUserBase(UserBase userBase){
        User user = new User();
        user.setEmail(userBase.getEmail());
        user.setLevel(userBase.getLevel());
        user.setPassHash(userBase.getPassHash());
        user.setRegistrationDate(userBase.getRegistrationDate());
        user.setType(userBase.getType());
        user.setFullName(userBase.getFullName());
        return user;
    }

}
