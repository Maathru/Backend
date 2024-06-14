package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}