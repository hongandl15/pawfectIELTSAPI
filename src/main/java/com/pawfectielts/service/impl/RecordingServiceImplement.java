package com.pawfectielts.service.impl;

import com.pawfectielts.entity.Recording;
import com.pawfectielts.entity.Set;
import com.pawfectielts.repositories.RecordingRepository;
import com.pawfectielts.repositories.SetRepository;
import com.pawfectielts.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecordingServiceImplement implements RecordingService {
    @Autowired
    private RecordingRepository recordingRepository;

    @Override
    public List<Recording> findAllRecording() {
        return recordingRepository.findAll();
    }

    @Override
    public Recording findRecordingByPartId(Long partId) {
        return recordingRepository.findByPartId(partId);
    }
}
