import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { Vehicule } from '../../models/Vehicule';

@Injectable({
  providedIn: 'root'
})
export class VehiculeService {
  private apiUrl = 'http://localhost:8080/api/vehicule'; // Ensure URL is correct
  private vehiculesSubject=new BehaviorSubject<Vehicule[]>([]);
  vehicule$=this.vehiculesSubject.asObservable();


  constructor(private http: HttpClient) { 
    this.loadVehicules();
  }

  private loadVehicules():void{
    this.http.get<Vehicule[]>(this.apiUrl).subscribe(vehicules=>{
      this.vehiculesSubject.next(vehicules);
    })
  }

  getVehicules(): Observable<Vehicule[]> {
    return this.vehicule$;
  }

  addVehicule(vehicule: Vehicule): Observable<Vehicule> {
    return this.http.post<Vehicule>(this.apiUrl, vehicule).pipe(
      tap(()=>{
       this.loadVehicules();
      }),
      catchError(error=>{
        console.error('Error during add',error);
        return throwError(() => error);
      })
    );
  }

  updateVehicule(vehicule: Vehicule): Observable<Vehicule> {
    return this.http.put<Vehicule>(`${this.apiUrl}/${vehicule.id}`, vehicule).pipe(
      tap(()=>{
        this.loadVehicules();
      }),
      catchError(error => {
        console.error('Error during status update:', error);
        return throwError(() => error);
      })        
    );
  }

  deleteVehicule(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(()=>{
        this.loadVehicules();
      }),
      catchError(error => {
        console.error('Error during status update:', error);
        return throwError(() => error);
      })    
    );
  }
  getVehiculeById(id:number):Observable<Vehicule>{
    return this.http.get<Vehicule>(`${this.apiUrl}/${id}`);
  }
}
