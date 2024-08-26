import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User, UserStatus } from '../../../models/User';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  users$: Observable<User[]> | undefined;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.users$ = this.userService.getUsers();
  }

  toggleAccount(user: User): void {
    const newStatus: UserStatus = user.status === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE';
    this.userService.updateUserStatus(user.id, newStatus).subscribe(() => {
      // No need to manually update users here; it's handled by BehaviorSubject in UserService
    });
  }

  deleteAccount(userId: number): void {
   if(confirm('Are you sure you want to delete this user?')){
    this.userService.deleteUser(userId).subscribe(()=>{
     
    });
   }
  }
}
