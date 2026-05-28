package com.example.practo.Controller;

import com.example.practo.Entity.Review;
import com.example.practo.Payload.ReviewDto;
import com.example.practo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    //POST http://localhost:8080/api/reviews
    @PostMapping
    public ResponseEntity<String> addReview(
            @RequestBody ReviewDto dto){

      String message = reviewService.addReview(dto);

        return new ResponseEntity<>( message,HttpStatus.CREATED);
    }

    //GET http://localhost:8080/api/reviews?doctorId=1
    @GetMapping
    public ResponseEntity<List<ReviewDto>>
    getReviewsByDoctor(@RequestParam Long doctorId){

        List<ReviewDto> dtos =
                reviewService.getReviewsByDoctor(doctorId);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}