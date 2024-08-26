import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: string | null = null;
  private ApiUrl = 'http://localhost:8080/api/auth';

  constructor(
    private http: HttpClient,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  setToken(token: string): void {
    this.token = token;
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('token', token);
    }
  }

  getToken(): string | null {
    if (!this.token) {
      if (isPlatformBrowser(this.platformId)) {
        this.token = localStorage.getItem('token');
      }
    }
    return this.token;
  }

  register(payload: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
    return this.http.post(`${this.ApiUrl}/register`, payload, { headers });
  }

  login(loginPayload: { username: string, motDePasse: string }): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
    return this.http.post<any>(`${this.ApiUrl}/login`, loginPayload, { headers })
      .pipe(tap(response => this.handleAuth(response.token)));
  }

  logout(): void {
    this.token = null;
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('token');
    }
    this.router.navigate(['/login']);
  }

  private handleAuth(token: string): void {
    this.setToken(token);
  }
  
}
