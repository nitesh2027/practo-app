package com.example.practo.Service;

import com.example.practo.Entity.Appointment;

public interface PdfService {

    byte[] generateAppointmentPdf(Appointment appointment);
}