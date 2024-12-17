import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { NewUserModel } from '../models/usermodel';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiServerUrl = 'http://localhost:8089/users';

  constructor(private http: HttpClient, private router: Router) { }

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['signin']);
  }

  authenticate({ email, password }: { email: string; password: string }): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { email, password };
    return this.http.post<string>(`${this.apiServerUrl}/authentificate`, body, { headers });
  }

  new(user: NewUserModel) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = {
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      password: user.password
    };
    return this.http.post<string>(`${this.apiServerUrl}/new`, body, { headers, responseType: 'text' as 'json' });
  }
  

}
