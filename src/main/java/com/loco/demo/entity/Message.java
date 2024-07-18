package com.loco.demo.entity;

import java.util.Date;

import com.loco.demo.AuthenModel.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {
    @Id
    @Column(name = "id", length = 50)
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="chat_id")
    private ChatRoom chatId;
    @Lob
    @Column(columnDefinition = "TEXT",name="content")
    private String content;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sender_id")
    private User senderId;
    @OneToOne(fetch = FetchType.EAGER   )
    @JoinColumn(name="recipient_id")
    private User recipientId;
    @Column(name="time_stamp")
    private Date timeStamp;
}
