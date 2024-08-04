package com.loco.demo.entity;

import java.util.Date;

import com.loco.demo.AuthenModel.User;

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
    @Column(name = "img_list", columnDefinition = "TEXT")
    private String imgList;
    @Column(name = "share_day", columnDefinition = "DATETIME")
    private Date shareDay;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
