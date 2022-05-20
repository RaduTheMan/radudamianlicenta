import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-game-summary',
  templateUrl: './game-summary.component.html',
  styleUrls: ['./game-summary.component.css']
})
export class GameSummaryComponent {

  @Input() imageUrl!: string;
  @Input() summary!: string;
  @Input() title!: string;
  constructor() { }

}
