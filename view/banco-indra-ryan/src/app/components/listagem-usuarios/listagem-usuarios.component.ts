import { Component, OnInit } from '@angular/core';
import {ICliente} from "../../interfaces/cliente";
import {ClienteService} from "../../services/cliente.service";

@Component({
  selector: 'app-listagem-usuarios',
  templateUrl: './listagem-usuarios.component.html',
  styleUrls: ['./listagem-usuarios.component.css']
})
export class ListagemUsuariosComponent implements OnInit {
  clientes: Array<ICliente> = [];
  constructor(private clienteService: ClienteService) {
    this.buscarTodosClientes();
  }

  ngOnInit(): void {
  }
  buscarTodosClientes() {
    this.clienteService.BuscarTodosClientes().subscribe( {
        next: (clientes) => {
          this.clientes = clientes;
        },
        error: err => console.log(err)
      }
    )
  }

}
