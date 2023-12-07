package com.messaginapps.simplemessaging.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class ChatNotification {
    @Id
    private String id;

    private String senderId;
    private String recipientId;
    private String content;
}
