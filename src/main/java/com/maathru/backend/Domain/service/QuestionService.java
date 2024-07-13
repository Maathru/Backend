package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.QuestionDto;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.QuestionNotFoundException;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.External.repository.QuestionRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public ResponseEntity<Question> addQuestion(QuestionDto questionDto) {
        Optional<User> optionalUser = userRepository.findById(questionDto.getAuthor());

        if (optionalUser.isPresent()) {
            Question question = new Question();
            question.setQuestion(questionDto.getQuestion());
            question.setAuthor(optionalUser.get());
            question = questionRepository.save(question);

            return ResponseEntity.status(201).body(question);
        } else {
            log.error("User not found");
            throw new UserNotFoundException("User not found");
        }
    }

    public ResponseEntity<Iterable<Question>> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();

        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("Questions not found");
        }
        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<Question> getQuestion(long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            return ResponseEntity.ok(optionalQuestion.get());
        } else {
            log.error("Question not found");
            throw new QuestionNotFoundException("Question not found");
        }
    }

    public ResponseEntity<Question> deleteQuestion(long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            questionRepository.delete(optionalQuestion.get());
            return ResponseEntity.ok().body(optionalQuestion.get());
        } else {
            log.error("Question not found");
            throw new QuestionNotFoundException("Question not found");
        }
    }
}
