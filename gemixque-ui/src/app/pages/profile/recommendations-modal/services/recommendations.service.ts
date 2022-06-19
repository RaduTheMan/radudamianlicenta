import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConfigurationService } from 'src/app/configuration/configuration.service';
import { Recommendation } from '../types';

@Injectable({
  providedIn: 'any'
})
export class RecommendationsService {

  constructor(private readonly httpClient: HttpClient, private readonly configuration: ConfigurationService) { }

  getRecommendations(uuid: string, k: number): Observable<Recommendation[]> {
    const url = this.configuration.getEndpoint('recommendedGamesOfUser', uuid);
    const params = new HttpParams().set('k', k);
    return this.httpClient.get<Recommendation[]>(url!, { params });
  }

}
