package com.pawfectielts.dto.AdminDTO;

import com.pawfectielts.entity.Topic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PartDTO {
    private Long id;
    private Topic topic;
    private int order;
    private String content;
}
