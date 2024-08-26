import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Vehicule } from '../../../models/Vehicule';
import { User } from '../../../models/User';
import { UserService } from '../../services/user.service';
import { VehiculeService } from '../../services/vehicule.service';

@Component({
  selector: 'app-vehicule',
  templateUrl: './vehicule.component.html',
  styleUrls: ['./vehicule.component.css'] // Correction de styleUrl en styleUrls
})
export class VehiculeComponent implements OnInit {
  vehiculeForm: FormGroup;
  currentUser: User | null = null;
  showForm = false;
  vehicule$: Observable<Vehicule[]> | undefined;
  isEditing = false; // Ajouté pour savoir si nous sommes en mode édition
  vehiculeToEdit: Vehicule | null = null; // Pour stocker le véhicule à éditer

  constructor(
    private fb: FormBuilder,
    private vehiculeService: VehiculeService,
    private userService: UserService
  ) {
    this.vehiculeForm = this.fb.group({
      marque: ['', Validators.required],
      modele: ['', Validators.required],
      plaqueImmatriculation: ['', Validators.required],
      dateAchat: ['', Validators.required],
      type: ['', Validators.required],
      etat: ['', Validators.required],
      userId: ['']
    });
  }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(user => {
      this.currentUser = user;
      if (this.currentUser) {
        this.vehiculeForm.patchValue({ userId: this.currentUser });
      }
    });

    this.vehicule$ = this.vehiculeService.getVehicules();
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

  addVehicule(): void {
    if (this.vehiculeForm.valid) {
      if (this.isEditing && this.vehiculeToEdit) {
        // Mise à jour du véhicule
        const updatedVehicule = { ...this.vehiculeToEdit, ...this.vehiculeForm.value };
        this.vehiculeService.updateVehicule(updatedVehicule).subscribe({
          next: response => {
            console.log('Véhicule mis à jour avec succès', response);
            this.closeForm();
          },
          error: error => console.error('Erreur lors de la mise à jour du véhicule', error)
        });
      } else {
        // Ajout d'un nouveau véhicule
        const newVehicule = this.vehiculeForm.value;
        this.vehiculeService.addVehicule(newVehicule).subscribe({
          next: response => {
            console.log('Véhicule créé avec succès', response);
            this.closeForm();
          },
          error: error => console.error('Erreur lors de la création du véhicule', error)
        });
      }
    }
  }

  editVehicule(vehicule: Vehicule): void {
    this.isEditing = true;
    this.vehiculeToEdit = vehicule;
    this.vehiculeForm.patchValue(vehicule);
    this.toggleForm(); // Afficher le formulaire en mode édition
  }

  deleteVehicule(vehiculeId: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce véhicule ?')) {
      this.vehiculeService.deleteVehicule(vehiculeId).subscribe({
        next: () => {
          console.log('Véhicule supprimé avec succès');
          // Rafraîchir la liste des véhicules
          this.vehicule$ = this.vehiculeService.getVehicules();
        },
        error: error => console.error('Erreur lors de la suppression du véhicule', error)
      });
    }
  }

  private resetForm(): void {
    this.isEditing = false;
    this.vehiculeToEdit = null;
    this.vehiculeForm.reset();
  }
}
