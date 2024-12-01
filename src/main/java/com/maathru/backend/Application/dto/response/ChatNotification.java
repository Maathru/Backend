package com.maathru.backend.Application.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ChatNotification {
    private long id;
    private long senderId;
    private long recipientId;
    private String content;
}
