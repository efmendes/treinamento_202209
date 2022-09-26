import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ICliente } from 'src/app/interfaces/cliente';
import { ClientesService } from 'src/app/services/clientes.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class ClientesComponent implements OnInit {
  clientes: ICliente[] = [];

  constructor(private clienteService: ClientesService,
    private router: Router,
    private location: Location,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.buscarTodosClientes();
  }

  buscarTodosClientes() {
    this.clienteService.listarTodosClientes().subscribe((clientes: ICliente[]) => {
      this.clientes = clientes;
    });
  }
  onExcluir(id: string) {
    this.clienteService.removerCliente(id).subscribe();
  }


}
