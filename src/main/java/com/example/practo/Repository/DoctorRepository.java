package com.example.practo.Repository;

import com.example.practo.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.print.Doc;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    @Query("SELECT d FROM Doctor d WHERE d.doctorName LIKE %:doctorName%")
    List<Doctor> searchDoctorByName(
            @Param("doctorName") String doctorName
    );

    @Query("SELECT d FROM Doctor d WHERE d.specialization LIKE %:specialization%")
    List<Doctor> searchDoctorBySpecialization(
            @Param("specialization") String specialization
    );
}