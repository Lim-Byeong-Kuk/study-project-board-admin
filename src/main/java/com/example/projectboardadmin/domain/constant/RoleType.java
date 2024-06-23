package com.example.projectboardadmin.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {
    USER("ROLE_USER", "사용자"), // ROLE_ 이렇게 시작하는것은 스프링시큐리티의 규칙이다.
    MANAGER("ROLE_MANAGER", "운영자"),
    DEVELOPER("ROLE_DEVELOPER", "개발자"),
    ADMIN("ROLE_ADMIN", "관리자")
    ;

    @Getter
    private final String roleName;
    @Getter
    private final String description;

}
