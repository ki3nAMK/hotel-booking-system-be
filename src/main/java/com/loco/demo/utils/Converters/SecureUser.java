package com.loco.demo.utils.Converters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.loco.demo.AuthenModel.Role;
import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.Location;

import java.util.*;

@JsonInclude(Include.NON_NULL)
public class SecureUser{
    private String userId;
    private String avatar;
    private String username;
    private String name;
    private Byte gender;
    private Date birthday;
    private String email;
    private String phoneNumber;
    private String sumary;
    private String location;
    private Role role;
    private Boolean onlineStatus;
    private Date lastOnline;
    private Set<Role> authorities;
    private Location address;
    public SecureUser(User user) {
        this.userId = user.getUserId();
        this.avatar = user.getAvatar();
        this.username = user.getUsername();
        this.name = user.getName();
        this.gender = user.getGender();
        this.birthday = user.getBirthday();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.sumary = user.getSumary();
        this.location = user.getLocation();
        this.role = user.getRole();
        this.onlineStatus = user.getOnlineStatus();
        this.lastOnline = user.getLastOnline();
        this.authorities = (Set<Role>) user.getAuthorities();
        this.address=user.getAddress();
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public Set<Role> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
    public Location getAddress() {
        return address;
    }
    public void setAddress(Location address) {
        this.address = address;
    }
}
