import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { take } from 'rxjs';
import { Game, Review } from 'src/app/shared/types';
import { GameDetailsService } from './service';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {

  gameId: string;
  isLoading = false;
  game?: Game;
  page = 1;
  pageSize = 10;
  reviews?: Review[];
  screenshots?: string[];
  constructor(private readonly route: ActivatedRoute, private readonly service: GameDetailsService) {
    this.gameId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.isLoading = true;
    this.service.getGameById(this.gameId).pipe(take(1)).subscribe(data => {
      this.isLoading = false;
      this.game = data;
      this.screenshots = this.game.visuals.screenshots;
      console.log(data);
    });
    this.service.getReviewsMadeOnGame(this.gameId).pipe(take(1)).subscribe(data => {
      this.reviews = data;
    });
  }

}
