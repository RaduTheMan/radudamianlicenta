import { Injectable } from '@angular/core';
import { User } from '../shared/types/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn = false;
  user?: User;

  constructor() {
    //hardcoded user for now
    this.user = {
      username: 'libbie.dickinson',
      uuid: '0099d5b7-7074-4553-b58b-4c4d53061086',
      email: 'libbie.dickinson@yahoo.com'
    };
   }

   onLogin(): void {
    this.isLoggedIn = true;
   }

   onLogout(): void {
    this.isLoggedIn = false;
   }

}
