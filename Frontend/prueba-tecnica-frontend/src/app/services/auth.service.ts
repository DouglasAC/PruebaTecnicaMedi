import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LogingResponse } from '../models/loging-response';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly API_URL =  `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient, private router: Router) { }

  loging(username: string, password: string) {
    return this.http
      .post<LogingResponse>(`${this.API_URL}/login`, { username: username, password: password} )
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
        })
      );
  };


  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
