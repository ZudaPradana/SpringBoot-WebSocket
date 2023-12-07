package com.messaginapps.simplemessaging.controller;


import com.messaginapps.simplemessaging.model.ChatMessage;
import com.messaginapps.simplemessaging.model.ChatNotification;
import com.messaginapps.simplemessaging.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ChatRest {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatRest(ChatMessageService chatMessageService, SimpMessagingTemplate simpMessagingTemplate){
        this.chatMessageService = chatMessageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage saveMessage = chatMessageService.saveChat(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/message", ChatNotification.builder()
                .id(saveMessage.getId())
                .senderId(saveMessage.getSenderId())
                .recipientId(saveMessage.getRecipientId())
                .content(saveMessage.getContent())
                .build());
    }

    @GetMapping("/message/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessage(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId)
    {
        return ResponseEntity.ok(chatMessageService.findChatMessage(senderId, recipientId));
    }
}
