package com.messaginapps.simplemessaging.repository;

import com.messaginapps.simplemessaging.model.RoomChat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomChatRepository extends MongoRepository<RoomChat, String> {
    Optional<RoomChat> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
