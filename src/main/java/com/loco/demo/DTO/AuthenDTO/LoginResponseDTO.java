package com.loco.demo.DTO.AuthenDTO;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.TokenDTO;
import com.loco.demo.utils.Converters.SecureUser;

public class LoginResponseDTO {
    private SecureUser user ;
    private TokenDTO jwt ;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(User user, TokenDTO jwt) {
        this.user = new SecureUser(user);
        this.jwt = jwt;
    }

    public SecureUser getUser() {
        return user;
    }

    public void setUser(SecureUser user) {
        this.user = user;
    }

    public TokenDTO getJwt() {
        return jwt;
    }

    public void setJwt(TokenDTO jwt) {
        this.jwt = jwt;
    }
}

