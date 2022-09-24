import { Component, OnInit } from '@angular/core';
import { ICliente } from 'src/app/interfaces/cliente';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  constructor(private clienteService: ClientesService) { }
  clientes: ICliente[] = [];
  ngOnInit(): void {
    this.buscarTodosClientes();
  }

  buscarTodosClientes() {
    this.clienteService.listarTodosClientes().subscribe((clientes: ICliente[]) => {
      this.clientes = clientes;
    });
  }

}
