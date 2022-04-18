package com.damian.gemixqueapi.service;

import com.damian.gemixqueapi.projection.review.FullReviewInterfaceProjection;
import com.damian.gemixqueapi.projection.review.GetReviewsMadeFromUserInterfaceProjection;
import com.damian.gemixqueapi.projection.review.GetReviewsMadeOnGameInterfaceProjection;
import com.damian.gemixqueapi.repository.ReviewRepository;
import com.damian.gemixqueapi.repository.ReviewsMadeByUserRepository;
import com.damian.gemixqueapi.repository.ReviewsMadeOnGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewsMadeByUserRepository reviewsMadeByUserRepository;

    @Autowired
    private ReviewsMadeOnGameRepository reviewsMadeOnGameRepository;

    public List<FullReviewInterfaceProjection> getAllReviews() {
        return reviewRepository.findAllProjectedBy();
    }

    public Optional<FullReviewInterfaceProjection> getReviewById(String id){
        return reviewRepository.findByUuid(id);
    }

    public Optional<GetReviewsMadeFromUserInterfaceProjection> getReviewsMadeByUser(String userId){
        return reviewsMadeByUserRepository.findByUuid(userId);
    }

    public Optional<GetReviewsMadeOnGameInterfaceProjection> getReviewsMadeOnGame(String gameId){
        return reviewsMadeOnGameRepository.findByUuid(gameId);
    }
}
