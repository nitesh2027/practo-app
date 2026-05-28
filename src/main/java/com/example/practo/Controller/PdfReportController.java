package com.example.practo.Controller;



import com.example.practo.Payload.BloodReportRequestDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController

@RequestMapping("/pdf")

@CrossOrigin("*")

public class PdfReportController {

    // http://localhost:8080/pdf/blood-report
    @PostMapping("/blood-report")

    public ResponseEntity<byte[]>
    generatePdf(

            @RequestBody
            BloodReportRequestDto dto) {

        try {

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    out
            );

            document.open();

            document.add(
                    new Paragraph(
                            "Blood Report"
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(
                            "Patient Name: "
                                    + dto.getPatientName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Hemoglobin: "
                                    + dto.getHemoglobin()
                    )
            );

            document.add(
                    new Paragraph(
                            "Blood Sugar: "
                                    + dto.getBloodSugar()
                    )
            );

            document.add(
                    new Paragraph(
                            "Cholesterol: "
                                    + dto.getCholesterol()
                    )
            );

            document.add(
                    new Paragraph(
                            "Blood Pressure: "
                                    + dto.getBloodPressure()
                    )
            );

            document.close();

            return ResponseEntity.ok()

                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,

                            "attachment; filename=blood-report.pdf"
                    )

                    .contentType(
                            MediaType.APPLICATION_PDF
                    )

                    .body(out.toByteArray());

        }

        catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError()
                    .build();
        }
    }
}