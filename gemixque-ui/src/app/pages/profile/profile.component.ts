import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { forkJoin, take } from 'rxjs';
import { Review } from 'src/app/shared/types';
import { UserService } from './services';
import { User } from './types';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  uuid: string;
  isLoading = false;
  pageGames = 1;
  pageReviews = 1;
  pageSize = 10;

  sources?: {
    user: User;
    gamesPlayedByUser: {uuid: string; title: string }[];
    reviewsMadeByUser: Review[]
  };
  constructor(private readonly service: UserService, private readonly activatedRoute: ActivatedRoute) {
    this.uuid = this.activatedRoute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.isLoading = true;
    forkJoin({
      user: this.service.getUser(this.uuid),
      gamesPlayedByUser: this.service.getGamesPlayedByUser(this.uuid),
      reviewsMadeByUser: this.service.getReviewsMadeByUser(this.uuid)
    }).pipe(take(1)).subscribe(data => {
      this.sources = data;
      this.isLoading = false;
    });
  }
}
