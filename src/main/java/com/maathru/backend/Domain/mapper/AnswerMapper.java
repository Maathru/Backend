package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.response.AnswerResponse;
import com.maathru.backend.Domain.entity.Answer;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnswerMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static AnswerResponse toAnswerResponse(Answer answer) {
        AnswerResponse response = new AnswerResponse();
        response.setId(answer.getAnswerId());
        response.setAnswer(answer.getAnswer());
        response.setAuthorName((answer.getCreatedBy() != null) ? answer.getCreatedBy().getFirstName() + " " + answer.getCreatedBy().getLastName() : null);
        response.setCreatedAt(answer.getCreatedAt().format(formatter));
        return response;
    }

    public static List<AnswerResponse> toAnswerResponseList(Optional<Iterable<Answer>> optionalAnswers) {
        if (optionalAnswers.isEmpty()) {
            return Collections.emptyList();
        }

        Iterable<Answer> answers = optionalAnswers.get();
        List<AnswerResponse> answerResponseList = new ArrayList<>();

        for (Answer answer : answers) {
            AnswerResponse response = toAnswerResponse(answer);
            answerResponseList.add(response);
        }

        return answerResponseList;
    }
}
