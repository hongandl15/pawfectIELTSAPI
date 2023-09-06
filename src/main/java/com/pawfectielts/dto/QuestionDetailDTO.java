package com.pawfectielts.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDetailDTO {
    private long id;
    private int order;
    private String name;
    private String correctAnswer;
    private List<QuestionDetailChildDTO> child;
}
