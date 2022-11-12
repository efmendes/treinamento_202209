import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { HomeComponent } from './pages/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './components/header/header.component';
import { ContasComponent } from './pages/contas/contas.component';
import { CadastrarEditarComponent } from './pages/clientes/cadastrar/cadastrar.component';
import { FooterComponent } from './components/footer/footer.component';
import { TransferenciaComponent } from './pages/contas/transferencia/transferencia.component';
import { ExtratoComponent } from './pages/extrato/extrato.component';
import { SaqueComponent } from './pages/contas/saque/saque.component';
import { DepositoComponent } from './pages/contas/deposito/deposito.component';
import { ClienteContasComponent } from './pages/clientes/cliente-contas/cliente-contas.component';
import { CadastrarContaClienteComponent } from './pages/clientes/cliente-contas/cadastrar-conta-cliente/cadastrar-conta-cliente.component';
import { EditarContaComponent } from './pages/contas/editar-conta/editar-conta.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    HomeComponent,
    HeaderComponent,
    ContasComponent,
    CadastrarEditarComponent,
    FooterComponent,
    TransferenciaComponent,
    ExtratoComponent,
    SaqueComponent,
    DepositoComponent,
    ClienteContasComponent,
    CadastrarContaClienteComponent,
    EditarContaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
