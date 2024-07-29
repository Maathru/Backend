package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.QuestionDto;
import com.maathru.backend.Application.dto.response.QuestionResponse;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Domain.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/question")
@RequiredArgsConstructor
@Validated
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping()
    public ResponseEntity<String> addQuestion(@RequestBody @Valid QuestionDto questionDto) {
        return questionService.addQuestion(questionDto);
    }

    @GetMapping()
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<QuestionResponse>> getAllQuestionsWithDefaultPagination(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize) {
        return questionService.getAllQuestionsWithPagination(offset, pageSize);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<QuestionResponse>> getAllQuestionsWithPagination(
            @PathVariable int offset,
            @PathVariable int pageSize) {
        return questionService.getAllQuestionsWithPagination(offset, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable long id) {
        return questionService.getQuestion(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable long id) {
        return questionService.deleteQuestion(id);
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<List<QuestionResponse>> searchQuestion(@PathVariable String keyword) {
        return questionService.searchQuestionsByKeyword(keyword);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editQuestion(@PathVariable long id, @RequestBody @Valid QuestionDto questionDto) {
        return questionService.editQuestion(id, questionDto);
    }
}
