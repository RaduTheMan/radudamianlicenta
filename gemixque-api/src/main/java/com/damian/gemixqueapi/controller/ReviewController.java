package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.review.FullReviewInterfaceProjection;
import com.damian.gemixqueapi.projection.review.GetUsersLikedInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GetReviewsMadeFromUserInterfaceProjection;
import com.damian.gemixqueapi.projection.game.GetReviewsMadeOnGameInterfaceProjection;
import com.damian.gemixqueapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public List<FullReviewInterfaceProjection> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("/reviews/{id}")
    public FullReviewInterfaceProjection getReview(@PathVariable String id){
        return reviewService.getReviewById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/users/{userId}/reviews")
    public GetReviewsMadeFromUserInterfaceProjection getReviewsFromUser(@PathVariable String userId){
        return reviewService.getReviewsMadeByUser(userId).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/games/{gameId}/reviews")
    public GetReviewsMadeOnGameInterfaceProjection getReviewsOnGame(@PathVariable String gameId){
        return reviewService.getReviewsMadeOnGame(gameId).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/reviews/{reviewId}/likes")
    public GetUsersLikedInterfaceProjection getUsersLikedOnReview(@PathVariable String reviewId){
        return reviewService.getUsersLikedOnReview(reviewId).orElseThrow(ResourceNotFoundException::new);
    }
}
