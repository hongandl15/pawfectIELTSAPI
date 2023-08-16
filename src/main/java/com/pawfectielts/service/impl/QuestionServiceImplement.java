package com.pawfectielts.service.impl;

import com.pawfectielts.dto.QuestionDTO;
import com.pawfectielts.dto.QuestionDetailChildDTO;
import com.pawfectielts.dto.QuestionDetailDTO;
import com.pawfectielts.entity.Question;
import com.pawfectielts.entity.QuestionDetail;
import com.pawfectielts.entity.QuestionDetailChild;
import com.pawfectielts.entity.QuestionType;
import com.pawfectielts.repositories.QuestionDetailChildRepository;
import com.pawfectielts.repositories.QuestionDetailRepository;
import com.pawfectielts.repositories.QuestionRepository;
import com.pawfectielts.repositories.QuestionTypeRepository;
import com.pawfectielts.service.QuestionService;
import org.modelmapper.internal.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImplement implements QuestionService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionDetailRepository questionDetailRepository;
    @Autowired
    private QuestionDetailChildRepository questionDetailChildRepository;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    public QuestionServiceImplement(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<QuestionDetailDTO> getQuestionDetailDTOsByQuestionId(Long questionId) {
        List<QuestionDetail> listQuestionDetail = questionDetailRepository.findByQuestionId(questionId);
        List<QuestionDetailDTO> questionDetailDTOList = listQuestionDetail.stream()
                .map(questionDetail -> modelMapper.map(questionDetail, QuestionDetailDTO.class))
                .collect(Collectors.toList());
        for (QuestionDetailDTO questionDetailDTO: questionDetailDTOList) {
            questionDetailDTO.setChild(getQuestionDetailChildDTOsByQuestionId(questionDetailDTO.getId()));
        }
        return questionDetailDTOList;
    }
    @Override
    public List<QuestionDetailChildDTO> getQuestionDetailChildDTOsByQuestionId(Long questionDetailId) {
        List<QuestionDetailChild> listQuestionDetailChild = questionDetailChildRepository.findByQuestionDetailId(questionDetailId);
        return listQuestionDetailChild.stream()
                .map(questionDetailChildDTO -> modelMapper.map(questionDetailChildDTO, QuestionDetailChildDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<QuestionDTO> getListQuestion(Long partId) {
        List<Question> listQuestionList = questionRepository.findAllByPartId(partId);
        List<QuestionDTO> listQuestionDTO = new ArrayList<>();
        for (Question question: listQuestionList) {
            QuestionDTO questionDTO = convertEntityToDTO(question.getId());
            listQuestionDTO.add(questionDTO);
        }
        return listQuestionDTO;
    }

    @Override
    public QuestionType getQuestionType(Long questionId) {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question != null) {
            return question.getQuestionType();
        }
        return null; // Handle if question is not found
    }

    @Override
    public QuestionDTO convertEntityToDTO(Long questionId) {
        QuestionDTO response = new QuestionDTO();
        Optional<Question> question = questionRepository.findById(questionId);
        response.setId(question.get().getId());
        response.setOrder(question.get().getOrder());
        response.setTitle(question.get().getTitle());
        response.setQuestionDetails(getQuestionDetailDTOsByQuestionId(questionId));
        return response;
    }

}
