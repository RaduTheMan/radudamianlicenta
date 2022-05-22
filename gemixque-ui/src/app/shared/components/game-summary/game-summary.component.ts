import { Component, Input } from '@angular/core';
import { Game } from '../../types';

@Component({
  selector: 'app-game-summary',
  templateUrl: './game-summary.component.html',
  styleUrls: ['./game-summary.component.css']
})
export class GameSummaryComponent {

  @Input() game!: Game;
  constructor() { }

}
