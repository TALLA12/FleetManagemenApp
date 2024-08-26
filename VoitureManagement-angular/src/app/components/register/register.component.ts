import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      nom: ['', [Validators.required, Validators.maxLength(50)]],
      prenom: ['', [Validators.required, Validators.maxLength(50)]],
      username: ['', [Validators.required, Validators.maxLength(50)]],
      motDePasse: ['', [Validators.required, Validators.minLength(6)]],
      cpassword: ['', [Validators.required]],
      role: ['', [Validators.required]]
    });
  }

  onRegister() {
    this.authService.register(this.registerForm.value).subscribe({
      next: (response) => {
        console.log('Registration successful', response);
      },
      error: (error) => {
        console.error('Registration error', error);
        let errorMessage = 'Une erreur est survenue';
        if (error.error instanceof ErrorEvent) {
          // Erreur côté client
          errorMessage = `Erreur côté client : ${error.error.message}`;
        } else {
          // Erreur côté serveur
          errorMessage = `Erreur côté serveur : ${error.message}`;
        }
        alert(errorMessage);
      }
    });
  }
  
}
