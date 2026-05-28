package com.example.practo.Controller;

import com.example.practo.Entity.Patient;
import com.example.practo.Payload.LoginDto;
import com.example.practo.Payload.PatientDto;
import com.example.practo.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")

public class PatientController {

    @Autowired
    private PatientService patientService;
    //http://localhost:8080/api/patients
    @PostMapping
    public ResponseEntity<?> addPatient(@Valid  @RequestBody PatientDto dto, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Patient savedPatient = patientService.addPatient(dto);

        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(
            @Valid @RequestBody PatientDto dto){

        String message = patientService.signUp(dto);

        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginDto dto){

        String message = patientService.login(dto);

        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    //http://localhost:8080/api/patients?id=4
    @DeleteMapping
    public ResponseEntity<String>deletePatientById(@RequestParam Long id){
        patientService.deleteByPatient(id);
        return new ResponseEntity<>("Patient Id Deleted", HttpStatus.OK);
    }
    //http://localhost:8080/api/patients/all
    //http://localhost:8080/api/patients/all?pageNo=0&pageSize=5&sortBy=patientId&sortDir=asc
    @GetMapping("/all")
    public ResponseEntity<List<PatientDto>>getAll( @RequestParam(name="pageNo", defaultValue = "0", required = false) int pageNo,
                                                   @RequestParam(name="pageSize", defaultValue = "5", required = false) int pageSize,
                                                   @RequestParam(name="sortBy", defaultValue = "patientName", required = false) String sortBy,
                                                   @RequestParam(name="sortDir", defaultValue = "asc", required = false) String sortDir)
                                               {
        List<PatientDto> dtos=patientService.getAllPatients(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Patient>getPatientById(@RequestParam Long id){
        Patient patient=patientService.getPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
     //PUT http://localhost:8080/api/patients?id=1
    @PutMapping
    public ResponseEntity<Patient>updatePatient(@RequestParam Long id, @RequestBody PatientDto dto){
        Patient updatePatient = patientService.updatePatient(id, dto);
        return new ResponseEntity<>(updatePatient, HttpStatus.OK);
    }
}