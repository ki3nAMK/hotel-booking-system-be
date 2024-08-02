package com.loco.demo.entity;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Message {
    @Id
    @Column(name = "id", length = 50)
    private String id;

    @Column(name = "chat_id")
    private String chatId;

    @Lob
    @Column(columnDefinition = "TEXT", name = "content")
    private String content;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private User senderId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id")
    private User recipientId;
    @Column(name = "time_stamp", columnDefinition = "DATETIME")
    private String timeStamp;
}
