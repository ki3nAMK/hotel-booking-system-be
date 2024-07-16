package com.loco.demo.AuthenModel;

import com.loco.demo.utils.Constants.Const;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false, length = 50)
    private String userId;
    @Column(name = "user_name", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "encode_password")
    private String encodePassword;
    private String name;
    private String email;
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities ;

    public User(String fakeID, String username, String password, String encodedPassword, Set<Role> authorities) {
        this.userId = fakeID;
        this.username = username;
        this.password = password;
        this.encodePassword = encodedPassword;
        this.authorities = authorities;
    }

    public User(String idGeneratedByUUID, String usernameGeneratedByUUID, String encodedPassword, String name, String avatar, String email, Set<Role> roles) {
        this.userId = idGeneratedByUUID;
        this.username = usernameGeneratedByUUID;
        this.password = Const.passwordForGoogleLoginAndRegister;
        this.encodePassword = encodedPassword;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.authorities = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
