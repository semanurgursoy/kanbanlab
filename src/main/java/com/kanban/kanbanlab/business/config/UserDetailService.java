package com.kanban.kanbanlab.business.config;

import com.kanban.kanbanlab.business.abstracts.UserService;
import com.kanban.kanbanlab.entities.concretes.Role;
import com.kanban.kanbanlab.entities.concretes.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailService {
    private final UserService userService;

    public UserDetails findUserByEmail(String email){
        User user = userService.findUserByEmail(email);
        GrantedAuthority authority = getUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authority);
    }

    private GrantedAuthority getUserAuthority(Role userRole) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRole());
        return grantedAuthority;
    }

    private UserDetails buildUserForAuthentication(User user, GrantedAuthority authority) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(authority));
    }

}
