package com.demo.service;

import com.demo.BaseDomain.UserBase;
import com.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vitya on 20.11.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserBase user = userService.findUserByEmail(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user == null) throw new UsernameNotFoundException(email);
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getType()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassHash(), grantedAuthorities);
    }
}
