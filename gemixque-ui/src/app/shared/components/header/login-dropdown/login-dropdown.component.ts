import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { passwordPattern, usernamePattern } from 'src/app/shared/validators';

@Component({
  selector: 'app-login-dropdown',
  templateUrl: './login-dropdown.component.html',
  styleUrls: ['./login-dropdown.component.css']
})
export class LoginDropdownComponent {
  @ViewChild('dropdownTrigger') ref!: ElementRef;
  formGroup: FormGroup;

  constructor(readonly authService: AuthService) {
    this.formGroup = new FormGroup({
      username: new FormControl('libbie.dickinson', [Validators.required, Validators.pattern(usernamePattern)]),
      password: new FormControl('8F93C729', [Validators.required, Validators.pattern(passwordPattern)]),
      rememberMe: new FormControl(null)
    });
  }

  onLogin(): void {
    this.ref.nativeElement.click();
    this.authService.onLogin();
    // console.log(this.formGroup.getRawValue());
  }
}
