package com.pawfectielts.dto.OpenAIDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usage{
    public int prompt_tokens;
    public int completion_tokens;
    public int total_tokens;
}