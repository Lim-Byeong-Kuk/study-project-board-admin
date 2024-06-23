package com.example.projectboardadmin.config;

import com.example.projectboardadmin.domain.constant.RoleType;
import com.example.projectboardadmin.dto.AdminAccountDto;
import com.example.projectboardadmin.dto.security.AdminAccountService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
@TestConfiguration
public class TestSecurityConfig {

    @MockBean private AdminAccountService adminAccountService;

    @BeforeTestMethod
    public void securitySetup() {
        given(adminAccountService.searchUser(anyString()))
                .willReturn(Optional.of(createAdminAccountDto()));
        given(adminAccountService.saveUser(anyString(), anyString(), anySet(), anyString(), anyString(), anyString()))
                .willReturn(createAdminAccountDto());
    }

    private AdminAccountDto createAdminAccountDto() {
        return AdminAccountDto.of(
                "lbkTest",
                "pw",
                Set.of(RoleType.USER),
                "lbk-test@email.com",
                "lbk-test",
                "test memo"
        );
    }

}
