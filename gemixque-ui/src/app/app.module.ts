import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  GameDetailsComponent,
  GamesComponent,
  HomeComponent,
  ProfileComponent,
  SignUpComponent,
} from './pages';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from './shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RecommendationsModalComponent } from './pages/profile/recommendations-modal';
import { NgChartsModule } from 'ng2-charts';


const pages = [
  SignUpComponent,
  HomeComponent,
  ProfileComponent,
  GamesComponent,
  GameDetailsComponent,
];

@NgModule({
  declarations: [AppComponent, ...pages, RecommendationsModalComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    NgChartsModule,
    SharedModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
