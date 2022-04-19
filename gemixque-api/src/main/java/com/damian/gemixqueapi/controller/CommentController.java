package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.comment.GetCommentsMadeOnReviewInterfaceProjection;
import com.damian.gemixqueapi.repository.CommentsMadeOnReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentsMadeOnReviewRepository commentsMadeOnReviewRepository;

    @RequestMapping("/reviews/{reviewId}/comments")
    public GetCommentsMadeOnReviewInterfaceProjection getCommentsFromReview(@PathVariable String reviewId){
        return commentsMadeOnReviewRepository.findByUuid(reviewId).orElseThrow(ResourceNotFoundException::new);
    }
}
