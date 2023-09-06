package com.pawfectielts.service;

import com.pawfectielts.dto.AdminDTO.AddListeningTestRequest;
import com.pawfectielts.dto.AdminDTO.AddReadingTestRequest;
import com.pawfectielts.dto.AdminDTO.AddWritingTestRequest;
import com.pawfectielts.dto.AdminDTO.ListTestRequest;

public interface AdminService {
    String addTestFull(ListTestRequest listTestRequest);

    String deleteTest(Long testid);
}
