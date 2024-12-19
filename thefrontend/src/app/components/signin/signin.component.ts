import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-signin',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css',
})
export class SigninComponent implements OnInit {
  signinForm = new FormGroup({
    email: new FormControl('', Validators.required), //, Validators.email
    password: new FormControl('', Validators.required),
  });
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['user']);
    }
  }

  onSubmit() {
    if (this.signinForm.valid) {
      const email = this.signinForm.get('email')?.value as string;
      const password = this.signinForm.get('password')?.value as string;
      this.authService.authenticate({ email, password }).subscribe({
        next: (houssem) => {
          this.authService.setToken(houssem.token);
          console.log('Login successful, token stored!');
          //is it an admin ?
          if (this.authService.getRoles().includes('ROLE_ADMIN')) {
            this.router.navigate(['/admin']);
          } else this.router.navigate(['/user']);
        },
        error: (err) => {
          console.error('Login failed:', err);
          //alert('Invalid credentials. Please try again.');
        },
      });
    }
  }
}
