package com.example.practo.Controller;

import com.example.practo.Entity.Appointment;
import com.example.practo.Repository.AppointmentRepository;
import com.example.practo.Service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/appointment/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment Not Found"));
        byte[] pdf = pdfService.generateAppointmentPdf(appointment);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("appointment.pdf").build());
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}