import { Component } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { UntilDestroy } from '@ngneat/until-destroy';
import { passwordPattern, usernamePattern } from 'src/app/shared/validators';

@UntilDestroy()
@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {

  formGroup: FormGroup;
  passwordInfo = "The password should have minimum 8 characters, at least one letter and one number";
  usernameInfo = "The username should have between 8-20 characters, no _ or . at the beginning/ending, and no __ _. ._ or .. inside";
  reEnterPasswordInfo = "Re-enter your password";
  emailInfo = "Your email address";

  get password(): AbstractControl | null {
    return this.formGroup?.get('password');
  }

  get controls() {
    return this.formGroup.controls;
  }

  constructor() {
    this.formGroup = new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.pattern(usernamePattern)]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required, Validators.pattern(passwordPattern)]),
      repeatedPassword: new FormControl(null, [Validators.required, Validators.pattern(passwordPattern), this.mustMatchPassword.bind(this)])
    });
   }

  signUp(): void {
    console.log(this.formGroup.getRawValue());
  }

  mustMatchPassword(control: FormControl): {[s: string]: boolean} | null {
    if(this.password?.value !== control.value) {
      return { 'notMatch': true };
    }
    return null;
  }

}
