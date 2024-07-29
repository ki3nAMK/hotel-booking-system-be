package com.loco.demo.entity;

import java.util.Date;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.utils.Converters.SecureUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
    @Column(name = "create_at", columnDefinition = "DATE")
    private Date createAt;
    @Column(name = "status")
    private Byte status;

    public SecureUser getUser(){
        return new SecureUser(user);
    }
}
