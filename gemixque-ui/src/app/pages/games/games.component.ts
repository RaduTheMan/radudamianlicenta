import { Component, OnInit } from '@angular/core';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { BehaviorSubject, switchMap } from 'rxjs';
import { Game } from 'src/app/shared/types';
import { GamesService } from './services';

@UntilDestroy()
@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {

  currentPage = 1;
  maxPage = 1;
  currentPageEmiter$ = new BehaviorSubject<number>(this.currentPage);
  pageSize = 8;
  games!: Game[];
  isLoading = true;

  constructor(
    private readonly service: GamesService) {}

  ngOnInit(): void {
    this.currentPageEmiter$.asObservable().pipe(switchMap(page => {
      return this.service.getGamesPaginated(page, this.pageSize);
    })).pipe(untilDestroyed(this)).subscribe(data => {
      this.games = data.content;
      this.maxPage = data.totalPages;
      this.isLoading = false;
    });
  }

  previousPage(): void {
    if (this.currentPage > 1){
      this.currentPage -= 1;
      this.currentPageEmiter$.next(this.currentPage);
      this.isLoading = true;
    }
  }

  nextPage(): void {
    if (this.currentPage < this.maxPage){
      this.currentPage += 1;
      this.currentPageEmiter$.next(this.currentPage);
      this.isLoading = true;
    }
  }
}
