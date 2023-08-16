package com.pawfectielts.controller;

import com.pawfectielts.dto.QuestionDTO;
import com.pawfectielts.entity.Recording;
import com.pawfectielts.service.impl.QuestionServiceImplement;
import com.pawfectielts.service.impl.RecordingServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/recording")
public class RecordingController {
    private final RecordingServiceImplement recordingServiceImplement;

    public RecordingController(RecordingServiceImplement recordingServiceImplement) {
        this.recordingServiceImplement = recordingServiceImplement;
    }
    @GetMapping("/get/{partid}")
    public ResponseEntity<Recording> getPartContent(@PathVariable Long partid) {
        Recording response = recordingServiceImplement.findRecordingByPartId(partid);
        return ResponseEntity.ok().body(response);
    }
}
