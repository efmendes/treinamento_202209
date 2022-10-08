import { Component, OnInit } from '@angular/core';
import { ClientesService } from 'src/app/services/clientes.service';
import { Pipe, PipeTransform } from '@angular/core';
import { ICliente } from 'src/app/interfaces/cliente';
import Swal from 'sweetalert2';
import { ContasService } from 'src/app/services/contas.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  constructor(private clienteService: ClientesService, private contasService: ContasService) { }
  clientes: ICliente[] = [];
  ngOnInit(): void {
    this.listarTodos();
  }

  listarTodos(){
    this.clienteService.listarTodosClientes().subscribe((result: ICliente[]) => {
      this.clientes = result;
    });
  }

  listarContasCpf(){

  }

  confirmar(id: number){
    Swal.fire({ title: 'Você tem certeza?', text: "Você não pode reverter isto", icon: 'warning', showCancelButton: true, confirmButtonColor: '#3085d6', cancelButtonColor: '#d33',cancelButtonText:"Cancelar", confirmButtonText: 'Sim, remova!' }).then((result) => { if (result.isConfirmed) { this.clienteService.remover(id).subscribe(result =>{
      Swal.fire( 'Removido!', 'Cliente deletado com sucesso', 'success');
      this.listarTodos();
    }, error => {
      console.error(error);
    });  } })

  }

}

