import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ClientesComponent, } from './pages/clientes/clientes.component';
import { ContasComponent } from './pages/contas/contas.component';
import { HttpClientModule } from '@angular/common/http';
import { ClientesCadastrarEditarComponent } from './pages/clientes/clientes-cadastrar-editar/clientes-cadastrar-editar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CaixaComponent } from './pages/contas/caixa/caixa.component';
import { DepositoComponent } from './pages/contas/caixa/deposito/deposito.component';
import { SaqueComponent } from './pages/contas/caixa/saque/saque.component';
import { IndexComponent } from './pages/index/index.component';
import { TransferenciaComponent } from './pages/contas/caixa/transferencia/transferencia.component';
import { ContasCadastrarEditarComponent } from './pages/contas/contas-cadastrar-editar/contas-cadastrar-editar.component';
import { CpfPipe } from './shared/pipes/cpf.pipe';
import { ListaContasCpfComponent } from './pages/contas/lista-contas-cpf/lista-contas-cpf.component';
import { ExtratoComponent } from './pages/contas/caixa/extrato/extrato.component';
import { LocalDateTimePipe } from './shared/pipes/local-date-time.pipe';
import { ExtratoAgenciaContaComponent } from './pages/contas/caixa/extrato-agencia-conta/extrato-agencia-conta.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ClientesComponent,
    ContasComponent,
    ClientesCadastrarEditarComponent,
    CaixaComponent,
    DepositoComponent,
    SaqueComponent,
    IndexComponent,
    TransferenciaComponent,
    ContasCadastrarEditarComponent,
    CpfPipe,
    ListaContasCpfComponent,
    ExtratoComponent,
    LocalDateTimePipe,
    ExtratoAgenciaContaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }