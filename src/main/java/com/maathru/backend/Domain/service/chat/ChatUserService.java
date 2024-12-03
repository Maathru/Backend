package com.maathru.backend.Domain.service.chat;

import com.maathru.backend.Application.dto.response.ChatUserDto;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.chat.ChatUser;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.External.repository.UserRepository;
import com.maathru.backend.External.repository.chat.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.maathru.backend.enumeration.Status;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatUserService {
    private final ChatUserRepository chatUserRepository;
    private final UserRepository userRepository;

    public void saveUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        ChatUser chatUser = chatUserRepository.findByUser(user).orElseGet(ChatUser::new);

        if (chatUser.getId() == 0) {
            chatUser.setUser(user);
        }
        chatUser.setStatus(Status.ONLINE);

        chatUserRepository.save(chatUser);
    }

    public void disconnect(long id) {
        User user = userRepository.findById(id).orElse(null);
        ChatUser chatUser = chatUserRepository.findByUser(user).orElse(null);
        if (chatUser != null) {
            chatUser.setStatus(Status.OFFLINE);
            chatUser.setLastSeen(LocalDateTime.now());
            chatUserRepository.save(chatUser);
        }
    }

    public List<ChatUserDto> findConnectedUsers() {
        List<ChatUser> chatUsers = chatUserRepository.findAll();

        return chatUsers.stream()
                .map((user) -> ChatUserDto.builder()
                        .userId(user.getUser().getUserId())
                        .name(String.format("%s %s", user.getUser().getFirstName(), user.getUser().getLastName()))
                        .role(user.getUser().getRole())
                        .status(user.getStatus())
                        .lastSeen(user.getLastSeen())
                        .build()
                ).toList();
    }
}
