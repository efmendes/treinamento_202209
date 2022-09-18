import { Component } from '@angular/core';
import { ICliente } from './interfaces/cliente';
import { IConta } from './interfaces/conta';
import { ClienteService } from './services/cliente.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'treinamento-banco';

  constructor(private clienteService: ClienteService) {
    this.buscarTodosOsClientes();
    this.buscarTodasAsContas();
  }

  clientes: ICliente[] = [];
  contas: IConta[] = [];

  buscarTodosOsClientes() {
    this.clienteService.buscarClientes().subscribe((clientes: ICliente[]) => {
      this.clientes = clientes;
      this.ordenarClientesPorCampo();
    });
  }

  deletarCliente(id: number) {
    this.clienteService.deletarCliente(id);
  }

  ordenarClientesPorCampo(campo: "id" | "ativo" | "cpf" | "observacoes" | "nome" | "email" = "id") {
    this.clientes.sort((a, b) => {
      if (a[campo]! < b[campo]!) {
        return -1;
      }
      if (a[campo]! > b[campo]!) {
        return 1;
      }
      return 0;
    });
  }

  buscarTodasAsContas() {
    this.clienteService.buscarContas().subscribe((contas: IConta[]) => {
      this.contas = contas;
      this.ordenarContasPorCampo();
    });
  }

  ordenarContasPorCampo(campo: "id" | "agencia" | "numero" | "saldo" = "id") {
    this.contas.sort((a, b) => {
      if (a[campo]! < b[campo]!) {
        return -1;
      }
      if (a[campo]! > b[campo]!) {
        return 1;
      }
      return 0;
    });
  }
}
