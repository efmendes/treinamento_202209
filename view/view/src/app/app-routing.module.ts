import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';
import { ClientesCadastrarEditarComponent } from './pages/clientes/clientes-cadastrar-editar/clientes-cadastrar-editar.component';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { CaixaComponent } from './pages/contas/caixa/caixa.component';
import { DepositoComponent } from './pages/contas/caixa/deposito/deposito.component';
import { ExtratoAgenciaContaComponent } from './pages/contas/caixa/extrato-agencia-conta/extrato-agencia-conta.component';
import { ExtratoComponent } from './pages/contas/caixa/extrato/extrato.component';
import { SaqueComponent } from './pages/contas/caixa/saque/saque.component';
import { TransferenciaComponent } from './pages/contas/caixa/transferencia/transferencia.component';
import { ContasCadastrarEditarComponent } from './pages/contas/contas-cadastrar-editar/contas-cadastrar-editar.component';
import { ContasComponent } from './pages/contas/contas.component';
import { ListaContasCpfComponent } from './pages/contas/lista-contas-cpf/lista-contas-cpf.component';
import { IndexComponent } from './pages/index/index.component';


const routes: Routes = [
  {
    path: '', component: IndexComponent
  },
  {
    path: 'clientes', component: ClientesComponent
  },
  {
    path: 'contas', component: ContasComponent
  },
  {
    path: 'contas/cadastrar', component: ContasCadastrarEditarComponent
  },
  {
    path: 'contas/cadastrar/:id', component: ContasCadastrarEditarComponent
  },
  {
    path: 'contas/editar/:id', component: ContasCadastrarEditarComponent
  },
  {
    path: 'contas/extrato/:id', component: ExtratoComponent
  },
  {
    path: 'contas/extrato/:agencia/:conta', component: ExtratoAgenciaContaComponent
  },
  {
    path: 'contas/:cpf', component: ListaContasCpfComponent
  },
  {
    path: 'clientes/cadastrar', component: ClientesCadastrarEditarComponent
  },
  {
    path: 'clientes/editar/:id', component: ClientesCadastrarEditarComponent
  },
  {
    path: 'caixa/deposito', component: DepositoComponent,
  },
  {
    path: 'caixa/deposito/:id', component: DepositoComponent,
  },
  {
    path: 'caixa/saque', component: SaqueComponent,
  },
  {
    path: 'caixa/saque/:id', component: SaqueComponent,
  },
  {
    path: 'caixa/transferencia', component: TransferenciaComponent,
  },
  {
    path: 'caixa/transferencia/:id', component: TransferenciaComponent,
  },
  {
    path: 'caixa', component: CaixaComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }