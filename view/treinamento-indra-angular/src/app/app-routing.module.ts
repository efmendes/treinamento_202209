import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { HomeComponent } from './pages/home/home.component';
import { ContasComponent } from './pages/contas/contas.component';
import { PaginaComponent } from './pages/clientes/pagina/pagina.component';
import { ExtratoComponent } from './pages/extrato/extrato.component';

const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  {
    path: 'clients', component: ClientesComponent
  },
  {
    path: 'accounts', component: ContasComponent
  },
  {
    path: 'client/:id', component: PaginaComponent
  },
  {
    path: 'extract', component: ExtratoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
