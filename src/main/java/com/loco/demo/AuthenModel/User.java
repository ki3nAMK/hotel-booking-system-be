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
    @Column(name = "id" ,nullable = false, length = 50)
    private String userId;
    @Column(name = "user_name", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "decode_password")
    private String decodePassword;
    private String name;
    private String email;
    private String avatar;
    @Column(name = "phone")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities ;

    public User() {
    }


    public User(String fakeID, String username, String password, String encodedPassword, Set<Role> authorities) {
        this.userId = fakeID;
        this.username = username;
        this.password = encodedPassword;
        this.decodePassword = password;
        this.authorities = authorities;
    }

    public User(String idGeneratedByUUID, String usernameGeneratedByUUID, String encodedPassword, String name, String avatar, String email, Set<Role> roles) {
        this.userId = idGeneratedByUUID;
        this.username = usernameGeneratedByUUID;
        this.password = encodedPassword;
        this.decodePassword = Const.passwordForGoogleLoginAndRegister;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.authorities = roles;
    }

    public User(String encodedUserId, String username, String encodedPassword, String email, String phoneNumber, Set<Role> roles, String password) {
        this.userId = encodedUserId;
        this.username = username;
        this.decodePassword = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authorities = roles;
        this.password = encodedPassword;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDecodePassword() {
        return decodePassword;
    }

    public void setDecodePassword(String encodePassword) {
        this.decodePassword = encodePassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
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
