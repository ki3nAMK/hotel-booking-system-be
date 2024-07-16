package com.loco.demo.DTO.AuthenDTO;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.Status.StatusResponseAPI;

import java.util.Objects;

public class GetMeDataDTO {
    private StatusResponseAPI status;
    private String message;
    private String code;
    private User data;

    public GetMeDataDTO(StatusResponseAPI status, String message, String code, User data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public StatusResponseAPI getStatus() {
        return status;
    }

    public void setStatus(StatusResponseAPI status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetMeDataDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetMeDataDTO that)) return false;
        return getStatus() == that.getStatus() && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getCode(), that.getCode()) && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getMessage(), getCode(), getData());
    }
}
