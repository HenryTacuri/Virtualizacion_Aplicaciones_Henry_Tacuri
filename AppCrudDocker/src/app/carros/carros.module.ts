import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarrosRoutingModule } from './carros-routing.module';
import { EditarPageComponent } from './pages/editar-page/editar-page.component';
import { NewPageComponent } from './pages/new-page/new-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { TableCarrosComponent } from './components/table-carros/table-carros.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from './shared/shared.module';


@NgModule({
  declarations: [
    EditarPageComponent,
    NewPageComponent,
    HomePageComponent,
    TableCarrosComponent,
  ],
  imports: [
    CommonModule,
    CarrosRoutingModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class CarrosModule { }
