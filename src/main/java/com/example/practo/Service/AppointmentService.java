package com.example.practo.Service;

import com.example.practo.Entity.Appointment;
import com.example.practo.Payload.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    public Appointment bookAppointment(AppointmentDto dto);
    public List<AppointmentDto> getAllApointments(int pageNo, int pageSize, String sortBy, String sortDir);
    String cancelAppointment(Long id);
    AppointmentDto rescheduleAppointment(
            Long id,
            AppointmentDto dto);
}
