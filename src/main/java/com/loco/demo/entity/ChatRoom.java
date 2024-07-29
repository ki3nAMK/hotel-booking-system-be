package com.loco.demo.entity;

import java.util.List;

import com.loco.demo.AuthenModel.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @Column(length = 50)
    private String id;

    private String chatId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private User senderId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id")
    private User recipientId;

    // Direction
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chatId")
    private List<Message> message;

}
