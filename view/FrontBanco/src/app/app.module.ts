import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { HomeComponent } from './pages/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './components/header/header.component';
import { ContasComponent } from './pages/contas/contas.component';
import { CadastraEditaClienteComponent } from './pages/clientes/cadastra-edita-cliente/cadastra-edita-cliente.component';
import { CadastraEditaContaComponent } from './pages/contas/cadastra-edita-conta/cadastra-edita-conta.component';
import { CaixaEletronicoComponent } from './pages/caixa/caixa-eletronico/caixa-eletronico.component';
import { FormOperacaoComponent } from './pages/caixa/form-operacao/form-operacao.component';
import { ExtratoComponent } from './pages/extrato/extrato.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    HomeComponent,
    HeaderComponent,
    ContasComponent,
    CadastraEditaClienteComponent,
    CadastraEditaContaComponent,
    CaixaEletronicoComponent,
    FormOperacaoComponent,
    ExtratoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    Ng2SearchPipeModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
