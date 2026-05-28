package com.example.practo.Service;

import com.example.practo.Entity.Patient;
import com.example.practo.Exception.ResourceNotFoundException;
import com.example.practo.Payload.LoginDto;
import com.example.practo.Payload.PatientDto;
import com.example.practo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ADD PATIENT
    @Override
    public Patient addPatient(PatientDto dto) {

        Patient patient = new Patient();

        patient.setPatientName(dto.getPatientName());
        patient.setAge(dto.getAge());
        patient.setDisease(dto.getDisease());
        patient.setMobile(dto.getMobile());

        patient.setEmail(dto.getEmail());

        patient.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        patient.setRole("PATIENT");

        return patientRepository.save(patient);
    }

    // DELETE PATIENT
    @Override
    public void deleteByPatient(Long id) {

        patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient Not Found"));

        patientRepository.deleteById(id);
    }

    // GET ALL PATIENTS
    @Override
    public List<PatientDto> getAllPatients(
            int pageNo,
            int pageSize,
            String sortBy,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(
                Sort.Direction.ASC.name())

                ? Sort.by(Sort.Direction.ASC, sortBy)

                : Sort.by(Sort.Direction.DESC, sortBy);

        Pageable pageable =
                PageRequest.of(pageNo, pageSize, sort);

        Page<Patient> all =
                patientRepository.findAll(pageable);

        List<Patient> patients = all.getContent();

        List<PatientDto> patientDtos =
                patients.stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());

        return patientDtos;
    }

    // GET PATIENT BY ID
    @Override
    public Patient getPatientById(Long id) {

        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient Not Found By Id"));
    }

    // UPDATE PATIENT
    @Override
    public Patient updatePatient(
            Long id,
            PatientDto dto) {

        Patient patient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Patient Not Found"));

        patient.setPatientName(dto.getPatientName());
        patient.setAge(dto.getAge());
        patient.setDisease(dto.getDisease());
        patient.setMobile(dto.getMobile());

        patient.setEmail(dto.getEmail());

        patient.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        patient.setRole("PATIENT");

        return patientRepository.save(patient);
    }

    // SIGNUP
    @Override
    public String signUp(PatientDto dto) {

        Patient patient = new Patient();

        patient.setPatientName(dto.getPatientName());
        patient.setAge(dto.getAge());
        patient.setMobile(dto.getMobile());
        patient.setDisease(dto.getDisease());

        patient.setEmail(dto.getEmail());

        patient.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        patient.setRole("PATIENT");

        patientRepository.save(patient);

        return "Patient Registered Successfully";
    }

    // LOGIN
    @Override
    public String login(LoginDto dto) {

        Patient patient =
                patientRepository.findByEmail(dto.getEmail())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Email Id Not Found"));

        boolean matches =
                passwordEncoder.matches(
                        dto.getPassword(),
                        patient.getPassword()
                );

        if (matches) {

            return "Login Successfully";

        } else {

            return "Invalid Password";
        }
    }

    // ENTITY TO DTO
    private PatientDto mapToDto(Patient patient) {

        PatientDto dto = new PatientDto();

        dto.setPatientName(patient.getPatientName());
        dto.setAge(patient.getAge());
        dto.setDisease(patient.getDisease());
        dto.setMobile(patient.getMobile());

        dto.setEmail(patient.getEmail());

        // encrypted password
        dto.setPassword(patient.getPassword());

        return dto;
    }
}