import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { User, UserStatus } from '../../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users'; 
  private usersSubject = new BehaviorSubject<User[]>([]);
  users$ = this.usersSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadUsers(); // Initial loading
  }

  private loadUsers(): void {
    this.http.get<User[]>(this.apiUrl).subscribe(users => {
      this.usersSubject.next(users);
    });
  }

  getUsers(): Observable<User[]> {
    return this.users$;
  }


  updateUserStatus(userId: number, status: UserStatus): Observable<void> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
    return this.http.put<void>(`${this.apiUrl}/${userId}/status`, { status }, { headers })
      .pipe(
        tap(() => {
          this.loadUsers();
        }),
        catchError(error => {
          console.error('Error during status update:', error);
          return throwError(() => error);
        })
      );
  }
  deleteUser(UserId:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${UserId}`)
    .pipe(
    tap(()=>{
      this.loadUsers();
    }),
    catchError(error=>{
      console.error('Error during status delete:',error);
      return throwError(() => error);
    })
    );
    
  }
  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/current`);
  }
  
}
