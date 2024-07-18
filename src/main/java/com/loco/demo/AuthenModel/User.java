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
    @Column(name = "id", nullable = false, length = 50)
    private String userId;
    @Column(name = "avatar", length = 255)
    private String avatar;
    @Column(name = "user_name", unique = true, length = 50)
    private String username;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "gender")
    private Byte gender;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "phone", length = 20)
    private String phoneNumber;
    @Column(name = "password", length = 255)
    private String password;
    @Column(name = "decode_password", length = 255)
    private String decodePassword;
    @Lob
    @Column(name = "sumary",columnDefinition = "TEXT")
    private String sumary;
    @Column(name = "location", length = 255)
    private String location;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",referencedColumnName = "role_id")
    private Role role;
    @Column(name = "online_status")
    private Boolean onlineStatus;
    @Column(name = "last_online")
    private Date lastOnline;
    

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_junction", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> authorities;



    public User() {
    }

    public User(String fakeID, String username, String password, String encodedPassword, Set<Role> authorities) {
        this.userId = fakeID;
        this.username = username;
        this.password = encodedPassword;
        this.decodePassword = password;
        this.authorities = authorities;
    }

    public User(String idGeneratedByUUID, String usernameGeneratedByUUID, String encodedPassword, String name,
            String avatar, String email, Set<Role> roles) {
        this.userId = idGeneratedByUUID;
        this.username = usernameGeneratedByUUID;
        this.password = encodedPassword;
        this.decodePassword = Const.passwordForGoogleLoginAndRegister;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.authorities = roles;
    }

    public User(String encodedUserId, String username, String encodedPassword, String email, String phoneNumber,
            Set<Role> roles, String password) {
        this.userId = encodedUserId;
        this.username = username;
        this.decodePassword = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authorities = roles;
        this.password = encodedPassword;
    }

    

    public User(String userId, String avatar, String username, String name, Byte gender, Date birthday, String email,
            String phoneNumber, String password, String decodePassword, String sumary, String location, Role role,
            Boolean onlineStatus, Date lastOnline, Set<Role> authorities) {
        this.userId = userId;
        this.avatar = avatar;
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.decodePassword = decodePassword;
        this.sumary = sumary;
        this.location = location;
        this.role = role;
        this.onlineStatus = onlineStatus;
        this.lastOnline = lastOnline;
        this.authorities = authorities;
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

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Date getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Date lastOnline) {
        this.lastOnline = lastOnline;
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
