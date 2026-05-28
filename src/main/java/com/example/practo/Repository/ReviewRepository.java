package com.example.practo.Repository;


import com.example.practo.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDoctorDoctorId(Long doctorId);
}
