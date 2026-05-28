package com.example.practo.Payload;



import lombok.Data;

@Data
public class ReviewDto {

    private Long patientId;
    private Long doctorId;
    private Integer rating;
    private String reviewMessage;






}