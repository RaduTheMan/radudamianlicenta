import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ConfigurationService } from 'src/app/configuration/configuration.service';
import { Game, Review } from 'src/app/shared/types';
import { User } from '../types';

@Injectable({
  providedIn: 'any'
})
export class UserService {

  constructor(private readonly httpClient: HttpClient, private readonly configuration: ConfigurationService) { }

  getUser(uuid: string): Observable<User> {
    const url = this.configuration.getEndpoint('userById', uuid);
    return this.httpClient.get<User>(url!);
  }

  getGamesPlayedByUser(uuid: string): Observable<{ uuid: string; title: string; }[]> {
    const url = this.configuration.getEndpoint('gamesPlayedByUser', uuid);
    return this.httpClient.get<{gamesPlayed: Game[]}>(url!).pipe(map(gamesWrapper => gamesWrapper.gamesPlayed), map(games => {
      return games.map(game => {
        return {
          uuid: game.uuid,
          title: game.title
        };
      });
    }));
  }

  getReviewsMadeByUser(uuid: string): Observable<Review[]> {
    const url = this.configuration.getEndpoint('reviewsMadeByUser', uuid);
    return this.httpClient.get<{ reviewsMade: Review[] }>(url!).pipe(map(reviewsWrapper => reviewsWrapper.reviewsMade));
  }

}
