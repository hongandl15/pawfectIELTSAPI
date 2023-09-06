package com.pawfectielts.dto;
import com.pawfectielts.dto.UserAnswerDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;


@Getter
@Setter
public class TestResultDTO {
    private Long id;
    private Long userid;
    private double score;
    private int rightAnswer;
    private int wrongAnswer;
    private int skipAnswer;
    private ArrayList<UserAnswerDTO> userAnswers;
    private Long testid;
    private String testName;
    private String create_at;
}
