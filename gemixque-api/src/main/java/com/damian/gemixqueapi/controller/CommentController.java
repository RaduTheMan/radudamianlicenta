package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.entity.CommentEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping("/comments")
    public List<CommentEntity> getAllComments(){
        return commentRepository.findAll();
    }

    @RequestMapping("/comments/{id}")
    public CommentEntity getComment(@PathVariable Long id){
        return commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
