import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Itineraire } from '../../models/Itineraire';

@Injectable({
  providedIn: 'root'
})
export class ItineraireService {
  private apiUrl = 'http://localhost:8080/api/itineraire';
  private itineraireSubject = new BehaviorSubject<Itineraire[]>([]);
  itineraire$ = this.itineraireSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadItineraire();
  }

  private loadItineraire() {
    this.http.get<Itineraire[]>(this.apiUrl).subscribe(itineraires => {
      this.itineraireSubject.next(itineraires);
    });
  }

  getItineraire(): Observable<Itineraire[]> {
    return this.itineraire$;
  }

  addItineraire(itineraire: Itineraire): Observable<Itineraire> {
    return this.http.post<Itineraire>(this.apiUrl, itineraire).pipe(
      tap(() => this.loadItineraire()),
      catchError(error => {
        console.error('Erreur lors de l\'ajout', error);
        return throwError(() => error);
      })
    );
  }

  updateItineraire(itineraire: Itineraire): Observable<Itineraire> {
    return this.http.put<Itineraire>(`${this.apiUrl}/${itineraire.id}`, itineraire).pipe(
      tap(() => this.loadItineraire()),
      catchError(error => {
        console.error('Erreur lors de la mise à jour', error);
        return throwError(() => error);
      })
    );
  }

  deleteItineraire(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => this.loadItineraire()),
      catchError(error => {
        console.error('Erreur lors de la suppression', error);
        return throwError(() => error);
      })
    );
  }
}
