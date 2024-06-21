package com.example.projectboardadmin.domain;

import com.example.projectboardadmin.domain.constant.RoleType;
import com.example.projectboardadmin.domain.converter.RoleTypesConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class AdminAccount extends AuditingFields {
    @Id
    @Column(length = 50)
    private String userId;

    @Setter
    @Column(nullable = false)
    private String userPassword;

    // 일대다, 다대다 매핑 등으로 해결하지 않고 다른 방법 써보자
    // "1,2,3" 이런식으로 통으로 문자열을 DB의 한 컬럼에 저장하는 방법
    @Convert(converter = RoleTypesConverter.class)
    @Column(nullable = false)
    private Set<RoleType> roleTypes = new LinkedHashSet<>();

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickname;

    @Setter
    private String memo;

    public AdminAccount() {}

    private AdminAccount(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.roleTypes = roleTypes;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;    // 생성 시점에는 수정자와 생성자가 같다.
    }

    public static AdminAccount of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        return AdminAccount.of(userId, userPassword, roleTypes, email, nickname, memo, null);
    }

    public static AdminAccount of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo, String createdBy) {
        return new AdminAccount(userId, userPassword, roleTypes, email, nickname, memo, createdBy);
    }

    public void addRoleType(RoleType roleType) {
        this.getRoleTypes().add(roleType);
    }

    public void addRoleTypes(Collection<RoleType> roleTypes) {
        this.getRoleTypes().addAll(roleTypes);
    }

    public void removeRoleType(RoleType roleType) {
        this.getRoleTypes().remove(roleType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminAccount that)) return false;
        return this.getUserId() != null && this.getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }

}
