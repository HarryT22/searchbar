package com.searchbar.sweng.searchbar.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    NORMAL,PREMIUM,ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
