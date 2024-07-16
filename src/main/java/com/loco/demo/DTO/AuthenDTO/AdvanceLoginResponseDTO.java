package com.loco.demo.DTO.AuthenDTO;

import com.loco.demo.DTO.Status.StatusAuthenticationRegister;
import com.loco.demo.utils.Converters.UserMinimalConverter;
import lombok.Getter;

import java.util.Objects;

@Getter
public class AdvanceLoginResponseDTO {
    private String message;
    private String code;
    private StatusAuthenticationRegister status;
    private UserMinimalConverter user_detail;
    private String access_token;

    public AdvanceLoginResponseDTO(String message, String code, StatusAuthenticationRegister status, UserMinimalConverter user_detail, String access_token) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.user_detail = user_detail;
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "AdvanceLoginResponseDTO{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", status=" + status +
                ", user_detail=" + user_detail +
                ", access_token='" + access_token + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdvanceLoginResponseDTO that)) return false;
        return Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getCode(), that.getCode()) && getStatus() == that.getStatus() && Objects.equals(getUser_detail(), that.getUser_detail()) && Objects.equals(getAccess_token(), that.getAccess_token());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage(), getCode(), getStatus(), getUser_detail(), getAccess_token());
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(StatusAuthenticationRegister status) {
        this.status = status;
    }

    public void setUser_detail(UserMinimalConverter user_detail) {
        this.user_detail = user_detail;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
