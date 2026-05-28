package com.example.practo.Service;

import com.example.practo.Entity.Appointment;
import com.example.practo.Service.PdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public byte[] generateAppointmentPdf(Appointment appointment) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // UPDATED A6 PAGE SIZE
            Document document = new Document(PageSize.A6, 20, 20, 20, 20);
            PdfWriter.getInstance(document,out);
            document.open();

            // SMALLER FONT SIZE
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph title = new Paragraph("Practo Appointment Receipt", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // SMALL SPACE
            document.add(new Paragraph(""));
            document.add(new Paragraph("Appointment ID : " + appointment.getAppointmentId()));
            document.add(new Paragraph("Patient Name : " + appointment.getPatient().getPatientName()));
            document.add(new Paragraph("Doctor Name : " + appointment.getDoctor().getDoctorName()));
            document.add(new Paragraph("Appointment Date : " + appointment.getAppointmentDate()));
            document.add(new Paragraph("Appointment Time : " + appointment.getAppointmentTime()));
            document.add(new Paragraph("Status : " + appointment.getStatus()));
            document.close();
            return out.toByteArray();

        }
        catch (Exception e){
            throw new RuntimeException("PDF Generation Failed");
        }
    }
}