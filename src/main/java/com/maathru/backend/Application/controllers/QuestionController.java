package com.maathru.backend.Application.controllers;

import com.maathru.backend.Application.dto.request.QuestionDto;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping()
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionDto questionDto){
        return questionService.addQuestion(questionDto);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable long id){
        return questionService.getQuestion(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable long id){
        return questionService.deleteQuestion(id);
    }

}
