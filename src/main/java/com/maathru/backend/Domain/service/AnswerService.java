package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.AnswerDto;
import com.maathru.backend.Domain.entity.Answer;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.AnswerNotFoundException;
import com.maathru.backend.Domain.exception.QuestionNotFoundException;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.AnswerRepository;
import com.maathru.backend.External.repository.QuestionRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public ResponseEntity<Answer> createAnswer(AnswerDto answerDto) {
        Optional<Question> optionalQuestion = questionRepository.findById(answerDto.getQuestion());
        Optional<User> optionalUser = userRepository.findById(answerDto.getAuthor());

        if (optionalQuestion.isPresent()) {
            if (optionalUser.isPresent()) {
                Answer answer = new Answer();
                answer.setAnswer(answerDto.getAnswer());
                answer.setQuestion(optionalQuestion.get());
                answer.setAuthor(optionalUser.get());
                return ResponseEntity.ok(answerRepository.save(answer));
            } else {
                log.error("user not found");
                throw new UserNotFoundException("Author not found");
            }
        } else {
            log.error("Question not found");
            throw new QuestionNotFoundException("Question not found");
        }
    }

    public ResponseEntity<Answer> getAnswer(Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        if (optionalAnswer.isPresent()) {
            return ResponseEntity.ok(optionalAnswer.get());
        } else {
            log.error("Answer not found");
            throw new AnswerNotFoundException("Answer not found");
        }
    }


    public ResponseEntity<Iterable<Answer>> getAnswerByQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            Optional<Iterable<Answer>> answers = answerRepository.findByQuestion(optionalQuestion.get());
            if (answers.isPresent()) {
                return ResponseEntity.ok(answers.get());
            } else {
                log.error("Answers not found");
                throw new AnswerNotFoundException("Answers not found");
            }
        } else {
            log.error("Question not found");
            throw new QuestionNotFoundException("Question not found");
        }
    }

    public ResponseEntity<Answer> deleteAnswer(Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        if (optionalAnswer.isPresent()) {
            answerRepository.delete(optionalAnswer.get());
            return ResponseEntity.ok(optionalAnswer.get());
        } else {
            log.error("Answer not found");
            throw new AnswerNotFoundException("Answer not found");
        }
    }
}
