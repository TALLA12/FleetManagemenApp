import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      motDePasse: ['', Validators.required]
    });
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      const loginPayload = {
        username: this.loginForm.get('username')?.value,
        motDePasse: this.loginForm.get('motDePasse')?.value
      };

      this.authService.login(loginPayload).subscribe(
        response => {
          console.log('User logged in successfully', response);
          this.router.navigate(['/profils']);
        },
        error => {
          console.error('Error during login:', error);
          if (error.error === 'User account is not active') {
            this.errorMessage = 'Votre compte est en attente d\'activation par un administrateur.';
          } else {
            this.errorMessage = 'Échec de la connexion. Veuillez vérifier vos informations d\'identification et réessayer.';
          }
        }
      );
    }
  }
}
