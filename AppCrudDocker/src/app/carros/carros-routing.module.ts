import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { EditarPageComponent } from './pages/editar-page/editar-page.component';
import { NewPageComponent } from './pages/new-page/new-page.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {path: 'home', component: HomePageComponent},
      {path: 'editar/:cod', component: EditarPageComponent},
      {path: 'new', component: NewPageComponent},
      {path: '**', redirectTo: 'home'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarrosRoutingModule { }
