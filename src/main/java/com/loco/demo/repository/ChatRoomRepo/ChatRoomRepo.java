package com.loco.demo.repository.ChatRoomRepo;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepo extends JpaRepository<ChatRoom, String> {
    Optional<ChatRoom> findChatRoomBySenderIdAndRecipientId(User sender, User receiver);
}
