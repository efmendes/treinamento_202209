import { Component } from '@angular/core';
import { ICliente } from './interfaces/cliente';
import { IConta } from './interfaces/conta';
import { ClienteService } from './service/cliente.service';
import { ContasService } from './service/contas.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  clientes: ICliente[] = [];
  contas: IConta[] = [];
  title = 'bancoFront';
  constructor(private clienteService: ClienteService, private contaService: ContasService) {
    this.buscarTodosClientes();
    this.buscarTodasContas();
  }

  buscarTodosClientes() {
    this.clienteService.buscarTodosClientes().subscribe((clientes: ICliente[]) => {
      this.clientes = clientes;
      console.log(this.clientes);
    });
  }

  buscarTodasContas() {
    this.contaService.buscarTodasContas().subscribe((contas: IConta[]) => {
      this.contas = contas;
      console.log(this.clientes);
    });
  }

}
