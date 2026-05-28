package com.example.practo.Controller;

import com.example.practo.Entity.Doctor;
import com.example.practo.Payload.DoctorDto;
import com.example.practo.Service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")

public class DoctorController {

    @Autowired
    private DoctorService doctorService;
  //https://localhost:8080/api/doctors/add
  @PostMapping("/add")
  public ResponseEntity<?> addDoctor(
          @Valid @RequestBody DoctorDto dto,
          BindingResult result) {
      if(result.hasErrors()){
          return new ResponseEntity<>(
                  result.getFieldError().getDefaultMessage(),
                  HttpStatus.BAD_REQUEST);
      }

      Doctor savedDoctor = doctorService.addDoctor(dto);

      return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
  }
    //GET http://localhost:8080/api/doctors/doctorName?doctorName=Rohit Das
  @GetMapping("/doctorName")
  public ResponseEntity<List<DoctorDto>>searchDoctorByName(@RequestParam String doctorName){
      List<DoctorDto> doctorDtos = doctorService.searchDoctorByName(doctorName);
      return new ResponseEntity<>(doctorDtos, HttpStatus.OK);
  }
    //GET http://localhost:8080/api/doctors/specialization?specialization=Gynecologist
  @GetMapping("/specialization")
  public ResponseEntity<List<DoctorDto>>searchDoctorBySpecialization(@RequestParam String specialization){
      List<DoctorDto> doctorDtos = doctorService.searchDoctorBySpecialization(specialization);
      return new ResponseEntity<>(doctorDtos, HttpStatus.OK);
  }
    //https://localhost:8080/api/doctors?id=5
    @DeleteMapping
    public ResponseEntity<String>deleteDoctorById(@RequestParam Long id){
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    //GET http://localhost:8080/api/doctors/all
    //http://localhost:8080/api/doctors/all?pageNo=0&pageSize=5&sortBy=doctorId&sortDir=asc
    //http://localhost:8080/api/doctors/all?pageNo=0&pageSize=5&sortBy=doctorName&sortDir=asc
    @GetMapping("/all")
    public ResponseEntity<List<DoctorDto>>getAllDoctor(
            @RequestParam(name="pageNo", defaultValue = "0",required = false) int pageNo,
            @RequestParam(name="pageSize", defaultValue = "5",required = false) int pageSize,
            @RequestParam(name="sortBy", defaultValue = "doctorName",required = false) String sortBy,
            @RequestParam(name="sortDir", defaultValue = "asc", required = false) String sortDir){
        List<DoctorDto> dtos = doctorService.getAllDoctors(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    //http://localhost:8080/api/doctors?id=2
    @GetMapping
    public ResponseEntity<Doctor>getDoctorById(@RequestParam Long id){
        Doctor doctors = doctorService.getDoctorById(id);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    //PUT http://localhost:8080/api/doctors?id=1
    @PutMapping
    public ResponseEntity<Doctor>updateDoctor(@RequestParam Long id, @RequestBody DoctorDto dto){
        Doctor updateDoctor = doctorService.updateDoctor(id, dto);
        return new ResponseEntity<>(updateDoctor, HttpStatus.OK);
    }
}