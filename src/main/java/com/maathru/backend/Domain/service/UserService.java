package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.CreateUserDto;
import com.maathru.backend.Application.dto.response.UserProfileDto;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }

        List<UserProfileDto> userProfileDtos = users.stream().map(user -> {
            UserProfileDto userProfileDto = new UserProfileDto();
            userProfileDto.setUserId(user.getUserId());
            userProfileDto.setFirstName(user.getFirstName());
            userProfileDto.setLastName(user.getLastName());
            userProfileDto.setEmail(user.getEmail());
            return userProfileDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(userProfileDtos);
    }

    public ResponseEntity<UserProfileDto> getUser(long id) {
        UserProfileDto userProfileDto = new UserProfileDto();
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            userProfileDto.setUserId(user.getUserId());
            userProfileDto.setFirstName(user.getFirstName());
            userProfileDto.setLastName(user.getLastName());
            userProfileDto.setEmail(user.getEmail());

            return ResponseEntity.ok(userProfileDto);
        }else{
            log.error("user not found");
            throw new UserNotFoundException("User not found");
        }
    }

    public ResponseEntity<User> addUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());

        user = userRepository.save(user);
        log.info("user added");
        return ResponseEntity.status(201).body(user);
    }

    public ResponseEntity<String> updateUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setEmail(user.getEmail());
            user.setPassword(user.getPassword());

            user = userRepository.save(user);
            log.info("User updated");
            return ResponseEntity.ok("User updated successfully");
        }else{
            log.error("user not found");
            throw new UserNotFoundException("User not found");
        }
    }
}
