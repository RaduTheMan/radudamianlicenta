import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(readonly authService: AuthService, private readonly router: Router) { }

  onLogout(): void {
    this.authService.onLogout();
    this.router.navigate(['']);
  }
}
