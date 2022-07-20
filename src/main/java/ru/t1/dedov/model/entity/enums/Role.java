package ru.t1.dedov.model.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, TRAINER, USER, GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
