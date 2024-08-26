import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Conducteur } from '../../../models/Conducteur';
import { Itineraire } from '../../../models/Itineraire';
import { User } from '../../../models/User';
import { ConducteurService } from '../../services/conducteur.service';
import { ItineraireService } from '../../services/itineraire.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-itineraire',
  templateUrl: './itineraire.component.html',
  styleUrls: ['./itineraire.component.css']
})
export class ItineraireComponent implements OnInit {
  itineraireForm!: FormGroup;
  showForm = false;
  conducteur$!: Observable<Conducteur[]>;
  itineraire$!: Observable<Itineraire[]>;
  currentUser: User | null = null;
  isEditing = false;
  itineraireToEdit: Itineraire | null = null;

  constructor(
    private fb: FormBuilder,
    private conducteurService: ConducteurService,
    private userService: UserService,
    private itineraireService: ItineraireService
  ) {
    this.itineraireForm = this.fb.group({
      origine: ['', [Validators.required]],
      destination: ['', [Validators.required]],
      distance: ['', [Validators.required]],
      duree: ['', [Validators.required]],
      dateDepart: ['', [Validators.required]],
      dateArrivee: ['', [Validators.required]],
      conducteurId: [null],
      userId: ['']
    });
  }

  ngOnInit(): void {
    this.itineraire$ = this.itineraireService.getItineraire();
    this.conducteur$ = this.conducteurService.getConducteurs();
    this.getCurrentUser();
  }

  toggleForm() {
    this.showForm = !this.showForm;
  }

  closeForm() {
    this.showForm = false;
    this.isEditing = false;
    this.itineraireToEdit = null;
  }

  getCurrentUser(): void {
    this.userService.getCurrentUser().subscribe(
      user => {
        this.currentUser = user;
        if (this.currentUser) {
          this.itineraireForm.patchValue({ userId: this.currentUser.id });
        }
      },
      error => console.error('Error fetching user data', error)
    );
  }

  addItineraire(): void {
    if (this.itineraireForm.valid) {
      const formValue = this.itineraireForm.value;
      const newItineraire = { ...formValue, conducteur: formValue.conducteurId };

      if (this.isEditing && this.itineraireToEdit) {
        const updatedItineraire = { ...this.itineraireToEdit, ...newItineraire };
        this.itineraireService.updateItineraire(updatedItineraire).subscribe(
          () => {
            this.itineraire$ = this.itineraireService.getItineraire();
            this.closeForm();
          },
          error => console.error('Erreur lors de la mise à jour de l\'itinéraire', error)
        );
      } else {
        this.itineraireService.addItineraire(newItineraire).subscribe(
          () => {
            this.itineraire$ = this.itineraireService.getItineraire();
            this.closeForm();
          },
          error => console.error('Erreur lors de la création de l\'itinéraire', error)
        );
      }
    }
  }

  editItineraire(itineraire: Itineraire): void {
    this.isEditing = true;
    this.itineraireToEdit = itineraire;
    this.itineraireForm.patchValue(itineraire);
    this.showForm = true;
  }

  deleteItineraire(itineraireId: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cet itinéraire ?')) {
      this.itineraireService.deleteItineraire(itineraireId).subscribe(
        () => this.itineraire$ = this.itineraireService.getItineraire(),
        error => console.error('Erreur lors de la suppression de l\'itinéraire', error)
      );
    }
  }
}
