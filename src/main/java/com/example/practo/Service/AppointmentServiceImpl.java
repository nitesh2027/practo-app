package com.example.practo.Service;

import com.example.practo.Entity.Appointment;
import com.example.practo.Entity.Doctor;
import com.example.practo.Entity.Patient;
import com.example.practo.Exception.ResourceNotFoundException;
import com.example.practo.Payload.AppointmentDto;
import com.example.practo.Repository.AppointmentRepository;
import com.example.practo.Repository.DoctorRepository;
import com.example.practo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl
        implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private WhatsAppService whatsAppService;

    @Autowired
    private EmailService emailService;
    // BOOK APPOINTMENT
    @Override
    public Appointment bookAppointment(
            AppointmentDto dto) {

        // FETCH DOCTOR
        Doctor doctor =
                doctorRepository.findById(
                        dto.getDoctorId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor Not Found By Id"));

        // FETCH PATIENT
        Patient patient =
                patientRepository.findById(
                        dto.getPatientId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient Not Found By Id"));

        // CREATE APPOINTMENT
        Appointment appointment =
                new Appointment();

        appointment.setAppointmentDate(
                dto.getAppointmentDate());

        appointment.setAppointmentTime(
                dto.getAppointmentTime());

        appointment.setStatus("BOOKED");

        appointment.setDoctor(doctor);

        appointment.setPatient(patient);

        // SAVE APPOINTMENT
        Appointment savedAppointment =
                appointmentRepository.save(
                        appointment);

        // COMMON MESSAGE
        String msg =
                "Hello "
                        + savedAppointment.getPatient()
                        .getPatientName()
                        + ", Appointment Booked Successfully";

        // SEND SMS
        smsService.sendSms(
                savedAppointment.getPatient()
                        .getMobile(),
                msg
        );

        System.out.println(
                "SMS SENT SUCCESSFULLY");

        // SEND WHATSAPP MESSAGE
        whatsAppService.sendWhatsAppMessage(
                savedAppointment.getPatient()
                        .getMobile(),
                msg
        );

        // EMAIL
        emailService.sendEmail(
                patient.getEmail(),
                "Appointment Confirmation",
                msg);

        System.out.println(
                "WHATSAPP MESSAGE SENT SUCCESSFULLY");

        return savedAppointment;
    }

    // GET ALL APPOINTMENTS
    @Override
    public List<AppointmentDto>
    getAllApointments(
            int pageNo,
            int pageSize,
            String sortBy,
            String sortDir) {

        Sort sort =
                sortDir.equalsIgnoreCase(
                        Sort.Direction.ASC.name())

                        ? Sort.by(
                        Sort.Direction.ASC,
                        sortBy)

                        : Sort.by(
                        Sort.Direction.DESC,
                        sortBy);

        Pageable pageable =
                PageRequest.of(
                        pageNo,
                        pageSize,
                        sort);

        Page<Appointment> all =
                appointmentRepository
                        .findAll(pageable);

        List<Appointment> appointments =
                all.getContent();

        List<AppointmentDto>
                appointmentDtos =
                appointments.stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());

        return appointmentDtos;
    }

    // CANCEL APPOINTMENT
    @Override
    public String cancelAppointment(
            Long id) {

        Appointment appointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Appointment Not Found"));

        appointment.setStatus(
                "CANCELLED");

        appointmentRepository.save(
                appointment);

        return "Appointment Cancelled Successfully";
    }

    // RESCHEDULE APPOINTMENT
    @Override
    public AppointmentDto
    rescheduleAppointment(
            Long id,
            AppointmentDto dto) {

        Appointment appointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Appointment Not Found"));

        appointment.setAppointmentDate(
                dto.getAppointmentDate());

        appointment.setAppointmentTime(
                dto.getAppointmentTime());

        appointment.setStatus(
                "RESCHEDULED");

        Appointment updatedAppointment =
                appointmentRepository.save(
                        appointment);

        return mapToDto(
                updatedAppointment);
    }

    // ENTITY TO DTO
    public AppointmentDto mapToDto(
            Appointment appointment) {

        AppointmentDto dto =
                new AppointmentDto();

        dto.setAppointmentId(
                appointment.getAppointmentId());

        dto.setDoctorName(
                appointment.getDoctor()
                        .getDoctorName());

        dto.setPatientName(
                appointment.getPatient()
                        .getPatientName());

        dto.setDoctorId(
                appointment.getDoctor()
                        .getDoctorId());

        dto.setPatientId(
                appointment.getPatient()
                        .getPatientId());

        dto.setAppointmentDate(
                appointment.getAppointmentDate());

        dto.setAppointmentTime(
                appointment.getAppointmentTime());

        dto.setStatus(
                appointment.getStatus());

        return dto;
    }
}