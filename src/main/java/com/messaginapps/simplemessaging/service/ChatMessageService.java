package com.messaginapps.simplemessaging.service;


import com.messaginapps.simplemessaging.model.ChatMessage;

import com.messaginapps.simplemessaging.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final RoomChatService roomChatService;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository, RoomChatService roomChatService){
        this.chatMessageRepository = chatMessageRepository;
        this.roomChatService = roomChatService;
    }

    public ChatMessage saveChat(ChatMessage chatMessage){
        var chatId = roomChatService.getRoomChatId(chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true)
                .orElseThrow(() -> new NoSuchElementException("Not Found"));
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessage(String senderId, String recipientId){
        var chatId = roomChatService.getRoomChatId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
