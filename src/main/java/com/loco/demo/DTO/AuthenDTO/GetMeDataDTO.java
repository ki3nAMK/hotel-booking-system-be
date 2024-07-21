package com.loco.demo.DTO.AuthenDTO;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.Status.StatusResponseAPI;
import com.loco.demo.utils.Converters.SecureUser;

import java.util.Objects;

public class GetMeDataDTO {
    private StatusResponseAPI status;
    private String message;
    private String code;
    private SecureUser data;

    public GetMeDataDTO(StatusResponseAPI status, String message, String code, SecureUser data) {
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

    public SecureUser getData() {
        return data;
    }

    public void setData(SecureUser data) {
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
