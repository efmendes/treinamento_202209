import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';

import { FormClientComponent } from './pages/login/form-client/form-client.component';
import { ContasComponent } from './pages/conta/contas/contas.component';
import { HomeComponent } from './pages/home/home.component';
import { CadastroComponent } from './pages/login/cadastro/cadastro.component';
import { LoginComponent } from './pages/login/logins/login.component';
import { FormContaComponent } from './pages/conta/form-conta/form-conta.component';
import { FormSaqueDepositoComponent } from './pages/conta/form-saque-deposito/form-saque-deposito.component';
import { FormTransferComponent } from './pages/conta/form-transfer/form-transfer.component';
import { ExtratosComponent } from './pages/extrato/extratos/extratos.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FormClientComponent,
    LoginComponent,
    CadastroComponent,
    ContasComponent,
    FormContaComponent,
    FormSaqueDepositoComponent,
    FormTransferComponent,
    ExtratosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
