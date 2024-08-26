import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private updateSource=new BehaviorSubject<void>(undefined);
  update$=this.updateSource.asObservable();

  notifyUpdate(){
    this.updateSource.next();
  }
  
}
