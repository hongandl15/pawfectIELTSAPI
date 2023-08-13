package com.pawfectielts.service;

import com.pawfectielts.entity.Recording;

import java.util.List;

public interface RecordingService {
    List<Recording> findAllRecording();
    Recording findRecordingByPartId(Long partId);
}
