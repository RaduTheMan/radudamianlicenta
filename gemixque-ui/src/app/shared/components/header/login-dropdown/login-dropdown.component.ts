import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, of } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { passwordPattern, usernamePattern } from 'src/app/shared/validators';

@Component({
  selector: 'app-login-dropdown',
  templateUrl: './login-dropdown.component.html',
  styleUrls: ['./login-dropdown.component.css'],
})
export class LoginDropdownComponent {
  @ViewChild('dropdownTrigger') ref!: ElementRef;
  formGroup: FormGroup;

  constructor(readonly authService: AuthService, private readonly matSnackBarService: MatSnackBar) {
    this.formGroup = new FormGroup({
      username: new FormControl(null, [
        Validators.required,
        Validators.pattern(usernamePattern),
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.pattern(passwordPattern),
      ]),
    });
  }

  onLogin(): void {
    this.ref.nativeElement.click();
    const data = this.formGroup.getRawValue();
    this.authService.tryingToLogin = true;
    this.authService.onLogin(data).pipe(
        catchError((error: HttpErrorResponse) => {
          const errorMessage = (error.error as { errorMessage: string }).errorMessage;
          this.matSnackBarService.open(errorMessage, 'close', { 
            verticalPosition: 'top',
            horizontalPosition: 'left',
            panelClass: ['red-snackbar'],
            duration: 2000
          });
          return of(undefined);
        })
      )
      .subscribe((response) => {
        this.authService.tryingToLogin = false;
        if (response) {
          const token = response.accessToken;
          const uuid = response.uuid;
          this.authService.tokenWrapper = { token, uuid };
          this.authService.isLoggedIn = true;
          localStorage.setItem(
            'userData',
            JSON.stringify(this.authService.tokenWrapper)
          );
        }
      });
  }
}
