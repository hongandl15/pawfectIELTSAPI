package com.pawfectielts.dto.OpenAIDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreDTO {
    private double scoreTaskResponse;
    private double scoreCoherence;
    private double scoreLexical;
    private double scoreGrammar;
    private double overallScore;
    private String content;
}
