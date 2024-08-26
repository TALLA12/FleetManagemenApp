import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { Conducteur } from '../../models/Conducteur';


@Injectable({
  providedIn: 'root'
})
export class ConducteurService {
  private apiUrl='http://localhost:8080/api/conducteur';
  private conducteurSubject=new BehaviorSubject<Conducteur[]>([]);
  conducteur$=this.conducteurSubject.asObservable();
  constructor(private http:HttpClient) {
    this.loadConducteurs();
   }

  private loadConducteurs():void{
     this.http.get<Conducteur[]>(this.apiUrl).subscribe(conducteurs=>{
      this.conducteurSubject.next(conducteurs);
     })
  }
  getConducteurs():Observable<Conducteur[]>{
    return this.conducteur$;
  }
  addConducteur(conducteur:Conducteur):Observable<Conducteur>{
    return this.http.post<Conducteur>(this.apiUrl,conducteur).pipe(
      tap(()=>{
        this.loadConducteurs();
      }),
      catchError(error=>{
        console.error('Error during add',error);
        return throwError(() => error);
      }),
    )
  }
  updateConducteur(conducteur:Conducteur):Observable<Conducteur>{
    return this.http.put<Conducteur>(`${this.apiUrl}/${conducteur.id}`,conducteur).pipe(
      tap(()=>{
        this.loadConducteurs();
      }),
      catchError(error=>{
        console.error('Error during add',error);
        return throwError(() => error);
      }),

    )
  }
  deleteConnducteur(id:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(()=>{
        this.loadConducteurs();
      }),
      catchError(error=>{
        console.error('Error during add',error);
        return throwError(() => error);
      }),
    )
  }
  
}
