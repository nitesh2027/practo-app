package com.example.practo.Service;

import com.example.practo.Entity.Doctor;
import com.example.practo.Entity.Patient;
import com.example.practo.Entity.Review;
import com.example.practo.Exception.ResourceNotFoundException;
import com.example.practo.Payload.ReviewDto;
import com.example.practo.Repository.DoctorRepository;
import com.example.practo.Repository.PatientRepository;
import com.example.practo.Repository.ReviewRepository;
import com.example.practo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public String addReview(ReviewDto dto) {

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor Not Found"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient Not Found"));

        Review review = new Review();

        review.setReviewMessage(dto.getReviewMessage());

        review.setRating(dto.getRating());

        review.setDoctor(doctor);

        review.setPatient(patient);

        reviewRepository.save(review);

        return "Review Added Successfully";


    }

    @Override
    public List<ReviewDto> getReviewsByDoctor(Long doctorId) {

        List<Review> reviews =
                reviewRepository.findByDoctorDoctorId(doctorId);

        return reviews.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ReviewDto mapToDto(Review review){

        ReviewDto dto = new ReviewDto();

        dto.setReviewMessage(review.getReviewMessage());

        dto.setRating(review.getRating());

        dto.setDoctorId(
                review.getDoctor().getDoctorId());

        dto.setPatientId(
                review.getPatient().getPatientId());

        return dto;
    }
}