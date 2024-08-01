package com.loco.demo.services.ChatRoom;

import com.loco.demo.AuthenModel.User;
import com.loco.demo.entity.ChatRoom;
import com.loco.demo.repository.AuthenRepo.UserAuthenRepo;
import com.loco.demo.repository.ChatRoomRepo.ChatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChatRoomService {

    private final ChatRoomRepo chatRoomRepo;
    private final UserAuthenRepo userAuthenRepo;

    @Autowired
    public ChatRoomService(ChatRoomRepo chatRoomRepo, UserAuthenRepo userAuthenRepo) {
        this.chatRoomRepo = chatRoomRepo;
        this.userAuthenRepo = userAuthenRepo;
    }

    public Optional<String> getChatRoomId(String sender,String recipient,Boolean createNewRoomIfNotExist) {
        User senderUser = userAuthenRepo.findUserByUserId(sender).isPresent()
                ? userAuthenRepo.findUserByUserId(sender).get()
                : null;
        User receiverUser = userAuthenRepo.findUserByUserId(recipient).isPresent()
                ? userAuthenRepo.findUserByUserId(recipient).get()
                : null;
        if(senderUser != null && receiverUser != null) {
            Optional<ChatRoom> chatRoom = chatRoomRepo.findChatRoomBySenderIdAndRecipientId(senderUser,receiverUser);
            return chatRoom.map(ChatRoom::getChatId).or(() -> {
                if (createNewRoomIfNotExist) {
                    String chatId = createChat(senderUser, receiverUser);
                    return Optional.of(chatId);
                }
                return Optional.empty();
            });
        }
        return Optional.empty();
    }

    private String createChat(User sender,User recipient) {
        String chatId = String.format("%s_%s",sender.getUserId(), sender.getUserId());
        String chatIdRecipient = UUID.randomUUID().toString();
        ChatRoom chatRoomRecipient = ChatRoom.builder()
                .id(chatIdRecipient)
                .chatId(chatId)
                .senderId(sender)
                .recipientId(recipient)
                .build();
        String chatIdSender = UUID.randomUUID().toString();
        ChatRoom chatRoomSender = ChatRoom.builder()
                .id(chatIdSender)
                .chatId(chatId)
                .senderId(recipient)
                .recipientId(sender)
                .build();
        chatRoomRepo.save(chatRoomRecipient);
        chatRoomRepo.save(chatRoomSender);
        return chatId;
    }
}