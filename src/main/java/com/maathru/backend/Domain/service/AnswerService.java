package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.AnswerDto;
import com.maathru.backend.Domain.entity.Answer;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.External.repository.AnswerRepository;
import com.maathru.backend.External.repository.QuestionRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public ResponseEntity<Answer> createAnswer(AnswerDto answerDto){
        Answer answer = new Answer();
        answer.setContent(answerDto.getContent());
        answer.setQuestion(questionRepository.findById(answerDto.getQuestionId()).orElse(null));
        answer.setAuthor(userRepository.findById(answerDto.getAuthorUserId()).orElse(null));
        return ResponseEntity.ok(answerRepository.save(answer));
    }


    public ResponseEntity<Answer> getAnswer(Long id) {
        return ResponseEntity.ok(answerRepository.findById(id).orElse(null));
    }


    public ResponseEntity<Iterable<Answer>> getAnswerByQuestion(Long id) {
        Question question = questionRepository.findById(id).orElse(null);
        return ResponseEntity.ok(answerRepository.findByQuestion(question).orElse(null));
    }

    public ResponseEntity<Answer> deleteAnswer(Long id) {
        Answer answer = answerRepository.findById(id).orElse(null);
        if(answer == null){
            return ResponseEntity.notFound().build();
        }
        answerRepository.delete(answer);
        return ResponseEntity.ok(answer);
    }
}
