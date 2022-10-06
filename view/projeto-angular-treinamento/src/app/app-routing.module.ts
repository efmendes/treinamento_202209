import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ContasComponent } from './pages/conta/contas/contas.component';
import { FormContaComponent } from './pages/conta/form-conta/form-conta.component';
import { FormSaqueDepositoComponent } from './pages/conta/form-saque-deposito/form-saque-deposito.component';
import { FormTransferComponent } from './pages/conta/form-transfer/form-transfer.component';
import { ExtratosComponent } from './pages/extrato/extratos/extratos.component';
import { HomeComponent } from './pages/home/home.component';
import { CadastroComponent } from './pages/login/cadastro/cadastro.component';
import { FormClientComponent } from './pages/login/form-client/form-client.component';
import { LoginComponent } from './pages/login/logins/login.component';


const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  {
    path: 'contas', component: ContasComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'login/cadastrar/:cpf', component: CadastroComponent
  },
  {
    path: 'login/cliente', component: FormClientComponent
  },
  {
    path: 'contas/editar/:id', component: FormContaComponent
  },
  {
    path: 'contas/operacoes/:id', component:  FormSaqueDepositoComponent
  },
  {
    path: 'contas/transferencia/:id', component:  FormTransferComponent
  },
  {
    path: 'extratos', component: ExtratosComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
