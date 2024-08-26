import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { Coutcarburant } from '../../models/Coutcarburant';

@Injectable({
  providedIn: 'root'
})
export class CarburantService {
  private apiUrl = 'http://localhost:8080/api/coutcarburant';
  private carburantSubject=new BehaviorSubject<Coutcarburant[]>([]);
  carburant$=this.carburantSubject.asObservable();
  constructor(private http:HttpClient) {
   this.loadCarburant();
  }
  private loadCarburant(){
    this.http.get<Coutcarburant[]>(this.apiUrl).subscribe(carburants=>{
      this.carburantSubject.next(carburants);
    })
  }
  getCarburants():Observable<Coutcarburant[]>{
    return this.carburant$;
  }
  addCarburant(carburant:Coutcarburant):Observable<Coutcarburant>{
    return this.http.post<Coutcarburant>(this.apiUrl,carburant).pipe(
      tap(()=>{
        this.loadCarburant();
      }),
      catchError(error=>{
        console.error('Error during add',error);
        return throwError(() => error);
      }),
    )
  }
  updateCarburant(carburant:Coutcarburant):Observable<Coutcarburant>{
    return this.http.put<Coutcarburant>(`${this.apiUrl}/${carburant.id}`,carburant).pipe(
      tap(()=>{
        this.loadCarburant();
      }),
      catchError(error=>{
        console.error('Error during update',error);
        return throwError(() => error);
      }),

    )
  }
  deleteCarburant(id:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(()=>{
        this.loadCarburant();
      }),
      catchError(error=>{
        console.error('Error during delete',error);
        return throwError(() => error);
      }),
    )
  }

}
