package com.loco.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "story")
public class Story {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @Lob
    @Column(name = "img", columnDefinition = "TEXT")
    private String img;
    @Column(name = "share_day", columnDefinition = "DATETIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private Date shareDay;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public SecureUser getUser() {
        return new SecureUser(this.user);
    }
}
