package com.pawfectielts.dto;

import com.pawfectielts.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDTO {
    private long id;
    private int order;
    private String title;
    private List<QuestionDetailDTO> questionDetails;
}
