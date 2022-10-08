import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ICliente } from 'src/app/interfaces/cliente';
import { AlertasService } from 'src/app/services/alertas.service';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: [
    './clientes.component.css',
    '../pages.css',
    '../../app.component.css',
  ],
})
export class ClientesComponent implements OnInit {
  constructor(
    private clienteService: ClientesService,
    private alertasService: AlertasService
  ) {}

  clientes: ICliente[] = [];
  listingError = false;

  ngOnInit(): void {
    this.buscarTodosClientes();
  }

  buscarTodosClientes() {
    this.clienteService.listarTodosClientes().subscribe(
      (clientes: ICliente[]) => {
        this.clientes = clientes;
      },
      (err) => (this.listingError = true)
    );
  }

  removerCliente(clienteID: number) {
    this.clienteService.removerCliente(clienteID).subscribe(
      () =>
        this.alertasService.alertaSucess('Usuário excluido com sucesso', () => {
          this.clientes = [];
          this.buscarTodosClientes();
        }),
      (error) =>
        this.alertasService.alertaFalha('Não foi possível excluir este cliente')
    );
  }
}
