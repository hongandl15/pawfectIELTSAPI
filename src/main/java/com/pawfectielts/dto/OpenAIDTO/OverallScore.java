package com.pawfectielts.dto.OpenAIDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OverallScore {
    private List<ScoreDTO> listScore;
}
