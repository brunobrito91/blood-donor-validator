package com.bruno.abreu.blooddonorvalidator.controller;

import com.bruno.abreu.blooddonorvalidator.model.Donor;
import com.bruno.abreu.blooddonorvalidator.result.DonorResult;
import com.bruno.abreu.blooddonorvalidator.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donor")
public class DonorController {

    private final
    DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping("/batch")
    public ResponseEntity<DonorResult> save(@RequestBody List<Donor> donors){
        DonorResult donorResult = donorService.fetchDonorResult(donors);
        return ResponseEntity.status(HttpStatus.CREATED).body(donorResult);
    }
}
