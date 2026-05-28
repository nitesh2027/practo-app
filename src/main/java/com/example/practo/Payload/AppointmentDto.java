package com.example.practo.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    private Long appointmentId;
    @NotNull(message = "Doctor Id is required")
    private Long doctorId;

    @NotNull(message = "Patient Id is required")
    private Long patientId;

    @NotBlank(message = "Appointment date is required")
    private String appointmentDate;

    @NotBlank(message = "Appointment time is required")
    private String appointmentTime;
    private String status;

    private String doctorName;

    private String patientName;
}
