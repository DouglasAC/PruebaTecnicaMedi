import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      this.errorMessage = "Por favor, complete todos los campos.";
      return;
    }

    this.errorMessage = '';
    const username = this.loginForm.get('username')?.value;
    const password = this.loginForm.get('password')?.value;

    this.authService.loging(username, password).subscribe({
      next: () => {
        this.router.navigate(['/empleados']);
      },
      error: (error) => {
        console.error('Error de inicio de sesión:', error);
        this.errorMessage = 'Credenciales incorrectas. Por favor, inténtelo de nuevo.';
      }
    });
  }

}
