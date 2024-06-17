package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.AnswerDto;
import com.maathru.backend.Domain.entity.Answer;
import com.maathru.backend.Domain.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/answer")
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping()
    public ResponseEntity<Answer> createAnswer(@RequestBody AnswerDto answerDto) {
        return answerService.createAnswer(answerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable Long id) {
        return answerService.getAnswer(id);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Iterable<Answer>> getAnswerByQuestion(@PathVariable Long id) {
        return answerService.getAnswerByQuestion(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Answer> deleteAnswer(@PathVariable Long id) {
        return answerService.deleteAnswer(id);
    }
}
