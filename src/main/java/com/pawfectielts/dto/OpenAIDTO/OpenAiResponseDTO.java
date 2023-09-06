package com.pawfectielts.dto.OpenAIDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class OpenAiResponseDTO {
    public String id;
    public String object;
    public int created;
    public String model;
    public ArrayList<Choice> choices;
    public Usage usage;
}
