package com.loco.demo.DTO.AuthenDTO;

import java.util.Objects;

public class GoogleLoginDTO {
    private String email;
    private String family_name;
    private String given_name;
    private String name;
    private String picture;

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "GoogleLogin{" +
                "email='" + email + '\'' +
                ", family_name='" + family_name + '\'' +
                ", given_name='" + given_name + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoogleLoginDTO that)) return false;
        return Objects.equals(email, that.email) && Objects.equals(family_name, that.family_name) && Objects.equals(given_name, that.given_name) && Objects.equals(name, that.name) && Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, family_name, given_name, name, picture);
    }

    public GoogleLoginDTO(String email, String family_name, String given_name, String name, String picture) {
        this.email = email;
        this.family_name = family_name;
        this.given_name = given_name;
        this.name = name;
        this.picture = picture;
    }
}
