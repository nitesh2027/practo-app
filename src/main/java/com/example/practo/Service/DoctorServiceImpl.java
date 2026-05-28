package com.example.practo.Service;

import com.example.practo.Entity.Doctor;
import com.example.practo.Entity.Patient;
import com.example.practo.Exception.ResourceNotFoundException;
import com.example.practo.Payload.DoctorDto;
import com.example.practo.Repository.DoctorRepository;
import com.example.practo.Repository.PatientRepository;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Doctor addDoctor(DoctorDto dto) {
        Doctor doctor=new Doctor();
        doctor.setFees(dto.getFees());
        doctor.setDoctorName(dto.getDoctorName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setHospitalName(dto.getHospitalName());
         return doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorDto> searchDoctorByName(String doctorName) {
        List<Doctor> doctors = doctorRepository.searchDoctorByName(doctorName);
        return doctors.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        
    }

    @Override
    public List<DoctorDto> searchDoctorBySpecialization(String specialization) {
        List<Doctor> doctors = doctorRepository.searchDoctorBySpecialization(specialization);
        return doctors.stream().map(r->mapToDto(r)).collect(Collectors.toList());
    }

    @Override
    public void deleteDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        doctorRepository.deleteById(id);

    }

    @Override
    public List<DoctorDto> getAllDoctors(int pageNo, int pageSize, String sortBy, String sortDir) {
       Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(Sort.Direction.ASC,sortBy):Sort.by(Sort.Direction.DESC,sortBy);
        Pageable pageable=PageRequest.of(pageNo,pageSize,sort);
        Page<Doctor> all=doctorRepository.findAll(pageable);
       List<Doctor>doctors= all.getContent();
       List<DoctorDto>doctorDtos=doctors.stream().map(r->mapToDto(r)).collect(Collectors.toList());
       return doctorDtos;
    }

    @Override
    public Doctor getDoctorById(Long id) {
        Doctor doctors = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found By Id"));
      return doctors;
    }

    @Override
    public Doctor updateDoctor(Long id, DoctorDto dto) {

        Doctor doctor = doctorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Doctor Not Found"));

          doctor.setDoctorName(dto.getDoctorName());
          doctor.setFees(dto.getFees());
          doctor.setSpecialization(dto.getSpecialization());
          doctor.setHospitalName(dto.getHospitalName());
          return doctorRepository.save(doctor);

    }
    private DoctorDto mapToDto(Doctor doctor){
        DoctorDto dto=new DoctorDto();
        dto.setDoctorName(doctor.getDoctorName());
        dto.setFees(doctor.getFees());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setHospitalName(doctor.getHospitalName());
        return dto;
    }


}