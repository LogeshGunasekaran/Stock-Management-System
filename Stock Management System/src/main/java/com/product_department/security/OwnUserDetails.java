package com.product_department.security;

import com.product_department.entity.UserCredential;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class OwnUserDetails implements UserDetails {

    private UserCredential userCredential;

    public OwnUserDetails(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    @Override
    public String getUsername() {
        return userCredential.getName();
    }


    @Override
    public String getPassword() {
        return userCredential.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>(Arrays
                                                                .asList(new SimpleGrantedAuthority(userCredential.getAuthority())));
        return authorityList;
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
