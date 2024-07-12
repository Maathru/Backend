package com.maathru.backend.External.repository;

import com.maathru.backend.Domain.entity.Answer;
import com.maathru.backend.Domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Iterable<Answer>> findByQuestion(Question question);
}