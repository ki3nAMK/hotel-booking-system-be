package com.loco.demo.DTO.AuthenDTO;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.TokenDTO;

public class LoginResponseDTO {
    private User user ;
    private TokenDTO jwt ;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(User user, TokenDTO jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TokenDTO getJwt() {
        return jwt;
    }

    public void setJwt(TokenDTO jwt) {
        this.jwt = jwt;
    }
}

