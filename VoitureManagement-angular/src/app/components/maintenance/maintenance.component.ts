import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { response } from 'express';
import { Observable } from 'rxjs';
import { Maintenance } from '../../../models/Maintenance';
import { User } from '../../../models/User';
import { Vehicule } from '../../../models/Vehicule';
import { MaintenanceService } from '../../services/maintenance.service';
import { UserService } from '../../services/user.service';
import { VehiculeService } from '../../services/vehicule.service';

@Component({
  selector: 'app-maintenance',
  templateUrl: './maintenance.component.html',
  styleUrl: './maintenance.component.css'
})
export class MaintenanceComponent implements OnInit{

  MaintenanceForm!:FormGroup;
  showForm = false;
  currentUser:User | null=null;
  maintenance$:Observable<Maintenance[]> | undefined;
  vehicule$: Observable<Vehicule[]> | undefined;
  isEditing=false;
  currentVehicule: Vehicule | null = null;
   maintenanceToEdit:Maintenance |null=null;
constructor(private fb:FormBuilder,private maintenanceService:MaintenanceService,private userService:UserService,private vehiculeService:VehiculeService){
  this.MaintenanceForm=this.fb.group({
    date:['',[Validators.required]],
    description:['',Validators.required],
    cout:['',Validators.required],
    vehiculeId:[null],
    userId:['']
  });
}
toggleForm(): void {
  this.showForm = !this.showForm;
  if (!this.showForm) {
    this.resetForm(); // Réinitialiser le formulaire lorsqu'on ferme le formulaire
  }
}

closeForm(): void {
  this.showForm = false;
  this.resetForm(); // Réinitialiser le formulaire lorsqu'on ferme le formulaire
}
  ngOnInit(): void {
      this.vehicule$ = this.vehiculeService.getVehicules();
      this.maintenance$=this.maintenanceService.getMaintenance();
      this.getCurrentUser();
  }

  getCurrentUser(): void {
    this.userService.getCurrentUser().subscribe(
      user => {
        this.currentUser = user;
        if (this.currentUser) {
          this.MaintenanceForm.patchValue({ userId: this.currentUser }); // Use user id
          console.log(this.currentUser);
        }
      },
      error => {
        console.error('Error fetching user data', error);
      }
    );
  }
  onVehiculeChange(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    const vehicule = this.MaintenanceForm.get('vehiculeId')?.value as Vehicule; // Get the selected Vehicule object

    if (vehicule) {
      this.currentVehicule = vehicule;
      console.log('Véhicule :', this.currentVehicule);
    }
  }

 
  addMaintenance(): void {
    if (this.MaintenanceForm.valid) {
      const formValue = this.MaintenanceForm.value;
      const newMaintenance = {
        ...formValue,
        vehiculeId: formValue.vehiculeId // Use the vehicule id
      };

      if (this.isEditing && this.maintenanceToEdit) {
        const updatedMaintenance = { ...this.maintenanceToEdit, ...newMaintenance };
        this.maintenanceService.updateMaintenance(updatedMaintenance).subscribe({
          next: response => {
            console.log('Maintenance mis à jour avec succès', response);
            this.maintenance$ = this.maintenanceService.getMaintenance();
            this.isEditing = false;
            this.maintenanceToEdit = null;
            this.closeForm();
          },
          error: error => console.error('Erreur lors de la mise à jour du maintenance', error)
        });
      } else {
        this.maintenanceService.addMaintenance(newMaintenance).subscribe({
          next: response => {
            console.log('Maintenance créé avec succès', response);
            this.maintenance$ = this.maintenanceService.getMaintenance();
            this.closeForm();
          },
          error: error => console.error('Erreur lors de la création du maintenance', error)
        });
      }
    }
  }

  editMaintenance(maintenance: Maintenance): void {
    this.isEditing = true;
    this.maintenanceToEdit = maintenance;
    this.MaintenanceForm.patchValue(maintenance);
    this.showForm = true;
  }

  deleteMaintenance(maintenanceId: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce maintennce ?')) {
      this.maintenanceService.deleteMaintenance(maintenanceId).subscribe({
        next: () => {
          console.log('Maintenance supprimé avec succès');
          this.maintenance$ = this.maintenanceService.getMaintenance();
        },
        error: error => console.error('Erreur lors de la suppression du Maintenace', error)
      });
    }
  }


 

  private resetForm(): void {
    this.isEditing = false;
    this.maintenanceToEdit = null;
    this.MaintenanceForm.reset();
  }

}
