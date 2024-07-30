package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.QuestionDto;
import com.maathru.backend.Application.dto.response.QuestionResponse;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.exception.UnauthorizedException;
import com.maathru.backend.Domain.mapper.QuestionMapper;
import com.maathru.backend.External.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final JwtService jwtService;

    public ResponseEntity<String> addQuestion(QuestionDto questionDto) {

        User currentUser = jwtService.getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new NotFoundException("Author not found");
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
            throw new NotFoundException("Questions not found");
        }

        List<QuestionResponse> questionResponses = QuestionMapper.toQuestionResponseList(questions);
        return ResponseEntity.ok(questionResponses);
    }

    public ResponseEntity<Page<QuestionResponse>> getAllQuestionsWithPagination(int offset, int pageSize) {
        Page<Question> questions = questionRepository.findAll(PageRequest.of(offset, pageSize));
        if (questions.isEmpty()) {
            throw new NotFoundException("Questions not found");
        }

        Page<QuestionResponse> questionResponses = QuestionMapper.toQuestionResponsePage(questions);
        return ResponseEntity.ok(questionResponses);
    }

    public ResponseEntity<QuestionResponse> getQuestion(long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            return ResponseEntity.ok(QuestionMapper.toQuestionResponse(optionalQuestion.get()));
        } else {
            throw new NotFoundException("Question not found");
        }
    }

    public ResponseEntity<String> deleteQuestion(long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            if(jwtService.getCurrentUser().getUserId() != optionalQuestion.get().getCreatedBy().getUserId()){
                throw new UnauthorizedException("You are not authorized to delete this question");
            }
            questionRepository.delete(optionalQuestion.get());
            return ResponseEntity.ok().body("Question deleted Successfully");
        } else {
            throw new NotFoundException("Question not found");
        }
    }

    public ResponseEntity<List<QuestionResponse>> searchQuestionsByKeyword(String keyword) {
        List<Question> questions = questionRepository.findByKeywordsContainingIgnoreCase(keyword);
        if (questions.isEmpty()) {
            throw new NotFoundException("Questions not found");
        }

        List<QuestionResponse> questionResponses = QuestionMapper.toQuestionResponseList(questions);
        return ResponseEntity.ok(questionResponses);
    }

    public ResponseEntity<String> editQuestion(long id, QuestionDto questionDto) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            if(jwtService.getCurrentUser().getUserId() != optionalQuestion.get().getCreatedBy().getUserId()){
                throw new UnauthorizedException("You are not authorized to edit this question");
            }
            Question question = optionalQuestion.get();
            question.setTitle(questionDto.getTitle());
            question.setDescription(questionDto.getDescription());
            question.setKeywords(questionDto.getKeywords());
            question.setUpdatedBy(jwtService.getCurrentUser());

            question = questionRepository.save(question);
            log.info("Question:{} updated successfully by {}", question.getQuestionId(), jwtService.getCurrentUser().getEmail());

            return ResponseEntity.status(201).body("Question updated successfully");
        } else {
            throw new NotFoundException("Question not found");
        }
    }
}
