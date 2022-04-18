package com.damian.gemixqueapi.service;

import com.damian.gemixqueapi.projection.game.GameInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GamesPlayedInterfaceProjection;
import com.damian.gemixqueapi.repository.GameRepository;
import com.damian.gemixqueapi.repository.GamesPlayedByUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GamesPlayedByUserRepository gamesPlayedByUserRepository;

    @Autowired
    private GameRepository gameRepository;

    public List<GameInterfaceProjection> getAllGames(){
        return gameRepository.findAllProjectedBy();
    }

    public Optional<GameInterfaceProjection> getGameById(String id){
        return gameRepository.findByUuid(id);
    }

    public Optional<GamesPlayedInterfaceProjection> getGamesFromUserId(String userId){
        return gamesPlayedByUserRepository.findByUuid(userId);
    }
}
