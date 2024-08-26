import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { Maintenance } from '../../models/Maintenance';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceService {
  private apiUrl = 'http://localhost:8080/api/maintenance';
  private maintenanceSubject=new BehaviorSubject<Maintenance[]>([]);
  maintenance$=this.maintenanceSubject.asObservable();
  constructor(private http:HttpClient) {
    this.loadMaintenances();

   }

  private loadMaintenances():void{
    this.http.get<Maintenance[]>(this.apiUrl).subscribe(maintenances=>{
       this.maintenanceSubject.next(maintenances);
    })
  }
  getMaintenance():Observable<Maintenance[]>{
    return this.maintenance$;
  }
  addMaintenance(maintenance:Maintenance):Observable<Maintenance>{
    return this.http.post<Maintenance>(this.apiUrl,maintenance).pipe(
      tap(()=>{
        this.loadMaintenances();
       }),
       catchError(error=>{
         console.error('Error during add',error);
         return throwError(() => error);
       })

    );
  }
  updateMaintenance(maintenance:Maintenance):Observable<Maintenance>{
    return this.http.put<Maintenance>(`${this.apiUrl}/${maintenance.id}`,maintenance).pipe(
      tap(()=>{
        this.loadMaintenances();
       }),
       catchError(error=>{
         console.error('Error during add',error);
         return throwError(() => error);
       })

    );
  }
  deleteMaintenance(id:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(()=>{
        this.loadMaintenances();
       }),
       catchError(error=>{
         console.error('Error during add',error);
         return throwError(() => error);
       })

    );
  }
}
