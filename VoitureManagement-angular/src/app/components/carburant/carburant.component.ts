import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Coutcarburant } from '../../../models/Coutcarburant';
import { User } from '../../../models/User';
import { Vehicule } from '../../../models/Vehicule';
import { CarburantService } from '../../services/carburant.service';
import { UserService } from '../../services/user.service';
import { VehiculeService } from '../../services/vehicule.service';

@Component({
  selector: 'app-carburant',
  templateUrl: './carburant.component.html',
  styleUrls: ['./carburant.component.css']
})
export class CarburantComponent implements OnInit{

  showForm = false;
  fuelForm: FormGroup;
  currentUser:User |null=null;
  currentVehicule:Vehicule |null=null;
  carburant$:Observable<Coutcarburant[]> |undefined;
  vehicule$: Observable<Vehicule[]> | undefined;
  isEditing=false;
  carburantToEdit: Coutcarburant | null = null;

  constructor(private fb: FormBuilder,private carburantService:CarburantService,private userService:UserService,private vehiculeService:VehiculeService) {
    this.fuelForm = this.fb.group({
      date: ['', Validators.required],
      quantite: ['', [Validators.required, Validators.min(0)]],
      prix: ['', [Validators.required, Validators.min(0)]],
      total: [{ value: '', disabled: true }, Validators.required],
      vehiculeId: [null],
      userId:['']
    });
  }
  ngOnInit(): void {
    this.vehicule$=this.vehiculeService.getVehicules();
    this.carburant$=this.carburantService.getCarburants();
    this.getCurrentUser();
  }
  
  getCurrentUser(): void {
    this.userService.getCurrentUser().subscribe(
      user => {
        this.currentUser = user;
        if (this.currentUser) {
          this.fuelForm.patchValue({ userId: this.currentUser }); // Use user id
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
    const vehicule = this.fuelForm.get('vehiculeId')?.value as Vehicule; // Get the selected Vehicule object

    if (vehicule) {
      this.currentVehicule = vehicule;
      console.log('Véhicule :', this.currentVehicule);
    }
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  closeForm() {
    this.showForm = false;
  }

  calculateTotal() {
    const quantite = this.fuelForm?.get('quantite')?.value || 0;
    const prix = this.fuelForm?.get('prix')?.value || 0;
    const total = quantite * prix;
    this.fuelForm?.get('total')?.setValue(total.toFixed(2), { emitEvent: false });
  }

  addCarburant(): void {
    if (this.fuelForm.valid) {
      const formValue = this.fuelForm.getRawValue();
      const newCarburant: Coutcarburant = {
        ...formValue,
        vehiculeId: formValue.vehiculeId,
        userId: formValue.userId
      };
  
      if (this.isEditing && this.carburantToEdit) {
        const updatedCarburant = { ...this.carburantToEdit, ...newCarburant };
        this.carburantService.updateCarburant(updatedCarburant).subscribe({
          next: response => {
            console.log('Carburant mis à jour avec succès', response);
            this.carburant$ = this.carburantService.getCarburants();
            this.isEditing = false;
            this.carburantToEdit = null;
            this.closeForm();
          },
          error: error => console.error('Erreur lors de la mise à jour du carburant', error)
        });
      } else {
        this.carburantService.addCarburant(newCarburant).subscribe({
          next: response => {
            console.log('Carburant créé avec succès', response);
            this.carburant$ = this.carburantService.getCarburants();
            this.closeForm();
          },
          error: error => console.error('Erreur lors de la création du carburant', error)
        });
      }
    }
  }
  

  editCarburant(carburant: Coutcarburant): void {
    this.isEditing = true;
    this.carburantToEdit = carburant;
    this.fuelForm.patchValue({
      date: carburant.date,
      quantite: carburant.quantite,
      prix: carburant.prix,
      total: carburant.total,
      vehiculeId: carburant.vehiculeId,
      userId: carburant.userId
    });
    this.showForm = true;
  }
  

  deleteCarburant(carburantId: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce carburant ?')) {
      this.carburantService.deleteCarburant(carburantId).subscribe({
        next: () => {
          console.log('Carburant supprimé avec succès');
          this.carburant$=this.carburantService.getCarburants();
        },
        error: error => console.error('Erreur lors de la suppression du Carburant', error)
      });
    }
  }


 

  private resetForm(): void {
    this.isEditing = false;
    this.carburantToEdit = null;
    this.fuelForm.reset();
  }

}
