package com.loco.demo.repository.MessageRepo;

import com.loco.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, String> {
    List<Message> findByChatIdOrderByTimeStamp(String chatId);
}
