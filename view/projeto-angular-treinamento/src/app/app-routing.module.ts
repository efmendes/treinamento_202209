
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CadastroComponent } from './pages/login/cadastro/cadastro.component';
import { ClientesComponent } from './pages/cliente/clientes/clientes.component';

import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/logins/login.component';
import { ContasComponent } from './pages/conta/contas/contas.component';
import { FormClientComponent } from './pages/cliente/form-client/form-client.component';

const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  {
    path: 'clientes', component: ClientesComponent
  },
  {
    path: 'clientes/cadastro', component: FormClientComponent
  },
  {
    path: 'clientes/editar/:id', component: FormClientComponent
  },
  {
    path: 'contas', component: ContasComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'login/cadastrar', component: CadastroComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
