import { ContaBancaria } from './interfaces/conta-bancaria';
import { ContaBancariaService } from './services/conta-bancaria.service';
import { Component } from '@angular/core';
import { Cliente } from './interfaces/cliente';
import { ClienteService } from './services/cliente.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'banco-view';
  clientes: Cliente[] = [];
  contas: ContaBancaria[] = [];
  constructor(private clienteService: ClienteService,
    private contaService: ContaBancariaService){
    this.buscarTodosClientes();
    this.buscarTodasContas();
  }

  buscarTodosClientes(){
    this.clienteService.buscarTodosClientes().subscribe((clientes: Cliente[]) => {
      this.clientes = clientes;
    });
  }

  buscarTodasContas(){
    this.contaService.findAll().subscribe((contas: ContaBancaria[]) => {
      this.contas = contas;
    });
  }
}
