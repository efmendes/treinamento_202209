import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarEditarClienteComponent } from './pages/clientes/cadastrar-cliente/cadastrar-cliente.component';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { CadastrarEditarContaComponent } from './pages/contas/cadastrar-conta/cadastrar-conta.component';
import { ContasComponent } from './pages/contas/contas.component';
import { ExtratoComponent } from './pages/contas/extrato/extrato.component';
import { HomeComponent } from './pages/home/home.component';
import { DepositoComponent } from './pages/operacoes/deposito/deposito.component';
import { SaqueComponent } from './pages/operacoes/saque/saque.component';
import { TransferenciaComponent } from './pages/operacoes/transferencia/transferencia.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'clientes',
    component: ClientesComponent,
  },
  {
    path: 'clientes/cadastro',
    component: CadastrarEditarClienteComponent,
  },
  {
    path: 'clientes/edicao/:id',
    component: CadastrarEditarClienteComponent,
  },
  {
    path: 'contas',
    component: ContasComponent,
  },
  {
    path: 'contas/cadastro',
    component: CadastrarEditarContaComponent,
  },
  {
    path: 'contas/edicao/:id',
    component: CadastrarEditarContaComponent,
  },
  {
    path: 'contas/extrato/:agencia/:numero',
    component: ExtratoComponent,
  },
  {
    path: 'operacao/saque',
    component: SaqueComponent,
  },
  {
    path: 'operacao/deposito',
    component: DepositoComponent,
  },
  {
    path: 'operacao/transferencia',
    component: TransferenciaComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
