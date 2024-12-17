import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-signup',
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit{
  result:string="";
signupForm = new FormGroup({
    email: new FormControl('', Validators.required), //, Validators.email
    password: new FormControl('', Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
  });
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['user'])
    }
  }

  onSubmit() {
    if (this.signupForm.valid) {
      const firstName = this.signupForm.get('firstName')?.value as string;
      const lastName = this.signupForm.get('lastName')?.value as string;
      const email = this.signupForm.get('email')?.value as string;
      const password = this.signupForm.get('password')?.value as string;
      this.authService.new({ firstName, lastName, email, password }).subscribe({
        next: (res) => {
          //console.log(res); 
          this.router.navigate(['/signin']);
        },
        error: (err) => {
          console.error('Signup failed:', err);
        },
      });
    }
  }
  
}
