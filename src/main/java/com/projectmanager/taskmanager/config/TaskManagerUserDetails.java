package com.projectmanager.taskmanager.config;

import com.projectmanager.taskmanager.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * In order to use the built-in functionality from Spring Security
 * we need to create a new class, that will make sure
 * that we are creating the users using the right way.
 */
public class TaskManagerUserDetails extends User implements UserDetails {
    private ArrayList<String> roles;
    private User user;

    public TaskManagerUserDetails(String email, String fullName, String password,
                                  ArrayList<String> roles, User user) {

        super(user.getEmail(), user.getFullName(), user.getPassword());
        this.roles = roles;
        this.user = user;
    }


    /*
        This will get our roles (that we currently keep as strings) and join them into one string
        (we use the StringUtils class from org.springframework.util).
        Then it will return collection of authorities.
        The authorities in Spring are the things we call "roles" or "permissions"
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRoles = StringUtils.collectionToCommaDelimitedString(this.roles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(userRoles);
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
