import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Conducteur } from '../../../models/Conducteur';
import { User } from '../../../models/User';
import { Vehicule } from '../../../models/Vehicule';
import { ConducteurService } from '../../services/conducteur.service';
import { UserService } from '../../services/user.service';
import { VehiculeService } from '../../services/vehicule.service';

@Component({
  selector: 'app-conducteur',
  templateUrl: './conducteur.component.html',
  styleUrls: ['./conducteur.component.css']
})
export class ConducteurComponent implements OnInit {
  showForm = false;
  currentUser: User | null = null;
  currentVehicule: Vehicule | null = null;
  conducteur$: Observable<Conducteur[]> | undefined;
  vehicule$: Observable<Vehicule[]> | undefined;
  conducteurForm: FormGroup;
  isEditing = false;
  conducteurToEdit: Conducteur | null = null;

  constructor(
    private fb: FormBuilder,
    private conducteurService: ConducteurService,
    private vehiculeService: VehiculeService,
    private userService: UserService
  ) {
    this.conducteurForm = this.fb.group({
      nom: ['', [Validators.required]],
      prenom: ['', [Validators.required]],
      dateNaissance: ['', Validators.required],
      numeroTelephone: ['', Validators.required],
      adresse: ['', Validators.required],
      email: ['', Validators.required],
      numeroPermis: ['', Validators.required],
      dateDelivrancePermis: ['', Validators.required],
      dateExpirationPermis: ['', Validators.required],
      typePermis: ['', Validators.required],
      dateDebutEmploi: ['', Validators.required],
      statutEmploi: ['', Validators.required],
      vehiculeAssigneId: [null], // Initialize as null
      userId: ['']
    });
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  closeForm() {
    this.showForm = false;
  }

  ngOnInit(): void {
    this.vehicule$ = this.vehiculeService.getVehicules();
    this.conducteur$ = this.conducteurService.getConducteurs();
    this.getCurrentUser();
  }

  onVehiculeChange(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    const vehicule = this.conducteurForm.get('vehiculeAssigneId')?.value as Vehicule; // Get the selected Vehicule object

    if (vehicule) {
      this.currentVehicule = vehicule;
      console.log('Véhicule assigné:', this.currentVehicule);
    }
  }

  getCurrentUser(): void {
    this.userService.getCurrentUser().subscribe(
      user => {
        this.currentUser = user;
        if (this.currentUser) {
          this.conducteurForm.patchValue({ userId: this.currentUser }); // Use user id
          console.log(this.currentUser);
        }
      },
      error => {
        console.error('Error fetching user data', error);
      }
    );
  }

  addConducteur(): void {
    if (this.conducteurForm.valid) {
      const formValue = this.conducteurForm.value;
      const newConducteur = {
        ...formValue,
        vehiculeAssigneId: formValue.vehiculeAssigneId // Use the vehicule id
      };

      if (this.isEditing && this.conducteurToEdit) {
        const updatedConducteur = { ...this.conducteurToEdit, ...newConducteur };
        this.conducteurService.updateConducteur(updatedConducteur).subscribe({
          next: response => {
            console.log('Conducteur mis à jour avec succès', response);
            this.conducteur$ = this.conducteurService.getConducteurs();
            this.isEditing = false;
            this.conducteurToEdit = null;
            this.closeForm();
          },
          error: error => console.error('Erreur lors de la mise à jour du conducteur', error)
        });
      } else {
        this.conducteurService.addConducteur(newConducteur).subscribe({
          next: response => {
            console.log('Conducteur créé avec succès', response);
            this.conducteur$ = this.conducteurService.getConducteurs();
            this.closeForm();
          },
          error: error => console.error('Erreur lors de la création du conducteur', error)
        });
      }
    }
  }

  editConducteur(conducteur: Conducteur): void {
    this.isEditing = true;
    this.conducteurToEdit = conducteur;
    this.conducteurForm.patchValue(conducteur);
    this.showForm = true;
  }

  deleteConducteur(conducteurId: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce conducteur ?')) {
      this.conducteurService.deleteConnducteur(conducteurId).subscribe({
        next: () => {
          console.log('Conducteur supprimé avec succès');
          this.conducteur$ = this.conducteurService.getConducteurs();
        },
        error: error => console.error('Erreur lors de la suppression du conducteur', error)
      });
    }
  }
}
