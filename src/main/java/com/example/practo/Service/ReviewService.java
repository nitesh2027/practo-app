package com.example.practo.Service;

import com.example.practo.Payload.ReviewDto;

import java.util.List;

public interface ReviewService {

    String addReview(ReviewDto dto);

    List<ReviewDto> getReviewsByDoctor(Long doctorId);
}