import { Component } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { UntilDestroy } from '@ngneat/until-destroy';
import { handleControl } from 'src/app/shared/helpers/control';
import { passwordPattern, usernamePattern } from 'src/app/shared/validators';

@UntilDestroy()
@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  formGroup: FormGroup;
  passwordInfo = 'The password should have minimum 8 characters, at least one letter and one number';
  usernameInfo = 'The username should have between 8-20 characters, no _ or . at the beginning/ending, and no __ _. ._ or .. inside';
  reEnterPasswordInfo = 'Re-enter your password';
  emailInfo = 'Your email address';

  constructor() {
    this.formGroup = new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.pattern(usernamePattern)]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required, Validators.pattern(passwordPattern)]),
      repeatedPassword: new FormControl(null, [Validators.required, Validators.pattern(passwordPattern), this.mustMatchPassword.bind(this)])
    });
   }

  get password(): AbstractControl {
    const control = this.formGroup?.get('password');
    return handleControl(control);
  }

  get repeatedPassword(): AbstractControl {
    const control = this.formGroup?.get('repeatedPassword');
    return handleControl(control);
  }

  get controls() {
    return this.formGroup.controls;
  }

  signUp(): void {
    console.log(this.formGroup.getRawValue());
  }

  mustMatchPassword(control: FormControl): {[s: string]: boolean} | null {
    if (this.password?.value !== control.value) {
      return { 'notMatch': true };
    }
    return null;
  }

  getErrorObjFromControl(control: AbstractControl): {
    notMatch?: boolean
  } {
    const errors = control.errors;
    if (errors !== null){
      return errors;
    }
    return {};
  }

}
