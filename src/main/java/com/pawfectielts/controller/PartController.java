package com.pawfectielts.controller;


import com.pawfectielts.entity.Part;
import com.pawfectielts.service.PartService;
import com.pawfectielts.service.impl.PartServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/part")
public class PartController {


    private final PartServiceImplement partServiceImp;

    public PartController(PartServiceImplement partServiceImp) {
        this.partServiceImp = partServiceImp;
    }
    @GetMapping("/get/{testid}")
    public ResponseEntity<List<Part>> getPartContent(@PathVariable Long testid) {
        List<Part> response = partServiceImp.findPartByTestDetailId(testid);
        return ResponseEntity.ok().body(response);
    }

}
