import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ClientesComponent } from './pages/cliente/clientes/clientes.component';
import { FormClientComponent } from './pages/cliente/form-client/form-client.component';
import { ContasComponent } from './pages/conta/contas/contas.component';
import { HomeComponent } from './pages/home/home.component';
import { CadastroComponent } from './pages/login/cadastro/cadastro.component';
import { LoginComponent } from './pages/login/logins/login.component';
import { FormContaComponent } from './pages/conta/form-conta/form-conta.component';


@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    HomeComponent,
    HeaderComponent,
    FormClientComponent,
    LoginComponent,
    CadastroComponent,
    ContasComponent,
    FormContaComponent
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
