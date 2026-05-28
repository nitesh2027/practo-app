package com.example.practo.Service;

import com.example.practo.Entity.Doctor;
import com.example.practo.Entity.Patient;
import com.example.practo.Payload.DoctorDto;
import org.springframework.data.annotation.Id;

import java.util.List;

public interface DoctorService {

    public Doctor addDoctor(DoctorDto dto);
    List<DoctorDto>searchDoctorByName(String doctorName);
    List<DoctorDto>searchDoctorBySpecialization(String specialization);

     void deleteDoctorById(Long id);
     List<DoctorDto>getAllDoctors(int pageNo, int pageSize, String sortBy, String sortDir);

    public Doctor getDoctorById(Long id);

    public Doctor updateDoctor(Long id, DoctorDto dto);
}
