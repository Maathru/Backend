package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.Application.dto.request.AnswerDto;
import com.maathru.backend.Application.dto.response.AnswerResponse;
import com.maathru.backend.Domain.entity.Answer;
import com.maathru.backend.Domain.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
@Validated
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping()
    public ResponseEntity<String> addAnswer(@RequestBody @Valid AnswerDto answerDto) {
        return answerService.addAnswer(answerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable Long id) {
        return answerService.getAnswer(id);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<List<AnswerResponse>> getAnswerByQuestion(@PathVariable Long id) {
        return answerService.getAnswerByQuestion(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Answer> deleteAnswer(@PathVariable Long id) {
        return answerService.deleteAnswer(id);
    }
}
