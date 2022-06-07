package com.damian.gemixqueapi.controller;

import com.damian.gemixqueapi.entity.game.GameEntity;
import com.damian.gemixqueapi.exception.ResourceNotFoundException;
import com.damian.gemixqueapi.projection.game.GameInterfaceProjection;
import com.damian.gemixqueapi.projection.user.GamesPlayedInterfaceProjection;
import com.damian.gemixqueapi.service.GameService;
import com.damian.gemixqueapi.service.RecommendationService;
import com.damian.gemixqueapi.service.UserService;
import com.damian.gemixqueapi.service.custom_classes.Fraction;
import com.damian.gemixqueapi.service.custom_classes.SolutionEntry;
import com.damian.gemixqueapi.service.custom_classes.UserWithWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/games")
    public List<GameInterfaceProjection> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/games/paginated")
    public Page<GameInterfaceProjection> getGamesPaginated(@RequestParam int page, @RequestParam int pageSize) {
        return gameService.getGamesPaginated(page, pageSize);
    }

    @GetMapping("/games/{id}")
    public GameInterfaceProjection getGame(@PathVariable String id) {
        return gameService.getGameById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/users/{userId}/games")
    public GamesPlayedInterfaceProjection getGamesFromUser(@PathVariable String userId) {
        return gameService.getGamesFromUserId(userId).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/users/{userId}/recommended-games")
    public List<SolutionEntry> getRecommendedGamesForUser(@PathVariable String userId, @RequestParam int k) {
        var currentUser = userService.getUserById(userId).orElseThrow(ResourceNotFoundException::new);
        var similarUsers = recommendationService.getSimilarUsers(userId);
        SortedMap<GameEntity, Fraction> gameRegistry = new TreeMap<>();

        var usersWithWeight = similarUsers.stream().map(similarUser -> {
            var weight = recommendationService.getWeight(userId, similarUser.getUuid());
            return new UserWithWeight(weight, similarUser.getUuid());
        }).filter(userWithWeight -> userWithWeight.getWeight() != 0.0).sorted().limit(k).collect(Collectors.toList());


        for (var userWithWeight : usersWithWeight) {
            var weight = userWithWeight.getWeight();
            var uncommonReviews = recommendationService.getUncommonReviewsForOtherUser(userId, userWithWeight.getId());
            //update scores
            for (var uncommonReview : uncommonReviews) {
                var gameEntity = uncommonReview.getOtherGame();
                var reviewEntity = uncommonReview.getRev();
                double score = Double.parseDouble(reviewEntity.getScore());
                double numerator = weight * (score - userWithWeight.getAverageScore());
                double denominator = Math.abs(weight);
                if (gameRegistry.containsKey(gameEntity)) {
                    Fraction oldFraction = gameRegistry.get(gameEntity);
                    double oldNumerator = oldFraction.getNumerator();
                    double oldDenominator = oldFraction.getDenominator();
                    oldFraction.setNumerator(oldNumerator + numerator);
                    oldFraction.setDenominator(oldDenominator + denominator);
                    gameRegistry.put(gameEntity, oldFraction);
                } else {
                    gameRegistry.put(gameEntity, new Fraction(currentUser.getAverageScore(), numerator, denominator));
                }
            }
        }
        return gameRegistry.entrySet().stream().map(entry -> {
            return new SolutionEntry(entry.getKey(), entry.getValue());
        }).sorted(new Comparator<SolutionEntry>() {
            @Override
            public int compare(SolutionEntry o1, SolutionEntry o2) {
                return (-1) * Double.compare(o1.getValue(), o2.getValue());
            }
        }).collect(Collectors.toList());
    }

}
