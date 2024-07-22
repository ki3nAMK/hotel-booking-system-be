package com.loco.demo.AuthenModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role")
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Getter
    @Id
    @Column(name="role_id", nullable = false, length = 50)
    private String roleId;
    @Column(name = "authority", length = 255)
    private String authority;
    @Getter
    @Setter
    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<User> users;

    public Role() {
        super();
    }

    public Role(String roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role roles)) return false;
        return Objects.equals(getRoleId(), roles.getRoleId()) && Objects.equals(getAuthority(), roles.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId(), getAuthority());
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleId=" + roleId +
                ", authority='" + authority + '\'' +
                '}';
    }
}

