package com.pawfectielts.dto.AdminDTO;

import com.pawfectielts.dto.QuestionDTO;
import com.pawfectielts.entity.Set;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class AddReadingTestRequest {
    private PartDTO Part;
    private ArrayList<QuestionDTO> listQuestion;

}
