package com.example.projectboardadmin.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {
    USER("ROLE_USER"), // ROLE_ 이렇게 시작하는것은 스프링시큐리티의 규칙이다.
    MANAGER("ROLE_MANAGER"),
    DEVELOPER("ROLE_DEVELOPER"),
    ADMIN("ROLE_ADMIN")
    ;

    @Getter
    private final String roleName;

}
