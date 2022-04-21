package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.game.GameInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GamesPlayedInterfaceProjection;
import com.damian.gemixqueapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public List<GameInterfaceProjection> getAllGames(){
        return gameService.getAllGames();
    }

    @GetMapping("/games/{id}")
    public GameInterfaceProjection getGame(@PathVariable String id){
        return gameService.getGameById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/users/{userId}/games")
    public GamesPlayedInterfaceProjection getGamesFromUser(@PathVariable String userId){
        return gameService.getGamesFromUserId(userId).orElseThrow(ResourceNotFoundException::new);
    }
}
