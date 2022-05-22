import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { take } from 'rxjs';
import { Game } from 'src/app/shared/types';
import { GameDetailsService } from './service';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {

  gameId: string;
  game?: Game;
  constructor(private readonly route: ActivatedRoute, private readonly service: GameDetailsService) {
    this.gameId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.service.getGameById(this.gameId).pipe(take(1)).subscribe(data => {
      console.log(data);
      this.game = data;
    });
  }

}
