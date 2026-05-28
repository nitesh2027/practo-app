package com.example.practo.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.example.practo.Service.BloodReportService;

@RestController
@RequestMapping("/blood-report")

@CrossOrigin("*")

public class BloodReportController {

    @Autowired
    private BloodReportService
            bloodReportService;

     // http://localhost:8080/blood-report/analyze
    @PostMapping("/analyze")

    public String analyzeReport(

            @RequestParam("file")
            MultipartFile file) {

        return bloodReportService
                .analyzeReport(file);
    }
}