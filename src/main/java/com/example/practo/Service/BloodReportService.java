package com.example.practo.Service;

import org.springframework.web.multipart.MultipartFile;

public interface BloodReportService {

    String analyzeReport(
            MultipartFile file);
}