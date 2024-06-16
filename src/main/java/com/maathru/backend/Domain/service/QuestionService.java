package com.maathru.backend.Domain.service;

import com.maathru.backend.Application.dto.request.QuestionDto;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.entity.User;
import com.maathru.backend.External.repository.QuestionRepository;
import com.maathru.backend.External.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public ResponseEntity<Question> addQuestion(QuestionDto questionDto){
        Question question = new Question();
        question.setQuestion(questionDto.getQuestion());
        question.setAuthor(userRepository.findById(questionDto.getAuthorUserId()).orElse(null));
        return ResponseEntity.ok(questionRepository.save(question));
    }

    public ResponseEntity<Iterable<Question>> getAllQuestions(){
        return ResponseEntity.ok(questionRepository.findAll());
    }

    public ResponseEntity<Question> getQuestion(long id){
        return ResponseEntity.ok(questionRepository.findById(id).orElse(null));
    }

    public ResponseEntity<Question> deleteQuestion(long id){
        Question question = questionRepository.findById(id).orElse(null);
        if(question == null){
            return ResponseEntity.notFound().build();
        }
        questionRepository.delete(question);
        return ResponseEntity.ok(question);
    }
}
