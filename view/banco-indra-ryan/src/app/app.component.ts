import { Component } from '@angular/core';
import {ClienteService} from "./services/cliente.service";
import {ICliente} from "./interfaces/cliente";
import {newArray} from "@angular/compiler/src/util";
import {IContaBancaria} from "./interfaces/conta-bancaria";
import {ContaBancariaServiceService} from "./services/conta-bancaria-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'banco-indra-ryan';
  clientes: Array<ICliente> = [];
  contas: Array<IContaBancaria> = [];
  constructor(private clienteService: ClienteService, private  contaBancariaService:ContaBancariaServiceService) {
    this.buscarTodosClientes();
    this.buscarTodasContas();
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
  buscarTodasContas(){
    this.contaBancariaService.buscarTodasContas().subscribe({
      next: (contas)=>{
        this.contas = contas;
        console.log(this.contas)
      },
      error: err => console.log(err)
    })
  }
}
