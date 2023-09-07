package com.pawfectielts.dto.AdminDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
public class ListTestRequest {
    private String testName;
    private List<AddReadingTestRequest> listTest;
    private Long setid;
    private Long skillid;

}
