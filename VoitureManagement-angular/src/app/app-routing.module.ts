import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import path from 'path';
import { CarburantComponent } from './components/carburant/carburant.component';
import { ConducteurComponent } from './components/conducteur/conducteur.component';
import { ItineraireComponent } from './components/itineraire/itineraire.component';
import { LoginComponent } from './components/login/login.component';
import { MaintenanceComponent } from './components/maintenance/maintenance.component';
import { ProfilComponent } from './components/profil/profil.component';
import { RegisterComponent } from './components/register/register.component';
import { VehiculeComponent } from './components/vehicule/vehicule.component';
import { authGuard } from './services/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'conducteurs', component: ConducteurComponent, canActivate: [authGuard] },
  { path: 'maintenances', component: MaintenanceComponent, canActivate: [authGuard] },
  { path: 'carburants', component: CarburantComponent, canActivate: [authGuard] },
  { path: 'itineraires', component: ItineraireComponent, canActivate: [authGuard] },
  { path: 'profils', component: ProfilComponent, canActivate: [authGuard] },
  { path: 'vehicules', component: VehiculeComponent, canActivate: [authGuard] },
  { path: '', redirectTo: 'profils', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
