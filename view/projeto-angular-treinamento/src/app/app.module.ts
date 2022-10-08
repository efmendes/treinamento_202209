import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { HomeComponent } from './pages/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './components/header/header.component';
import { TextInputComponent } from './components/text-input/text-input.component';
import { NewUserFormComponent } from './components/new-user-form/new-user-form.component';
import { ContasComponent } from './pages/contas/contas.component';
import { CadastrarEditarClienteComponent } from './pages/clientes/cadastrar-cliente/cadastrar-cliente.component';
import { CadastrarEditarContaComponent } from './pages/contas/cadastrar-conta/cadastrar-conta.component';
import { SaqueComponent } from './pages/operacoes/saque/saque.component';
import { DepositoComponent } from './pages/operacoes/deposito/deposito.component';
import { TransferenciaComponent } from './pages/operacoes/transferencia/transferencia.component';
import { ButtonComponent } from './components/button/button.component';
import { ExtratoComponent } from './pages/contas/extrato/extrato.component';
import { LoadingComponent } from './components/loading/loading.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    HomeComponent,
    HeaderComponent,
    TextInputComponent,
    NewUserFormComponent,
    ContasComponent,
    CadastrarEditarClienteComponent,
    CadastrarEditarContaComponent,
    SaqueComponent,
    DepositoComponent,
    TransferenciaComponent,
    ButtonComponent,
    ExtratoComponent,
    LoadingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
