package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.entity.game.GameEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.GameInterfaceProjection;
import com.damian.gemixqueapi.projection.GamesPlayedInterfaceProjection;
import com.damian.gemixqueapi.repository.GameRepository;
import com.damian.gemixqueapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/games")
    public List<GameInterfaceProjection> getAllGames(){
        return gameService.getAllGames();
    }

    @RequestMapping("/games/{id}")
    public GameInterfaceProjection getGame(@PathVariable String id){
        return gameService.getGameById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping("/users/{userId}/games")
    public GamesPlayedInterfaceProjection getGamesFromUser(@PathVariable String userId){
        return gameService.getGamesFromUserId(userId).orElseThrow(ResourceNotFoundException::new);
    }
}
