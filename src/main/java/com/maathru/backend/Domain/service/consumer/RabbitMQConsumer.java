package com.maathru.backend.Domain.service.consumer;

import com.maathru.backend.Application.dto.request.SignupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(SignupDto signupDto) {
        try {
            log.info("Received message: {}", signupDto.toString());
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
        }
    }
}
