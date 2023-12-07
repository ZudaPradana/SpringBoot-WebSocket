package com.messaginapps.simplemessaging.service;

import com.messaginapps.simplemessaging.model.RoomChat;
import com.messaginapps.simplemessaging.repository.RoomChatRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomChatService {
    private final RoomChatRepository roomChatRepository;

    public RoomChatService(RoomChatRepository roomChatRepository){
        this.roomChatRepository = roomChatRepository;
    }

    public Optional<String> getRoomChatId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {
        return roomChatRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(RoomChat::getChatId)
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        return Optional.of(createChatId(senderId, recipientId));
                    }
                    return Optional.empty();
                });
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);

        RoomChat senderRecipient = RoomChat.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        RoomChat recipientSender = RoomChat.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        roomChatRepository.save(senderRecipient);
        roomChatRepository.save(recipientSender);

        return chatId; // Mengembalikan nilai chatId
    }

}
