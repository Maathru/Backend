package com.maathru.backend.Domain.mapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.maathru.backend.Domain.entity.Answer;
import com.maathru.backend.Domain.entity.Question;
import com.maathru.backend.Application.dto.response.QuestionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class QuestionMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static QuestionResponse toQuestionResponse(Question question) {
        QuestionResponse response = new QuestionResponse();
        response.setId(question.getQuestionId());
        response.setTitle(question.getTitle());
        response.setDescription(question.getDescription());
        response.setKeywords(question.getKeywords());
        response.setNoOfAnswers(question.getAnswers().isEmpty() ?   0  : question.getAnswers().size());
        response.setAuthorName((question.getCreatedBy() != null) ? question.getCreatedBy().getFirstName() + " " + question.getCreatedBy().getLastName() : null);
        response.setAuthorId((question.getCreatedBy() != null) ? question.getCreatedBy().getUserId() : null);
        response.setCreatedAt(question.getCreatedAt().format(formatter));
        return response;
    }

    public static List<QuestionResponse> toQuestionResponseList(List<Question> questions) {
        return questions.stream()
                .map(QuestionMapper::toQuestionResponse)
                .collect(Collectors.toList());
    }

    public static Page<QuestionResponse> toQuestionResponsePage(Page<Question> questions) {
        List<QuestionResponse> questionResponses = questions.stream()
                .map(QuestionMapper::toQuestionResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(questionResponses, PageRequest.of(questions.getNumber(), questions.getSize(), questions.getSort()), questions.getTotalElements());
    }
}
