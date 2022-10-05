import { CadastraEditaClienteComponent } from './pages/clientes/cadastra-edita-cliente/cadastra-edita-cliente.component';
import { ContasComponent } from './pages/contas/contas.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { HomeComponent } from './pages/home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CadastraEditaContaComponent } from './pages/contas/cadastra-edita-conta/cadastra-edita-conta.component';
import { CaixaEletronicoComponent } from './pages/caixa/caixa-eletronico/caixa-eletronico.component';

const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  {
    path: 'clientes', component: ClientesComponent
  },
  {
    path: 'contas', component: ContasComponent
  },
  {
    path: 'clientes/cadastrar',
    component: CadastraEditaClienteComponent
  },
  {
    path: 'clientes/editar/:id',
    component: CadastraEditaClienteComponent
  },
  {
    path: 'contas/cadastrar',
    component: CadastraEditaContaComponent
  },
  {
    path: 'contas/editar/:id',
    component: CadastraEditaContaComponent
  },
  {
    path: 'caixa-eletronico',
    component: CaixaEletronicoComponent
  },
  {
    path: 'contas/caixa-eletronico/:id',
    component: CaixaEletronicoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    ReactiveFormsModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
