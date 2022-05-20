import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { passwordPattern, usernamePattern } from 'src/app/shared/validators';

@Component({
  selector: 'app-login-dropdown',
  templateUrl: './login-dropdown.component.html',
  styleUrls: ['./login-dropdown.component.css']
})
export class LoginDropdownComponent {
  formGroup: FormGroup;

  constructor() {
    this.formGroup = new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.pattern(usernamePattern)]),
      password: new FormControl(null, [Validators.required, Validators.pattern(passwordPattern)]),
      rememberMe: new FormControl(null)
    });
  }

  onLogin(): void {
    console.log(this.formGroup.getRawValue());
  }
}
