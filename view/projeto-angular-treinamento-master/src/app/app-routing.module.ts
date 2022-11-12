import { EditarContaComponent } from './pages/contas/editar-conta/editar-conta.component';
import { CadastrarContaClienteComponent } from './pages/clientes/cliente-contas/cadastrar-conta-cliente/cadastrar-conta-cliente.component';
import { ClienteContasComponent } from './pages/clientes/cliente-contas/cliente-contas.component';
import { ExtratoComponent } from './pages/extrato/extrato.component';
import { TransferenciaComponent } from './pages/contas/transferencia/transferencia.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarEditarComponent } from './pages/clientes/cadastrar/cadastrar.component';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { ContasComponent } from './pages/contas/contas.component';
import { HomeComponent } from './pages/home/home.component';
import { SaqueComponent } from './pages/contas/saque/saque.component';
import { DepositoComponent } from './pages/contas/deposito/deposito.component';

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
    path: 'clientes/cadastrar', component: CadastrarEditarComponent
  },
  {
    path: 'clientes/editar/:id', component: CadastrarEditarComponent
  },
  {
    path: 'cliente/minhas-contas/:cpf', component: ClienteContasComponent
  },
  {
    path: 'cliente/cadastrar-conta/:id', component: CadastrarContaClienteComponent
  },
  {
    path: 'contas/editar/:id', component: EditarContaComponent
  },
  {
    path: 'contas/saque/:id', component: SaqueComponent
  },
  {
    path: 'contas/deposito/:id', component: DepositoComponent
  },
  {
    path: 'contas/transferencia/:id', component: TransferenciaComponent
  },
  {
    path: 'contas/extrato/:id', component: ExtratoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
