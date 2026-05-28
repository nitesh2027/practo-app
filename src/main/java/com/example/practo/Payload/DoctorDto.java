package com.example.practo.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    @Size(min = 3, max=30, message = "should be more than 3 or more character”")
    private String doctorName;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "Hospital name is required")
    private String hospitalName;

    @NotBlank(message = "Fees is required")
    private String fees;
}