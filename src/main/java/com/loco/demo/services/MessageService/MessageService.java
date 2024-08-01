package com.loco.demo.services.MessageService;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.DTO.JSON.Socket.MessageRequest;
import com.loco.demo.entity.Message;
import com.loco.demo.repository.AuthenRepo.UserAuthenRepo;
import com.loco.demo.repository.MessageRepo.MessageRepo;
import com.loco.demo.services.ChatRoom.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepo messageRepo;
    private final ChatRoomService chatRoomService;
    private final UserAuthenRepo userAuthenRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo, ChatRoomService chatRoomService, UserAuthenRepo userAuthenRepo) {
        this.messageRepo = messageRepo;
        this.chatRoomService = chatRoomService;
        this.userAuthenRepo = userAuthenRepo;
    }
    public Message save(MessageRequest messageRequest) {
        User senderUser = userAuthenRepo.findUserByUserId(messageRequest.senderId()).isPresent()
                ? userAuthenRepo.findUserByUserId(messageRequest.senderId()).get()
                : null;
        User receiverUser = userAuthenRepo.findUserByUserId(messageRequest.recipientId()).isPresent()
                ? userAuthenRepo.findUserByUserId(messageRequest.recipientId()).get()
                : null;
        String chatId = chatRoomService.getChatRoomId(
                messageRequest.senderId(),
                messageRequest.recipientId(),
                true).orElseThrow();
        String timestamp = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Message message = Message.builder()
                .id(UUID.randomUUID().toString())
                .chatId(chatId)
                .recipientId(receiverUser)
                .senderId(senderUser)
                .content(messageRequest.content())
                .timeStamp(timestamp)
                .build();
        messageRepo.save(message);
        return message;
    }

    public List<Message> findChatMessages(String senderId, String recipientId) {
        Optional<String> chatId = chatRoomService.getChatRoomId(senderId, recipientId,true);
        return chatId.map(messageRepo::findByChatIdOrderByTimeStamp).orElse(new ArrayList<Message>());
    }
}
