import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarburantComponent } from './components/carburant/carburant.component';
import { ConducteurComponent } from './components/conducteur/conducteur.component';
import { ItineraireComponent } from './components/itineraire/itineraire.component';
import { LoginComponent } from './components/login/login.component';
import { MaintenanceComponent } from './components/maintenance/maintenance.component';
import { ProfilComponent } from './components/profil/profil.component';
import { RegisterComponent } from './components/register/register.component';
import { MainComponent } from './shared/layout/main/main.component';
import { SideBarComponent } from './shared/layout/side-bar/side-bar.component';
import { VehiculeComponent } from './components/vehicule/vehicule.component';
import { HttpClientModule, HTTP_INTERCEPTORS, provideHttpClient, withFetch } from '@angular/common/http';
import { NgHttpLoaderModule } from 'ng-http-loader';
import { AuthService } from './services/auth.service';
import { AuthInterceptor } from './auth.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // Si vous utilisez des animations

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    SideBarComponent,
    ConducteurComponent,
    CarburantComponent,
    ItineraireComponent,
    MaintenanceComponent,
    ProfilComponent,
    MainComponent,
    VehiculeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgHttpLoaderModule.forRoot(),
    BrowserAnimationsModule // Ajoutez ceci si vous utilisez des animations
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    AuthService,
    
    provideHttpClient(withFetch()) //  // Si vous avez une raison spécifique d'utiliser cela
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
