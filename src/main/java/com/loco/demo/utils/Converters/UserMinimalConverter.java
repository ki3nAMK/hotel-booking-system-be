package com.loco.demo.utils.Converters;

import com.loco.demo.AuthenModel.Role;
import lombok.Getter;

import java.util.Objects;
import java.util.Set;

@Getter
public class UserMinimalConverter {
    private String email;
    private String family_name;
    private String given_name;
    private String name;
    private String image;
    private Set<Role> authorities;

    @Override
    public String toString() {
        return "UserMinimalConverter{" +
                "email='" + email + '\'' +
                ", family_name='" + family_name + '\'' +
                ", given_name='" + given_name + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMinimalConverter that)) return false;
        return Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getFamily_name(), that.getFamily_name()) && Objects.equals(getGiven_name(), that.getGiven_name()) && Objects.equals(getName(), that.getName()) && Objects.equals(getImage(), that.getImage()) && Objects.equals(getAuthorities(), that.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFamily_name(), getGiven_name(), getName(), getImage(), getAuthorities());
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public UserMinimalConverter(String email, String family_name, String given_name, String name, String image, Set<Role> authorities) {
        this.email = email;
        this.family_name = family_name;
        this.given_name = given_name;
        this.name = name;
        this.image = image;
        this.authorities = authorities;
    }
}
