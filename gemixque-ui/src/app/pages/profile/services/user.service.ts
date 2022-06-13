import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConfigurationService } from 'src/app/configuration/configuration.service';
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

}
