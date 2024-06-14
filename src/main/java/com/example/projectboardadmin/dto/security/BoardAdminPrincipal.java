package com.example.projectboardadmin.dto.security;

import com.example.projectboardadmin.domain.constant.RoleType;
import com.example.projectboardadmin.dto.UserAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record BoardAdminPrincipal(
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String email,
        String nickname,
        String memo,
        Map<String, Object> oAuth2Attributes

) implements UserDetails, OAuth2User {

    // 카카오인증(OAuth2인증)을 타지 않은 경우
    public static BoardAdminPrincipal of(String username, String password, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        return BoardAdminPrincipal.of(username, password, roleTypes, email, nickname, memo, Map.of());
    }

    // 카카오인증(OAUth2인증)을 탄 경우
    public static BoardAdminPrincipal of(String username, String password, Set<RoleType> roleTypes, String email, String nickname, String memo, Map<String, Object> oAuth2Attributes) {

        return new BoardAdminPrincipal(
                username,
                password,
                roleTypes.stream()
                        .map(RoleType::getRoleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                email,
                nickname,
                memo,
                oAuth2Attributes
        );
    }

    public static BoardAdminPrincipal from(UserAccountDto dto) {
        return BoardAdminPrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.roleTypes(),
                dto.email(),
                dto.nickname(),
                dto.memo()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                username,
                password,
                authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(RoleType::valueOf)
                        .collect(Collectors.toUnmodifiableSet())
                ,
                email,
                nickname,
                memo
        );
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    // 권한 설정 : 자기 글만 쓰고 지을 수 있도록
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    // 디테일한 설정은 하지않고 스프링 시큐리티 설정에 위임하기 때문에 true 로 반환
    // 관련된 룰을 정하고 설정한다면 그때 다시 보면 될 것
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    @Override
    public Map<String, Object> getAttributes() { return oAuth2Attributes; }
    @Override
    public String getName() { return username; }

}
