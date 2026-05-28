package com.example.practo.Payload;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    @Size(min = 2, max =20, message = "Should be 2 or more char required")
    private String patientName;

    @Min(value = 1,
            message = "Minimum age should be 1")
    @Max(value = 80,
            message = "Maximum age should be 80")
    private Integer age;

    @NotBlank(message = "Disease should be required")
    private String disease;

    @Pattern(
            regexp = "^\\+91[0-9]{10}$",
            message = "Mobile number must be in format +91XXXXXXXXXX"
    )
    private String mobile;
    @Email
    private String email;

    @Size(min = 6)
    private String password;
}