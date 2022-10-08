import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ICliente } from 'src/app/interfaces/cliente';
import { ClientesService } from 'src/app/services/clientes.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  constructor(private clienteService: ClientesService) { }
  clientes: ICliente[] = [];
  loading = false;
  paginaAtual = 1;

  ngOnInit(): void {
    this.buscarTodosClientes();
  }

  buscarTodosClientes() {
    this.loading = true;
    this.clienteService.listarTodosClientes().subscribe((clientes: ICliente[]) => {
      this.loading = false;
      this.clientes = clientes;
    });
  }

  excluir(id: number) {
    Swal.fire({
      title: 'Tem certeza?',
      text: 'Você não conseguirá reverter!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sim, deletar!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.clienteService.remover(id).subscribe(
          (result) => {
            Swal.fire('Deletado!', 'O cliente foi deletado.', 'success');
            this.buscarTodosClientes();
          },
          (error) => {
            console.error(error);
          }
        );
      }
    });
  }

}
