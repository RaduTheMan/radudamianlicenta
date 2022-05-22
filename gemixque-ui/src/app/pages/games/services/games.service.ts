import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ConfigurationService } from 'src/app/configuration/configuration.service';
import { Game, Paginated } from 'src/app/shared/types';

@Injectable({
  providedIn: 'any'
})
export class GamesService {

constructor(private readonly configuration: ConfigurationService, private readonly httpClient: HttpClient) { }

getGamesPaginated(page: number, pageSize: number): Observable<Paginated<Game>> {
  const url = this.configuration.getEndpoint('gamesPaginated')!;
  const params = new HttpParams()
  .set('page', page)
  .set('pageSize', pageSize);
  return this.httpClient.get<Paginated<Game>>(url, { params } );
}

}
