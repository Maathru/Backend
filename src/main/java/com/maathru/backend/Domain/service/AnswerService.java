package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.AnswerDto;
import com.maathru.backend.Application.dto.response.AnswerResponse;
import com.maathru.backend.Application.dto.response.QuestionResponse;
import com.maathru.backend.Domain.entity.Answer;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.AnswerNotFoundException;
import com.maathru.backend.Domain.exception.QuestionNotFoundException;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.Domain.mapper.AnswerMapper;
import com.maathru.backend.Domain.mapper.QuestionMapper;
import com.maathru.backend.External.repository.AnswerRepository;
import com.maathru.backend.External.repository.QuestionRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public ResponseEntity<String> addAnswer(AnswerDto answerDto) {

        User currentUser = getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new UserNotFoundException("Author not found");
        }

        Optional<Question> optionalQuestion = questionRepository.findById(answerDto.getQuestion());

        if (optionalQuestion.isEmpty()) {
            throw new QuestionNotFoundException("Question not found");
        }

        Answer answer = new Answer();
        answer.setAnswer(answerDto.getAnswer());
        answer.setQuestion(optionalQuestion.get());
        answer.setCreatedBy(currentUser);
        answer.setUpdatedBy(currentUser);
        answerRepository.save(answer);

        log.info("Answer:{} added successfully for question:{} by {}", answer.getAnswerId(), answer.getQuestion().getQuestionId(), currentUser.getEmail());
        return ResponseEntity.status(201).body("Answer added successfully");
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


    public ResponseEntity<List<AnswerResponse>> getAnswerByQuestion(Long id) {
        log.info("getAnswerByQuestion");
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isEmpty()) {
            throw new QuestionNotFoundException("Question not found");
        }

        Optional<Iterable<Answer>> answers = answerRepository.findByQuestion(optionalQuestion.get());
        if (answers.isEmpty()) {
            throw new AnswerNotFoundException("Answers not found");
        }

        List<AnswerResponse> answerResponses = AnswerMapper.toAnswerResponseList(answers);
        return ResponseEntity.ok(answerResponses);
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

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            return optionalUser.orElseGet(User::new);
        }
        return new User();
    }
}
