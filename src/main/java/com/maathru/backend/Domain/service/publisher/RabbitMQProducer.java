package com.maathru.backend.Domain.service.publisher;

import com.maathru.backend.Application.dto.request.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(SignupDto signupDto) {
        log.info("Message sent: {}", signupDto.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, signupDto);
    }
}
