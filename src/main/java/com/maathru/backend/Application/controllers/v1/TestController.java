package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.SignupDto;
import com.maathru.backend.Domain.service.publisher.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final RabbitMQProducer rabbitMQProducer;

    @PostMapping()
    public ResponseEntity<String> sendMessage(@RequestBody SignupDto signupDto) {
        rabbitMQProducer.sendMessage(signupDto);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }
}
