import { Component, OnInit } from '@angular/core';
import { ICliente } from 'src/app/interfaces/cliente';
import { AlertasService } from 'src/app/services/alertas.service';
import { ClientesService } from 'src/app/services/clientes.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  constructor(
    private clienteService: ClientesService,
    private alertaService: AlertasService
    ) { }

    
  clientes: ICliente[] = [];
  ngOnInit(): void {
    this.buscarTodosClientes();
  }

  buscarTodosClientes() {
    this.clienteService.listarTodosClientes().subscribe((clientes: ICliente[]) => {
      this.clientes = clientes;
    }, (error) => {
      console.error(error);
    });
  }

  excluir(id?: number) {
    Swal.fire({
      title: 'Tem certeza?',
      text: "Essa ação não poderá ser desfeita!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sim, pode apagar!'
    }).then((result) => {
      if (result.isConfirmed) {

        if (id) {
          this.clienteService.excluirClientePorId(id).subscribe(() => {
            this.buscarTodosClientes();
          }, (error) => {
            console.error(error);
            this.alertaService.alertaErro('Não foi possivel excluir o cliente!');
          });
        }

        Swal.fire(
          'Deletado!',
          'Cliente deletado com sucesso.',
          'success'
        );
      } else {
        Swal.close;
      }
    });
  }
}
