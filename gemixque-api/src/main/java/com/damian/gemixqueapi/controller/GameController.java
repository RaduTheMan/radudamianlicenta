package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.entity.UserEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.game.GameInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GamesPlayedInterfaceProjection;
import com.damian.gemixqueapi.service.GameService;
import com.damian.gemixqueapi.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/games")
    public List<GameInterfaceProjection> getAllGames(){
        return gameService.getAllGames();
    }

    @GetMapping("/games/paginated")
    public  Page<GameInterfaceProjection> getGamesPaginated(@RequestParam int page, @RequestParam int pageSize) {
        return gameService.getGamesPaginated(page, pageSize);
    }

    @GetMapping("/games/{id}")
    public GameInterfaceProjection getGame(@PathVariable String id){
        return gameService.getGameById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/users/{userId}/games")
    public GamesPlayedInterfaceProjection getGamesFromUser(@PathVariable String userId){
        return gameService.getGamesFromUserId(userId).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/users/{userId}/recommended-games")
    public void getRecommendedGamesForUser(@PathVariable String userId){
        var similarUsers = recommendationService.getSimilarUsers(userId);
        for(var similarUser: similarUsers) {
            var weight = recommendationService.getWeight(userId, similarUser.getUuid());
            var uncommonReviews = recommendationService.getUncommonReviewsForOtherUser(userId, similarUser.getUuid());
            System.out.println(Arrays.toString(uncommonReviews.toArray()));
            break;
        }
    }

}
