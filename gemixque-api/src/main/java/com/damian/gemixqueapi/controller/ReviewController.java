package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.entity.ReviewEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.review.FullReviewInterfaceProjection;
import com.damian.gemixqueapi.projection.review.GetReviewsMadeFromUserInterfaceProjection;
import com.damian.gemixqueapi.projection.review.GetReviewsMadeOnGameInterfaceProjection;
import com.damian.gemixqueapi.repository.ReviewRepository;
import com.damian.gemixqueapi.repository.ReviewsMadeByUserRepository;
import com.damian.gemixqueapi.repository.ReviewsMadeOnGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewsMadeByUserRepository reviewsMadeByUserRepository;

    @Autowired
    private ReviewsMadeOnGameRepository reviewsMadeOnGameRepository;

    @RequestMapping("/reviews")
    public List<FullReviewInterfaceProjection> getAllReviews(){
        return reviewRepository.findAllProjectedBy();
    }

    @RequestMapping("/reviews/{id}")
    public FullReviewInterfaceProjection getReview(@PathVariable String id){
        return reviewRepository.findByUuid(id).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping("/users/{userId}/reviews")
    public GetReviewsMadeFromUserInterfaceProjection getReviewsFromUser(@PathVariable String userId){
        return reviewsMadeByUserRepository.findByUuid(userId).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping("/games/{gameId}/reviews")
    public GetReviewsMadeOnGameInterfaceProjection getReviewsOnGame(@PathVariable String gameId){
        return reviewsMadeOnGameRepository.findByUuid(gameId).orElseThrow(ResourceNotFoundException::new);
    }
}
