package com.bruno.abreu.blooddonorvalidator.controller;

import com.bruno.abreu.blooddonorvalidator.model.Donor;
import com.bruno.abreu.blooddonorvalidator.result.DonorResult;
import com.bruno.abreu.blooddonorvalidator.service.DonorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/donor")
@CrossOrigin
public class DonorController {

    private final DonorService donorService;

    private final ObjectMapper objectMapper;

    @Autowired
    public DonorController(DonorService donorService, ObjectMapper objectMapper) {
        this.donorService = donorService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<DonorResult> save(@RequestBody List<Donor> donors){
        donorService.save(donors);
        DonorResult donorResult = donorService.fetchDonorResult(donors);
        return ResponseEntity.status(HttpStatus.CREATED).body(donorResult);
    }

    @PostMapping(value = "/batch", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<DonorResult> save(@RequestPart MultipartFile file) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        List<Donor> donors = objectMapper.readValue(content, new TypeReference<>() {});

        DonorResult donorResult = donorService.fetchDonorResult(donors);
        return ResponseEntity.status(HttpStatus.CREATED).body(donorResult);
    }
}
