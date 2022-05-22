import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConfigurationService } from 'src/app/configuration/configuration.service';
import { Game } from 'src/app/shared/types';

@Injectable({
  providedIn: 'any'
})
export class GameDetailsService {

  constructor(private readonly httpClient: HttpClient, private readonly configuration: ConfigurationService) { }

  getGameById(id: string): Observable<Game> {
    const url = this.configuration.getEndpoint('gameById', id)!;
    return this.httpClient.get<Game>(url);
  }

}
