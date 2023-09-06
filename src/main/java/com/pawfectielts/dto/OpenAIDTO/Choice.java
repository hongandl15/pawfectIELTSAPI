package com.pawfectielts.dto.OpenAIDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Choice{
    public int index;
    public MessageDTO message;
    public String finish_reason;
}
