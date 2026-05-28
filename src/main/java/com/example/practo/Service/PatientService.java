package com.example.practo.Service;

import com.example.practo.Entity.Patient;
import com.example.practo.Payload.LoginDto;
import com.example.practo.Payload.PatientDto;

import java.util.List;

public interface PatientService {

    public Patient addPatient(PatientDto dto);

    void deleteByPatient(Long id);
    public List<PatientDto> getAllPatients(int pageNo, int pageSize, String sortBy, String sortDir);

    public Patient getPatientById(Long id);

    public Patient updatePatient(Long id, PatientDto dto);
    String signUp(PatientDto dto);
    String login(LoginDto dto);
}
