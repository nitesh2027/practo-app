package com.example.practo.Controller;

import com.example.practo.Entity.Appointment;
import com.example.practo.Payload.AppointmentDto;
import com.example.practo.Service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //http://localhost:8080/api/appointments/book
    //Book Appointment
    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@Valid @RequestBody AppointmentDto dto, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        Appointment appointment = appointmentService.bookAppointment(dto);

        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    // GET ALL APPOINTMENTS
    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "appointmentId", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "Asc", required = false) String sortDir) {

        List<AppointmentDto> dtos = appointmentService.getAllApointments(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // CANCEL APPOINTMENT
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String>
    cancelAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.cancelAppointment(id));
    }

    // RESCHEDULE APPOINTMENT
    @PutMapping("/reschedule/{id}")
    public ResponseEntity<AppointmentDto>
    rescheduleAppointment(@PathVariable Long id, @RequestBody AppointmentDto dto) {

        return ResponseEntity.ok(appointmentService.rescheduleAppointment(id, dto));
    }
}