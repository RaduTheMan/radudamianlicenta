package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.GameInterfaceProjection;
import com.damian.gemixqueapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("/games")
    public List<GameInterfaceProjection> getAllGames(){
        return gameRepository.findAllProjectedBy();
    }

    @RequestMapping("/games/{id}")
    public GameInterfaceProjection getGame(@PathVariable String id){
        return gameRepository.findByUuid(id).orElseThrow(ResourceNotFoundException::new);
    }
}
