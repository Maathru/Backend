package com.maathru.backend.External.repository.chat;

import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.entity.chat.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    Optional<ChatUser> findByUser(User user);
}
