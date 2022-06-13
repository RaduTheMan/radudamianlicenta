import { Component } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthService } from './services/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'gemixque-ui';
  constructor(readonly authService: AuthService) {
    const data = localStorage.getItem('userData');
    if (data) {
      const helper = new JwtHelperService();
      const tokenWrapper: { token: string, uuid: string } = JSON.parse(data);
      if (helper.isTokenExpired(tokenWrapper.token)) {
        this.authService.tokenWrapper = undefined;
        this.authService.isLoggedIn = false;
      } else {
        this.authService.tokenWrapper = tokenWrapper;
        this.authService.isLoggedIn = true;
      }
    }
  }
}
