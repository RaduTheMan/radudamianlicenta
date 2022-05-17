import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConfigurationService } from 'src/app/configuration/configuration.service';

@Injectable({
  providedIn: 'any'
})
export class GamesService {

constructor(private readonly configuration: ConfigurationService, private readonly httpClient: HttpClient) { }

getGamesPaginated(page: number, pageSize: number): Observable<unknown> | undefined {
  const url = this.configuration.getEndpoint('gamesPaginated');
  const params = new HttpParams()
  .set('page', page)
  .set('pageSize', pageSize);
  if (url !== undefined){
    return this.httpClient.get(url, { params } );
  }
  return undefined;
}

}
