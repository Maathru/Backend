package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.QuestionDto;
import com.maathru.backend.Application.dto.response.QuestionResponse;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.QuestionNotFoundException;
import com.maathru.backend.Domain.exception.UserNotFoundException;
import com.maathru.backend.Domain.mapper.QuestionMapper;
import com.maathru.backend.External.repository.QuestionRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public ResponseEntity<String> addQuestion(QuestionDto questionDto) {

        User currentUser = getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new UserNotFoundException("Author not found");
        }

        Question question = new Question();
        question.setTitle(questionDto.getTitle());
        question.setDescription(questionDto.getDescription());
        question.setKeywords(questionDto.getKeywords());
        question.setCreatedBy(currentUser);
        question.setUpdatedBy(currentUser);

        question = questionRepository.save(question);
        log.info("Question:{} added successfully by {}", question.getQuestionId(), currentUser.getEmail());

        return ResponseEntity.status(201).body("Question added successfully");
    }

    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("Questions not found");
        }

        List<QuestionResponse> questionResponses = QuestionMapper.toQuestionResponseList(questions);
        return ResponseEntity.ok(questionResponses);
    }

    public ResponseEntity<Page<QuestionResponse>> getAllQuestionsWithPagination(int offset , int pageSize) {
        Page<Question> questions = questionRepository.findAll(PageRequest.of(offset,pageSize));
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("Questions not found");
        }

        Page<QuestionResponse> questionResponses = QuestionMapper.toQuestionResponsePage(questions);
        return ResponseEntity.ok(questionResponses);
    }

    public ResponseEntity<QuestionResponse> getQuestion(long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            return ResponseEntity.ok(QuestionMapper.toQuestionResponse(optionalQuestion.get()));
        } else {
            throw new QuestionNotFoundException("Question not found");
        }
    }

    public ResponseEntity<Question> deleteQuestion(long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            questionRepository.delete(optionalQuestion.get());
            return ResponseEntity.ok().body(optionalQuestion.get());
        } else {
            throw new QuestionNotFoundException("Question not found");
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
