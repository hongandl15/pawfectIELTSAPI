package com.pawfectielts.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class AnswerDTO {
    private ArrayList<String> answer;
    private ArrayList<String> topic;
}
