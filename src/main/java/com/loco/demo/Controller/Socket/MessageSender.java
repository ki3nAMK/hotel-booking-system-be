package com.loco.demo.Controller.Socket;

import com.loco.demo.DTO.JSON.Socket.MessageRequest;
import com.loco.demo.entity.ChatNotification;
import com.loco.demo.entity.Message;
import com.loco.demo.services.MessageService.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@CrossOrigin
public class MessageSender {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    @Autowired
    public MessageSender(SimpMessagingTemplate simpMessagingTemplate, MessageService messageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageRequest messageRequest) {
        System.out.println(messageRequest);
        Message savedMsg = messageService.save(messageRequest);
        simpMessagingTemplate.convertAndSendToUser(
                messageRequest.recipientId(), "/queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        savedMsg.getSenderId().getUserId(),
                        savedMsg.getRecipientId().getUserId(),
                        savedMsg.getContent()
                )
        );
    }

    @GetMapping("/api/v1/ws/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<Message>> findChatMessages(@PathVariable String senderId,
                                                          @PathVariable String recipientId) {
        return ResponseEntity
                .ok(messageService.findChatMessages(senderId, recipientId));
    }
}
