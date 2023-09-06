package com.pawfectielts.dto.OpenAIDTO;

import com.pawfectielts.dto.OpenAIDTO.MessageDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PassageInputDTO {
    private String model;
    private ArrayList<MessageDTO> messages;
}
