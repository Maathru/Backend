package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.AnswerDto;
import com.maathru.backend.Application.dto.response.AnswerResponse;
import com.maathru.backend.Domain.entity.Answer;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.Domain.exception.NotFoundException;
import com.maathru.backend.Domain.exception.UnauthorizedException;
import com.maathru.backend.Domain.mapper.AnswerMapper;
import com.maathru.backend.External.repository.AnswerRepository;
import com.maathru.backend.External.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final JwtService jwtService;

    public ResponseEntity<String> addAnswer(AnswerDto answerDto) {

        User currentUser = jwtService.getCurrentUser();
        if (currentUser.getUserId() == 0) {
            throw new NotFoundException("Author not found");
        }

        Optional<Question> optionalQuestion = questionRepository.findById(answerDto.getQuestion());

        if (optionalQuestion.isEmpty()) {
            throw new NotFoundException("Question not found");
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
            throw new NotFoundException("Answer not found");
        }
    }


    public ResponseEntity<List<AnswerResponse>> getAnswerByQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isEmpty()) {
            throw new NotFoundException("Question not found");
        }

        Optional<Iterable<Answer>> answers = answerRepository.findByQuestion(optionalQuestion.get());
        if (answers.isEmpty()) {
            throw new NotFoundException("Answers not found");
        }

        List<AnswerResponse> answerResponses = AnswerMapper.toAnswerResponseList(answers);
        return ResponseEntity.ok(answerResponses);
    }

    public ResponseEntity<String> deleteAnswer(Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        if (optionalAnswer.isPresent()) {
            answerRepository.delete(optionalAnswer.get());
            return ResponseEntity.ok().body("Answer deleted successfully");
        } else {
            throw new NotFoundException("Answer not found");
        }
    }

    public ResponseEntity<String> editAnswer(Long id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        if (optionalAnswer.isPresent()) {
            if(jwtService.getCurrentUser().getUserId() != optionalAnswer.get().getCreatedBy().getUserId()){
                throw new UnauthorizedException("You are not authorized to edit this answer");
            }
            Answer answer = optionalAnswer.get();
            answer.setAnswer(answerDto.getAnswer());
            answerRepository.save(answer);
            return ResponseEntity.ok().body("Answer updated successfully");
        } else {
            throw new NotFoundException("Answer not found");
        }
    }
}
