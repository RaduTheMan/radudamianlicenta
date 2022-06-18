import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ConfigurationService } from 'src/app/configuration/configuration.service';
import { Game, Review } from 'src/app/shared/types';

@Injectable({
  providedIn: 'any'
})
export class GameDetailsService {

  constructor(private readonly httpClient: HttpClient, private readonly configuration: ConfigurationService) { }

  getGameById(id: string): Observable<Game> {
    const url = this.configuration.getEndpoint('gameById', id)!;
    return this.httpClient.get<Game>(url);
  }

  getReviewsMadeOnGame(id: string): Observable<Review[]> {
    const url = this.configuration.getEndpoint('reviewsMadeOnGame', id);
    return this.httpClient.get<{ reviewsMade: Review[] }>(url!).pipe(map(reviewsWrapper => reviewsWrapper.reviewsMade)); 
  }

}
