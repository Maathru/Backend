package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.response.ChatNotification;
import com.maathru.backend.Application.dto.response.ChatUserDto;
import com.maathru.backend.Domain.entity.chat.ChatMessage;
import com.maathru.backend.Domain.service.chat.ChatMessageService;
import com.maathru.backend.Domain.service.chat.ChatUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatUserService chatUserService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedMessage = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getRecipientId()), "/queue/messages",
                new ChatNotification(
                        savedMessage.getId(),
                        savedMessage.getSenderId(),
                        savedMessage.getRecipientId(),
                        savedMessage.getContent()
                )
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId") long senderId,
            @PathVariable("recipientId") long recipientId
    ) {
        return ResponseEntity.ok(chatMessageService.findMessages(senderId, recipientId));
    }

    @MessageMapping("/user.addUser")
    public void addUser(@Payload long userId) {
        chatUserService.saveUser(userId);
        messagingTemplate.convertAndSend("/user/" + userId + "/public", userId);
    }

    @MessageMapping("/user.disconnectUser")
    public void disconnectUser(@Payload long userId) {
        chatUserService.disconnect(userId);
        messagingTemplate.convertAndSend("/user/" + userId + "/public", userId);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ChatUserDto>> findConnectedUsers() {
        return ResponseEntity.ok(chatUserService.findConnectedUsers());
    }
}
