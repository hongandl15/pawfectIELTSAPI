package com.pawfectielts.service;

import com.pawfectielts.dto.QuestionDTO;
import com.pawfectielts.dto.QuestionDetailChildDTO;
import com.pawfectielts.dto.QuestionDetailDTO;
import com.pawfectielts.entity.Question;
import com.pawfectielts.entity.QuestionDetail;
import com.pawfectielts.entity.QuestionDetailChild;
import com.pawfectielts.entity.QuestionType;

import java.util.List;

public interface QuestionService {

     List<QuestionDetailDTO> getQuestionDetailDTOsByQuestionId(Long questionId);


     QuestionDTO convertEntityToDTO(Long questionId);

     List<QuestionDetailChildDTO> getQuestionDetailChildDTOsByQuestionId(Long questionId);
     List<QuestionDTO> getListQuestion(Long partID);

     QuestionType getQuestionType(Long questionId);
}

