import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConfigurationService } from '../configuration/configuration.service';
import { User } from '../shared/types/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn = false;
  tryingToLogin = false;
  tokenWrapper?: {token: string, uuid: string };

  constructor(private readonly httpClient: HttpClient, private readonly configuration: ConfigurationService) {}

   onLogin(data: object): Observable<{ accessToken: string, uuid: string }> {
    const url = this.configuration.getEndpoint('login');
    return this.httpClient.post<{ accessToken: string, uuid: string }>(url!, data);
   }

   onLogout(): void {
    this.isLoggedIn = false;
    this.tokenWrapper = undefined;
    localStorage.removeItem('userData');
   }

}
