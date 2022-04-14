package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.entity.ReviewEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @RequestMapping("/reviews")
    public List<ReviewEntity> getAllReviews(){
        return reviewRepository.findAll();
    }

    @RequestMapping("/reviews/{id}")
    public ReviewEntity getReview(@PathVariable String id){
        return reviewRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
