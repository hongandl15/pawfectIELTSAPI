package com.pawfectielts.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAnswerDTO {
    private int orderNumber;
    private boolean correct;
    private String answer;
}
