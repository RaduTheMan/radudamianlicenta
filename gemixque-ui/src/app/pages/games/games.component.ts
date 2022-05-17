import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { twenty } from 'src/app/shared/constants';
import { GamesService } from './services';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {

  currentPage: number = 1;
  pageSize: number = twenty;

  constructor(private readonly service: GamesService) { }

  ngOnInit(): void {
    this.service.getGamesPaginated(this.currentPage, this.pageSize)?.pipe(take(1))
    .subscribe(data => {
      console.log(data);
    });
  }
}
