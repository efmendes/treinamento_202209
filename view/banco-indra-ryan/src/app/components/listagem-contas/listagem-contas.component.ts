import { Component, OnInit } from '@angular/core';
import {IContaBancaria} from "../../interfaces/conta-bancaria";
import {ContaBancariaServiceService} from "../../services/conta-bancaria-service.service";

@Component({
  selector: 'app-listagem-contas',
  templateUrl: './listagem-contas.component.html',
  styleUrls: ['./listagem-contas.component.css']
})
export class ListagemContasComponent implements OnInit {
  contas: Array<IContaBancaria> = [];
  constructor(private  contaBancariaService:ContaBancariaServiceService) {
    this.buscarTodasContas();
  }
  ngOnInit(): void {
  }
  buscarTodasContas(){
    this.contaBancariaService.buscarTodasContas().subscribe({
      next: (contas)=>{
        this.contas = contas;
      },
      error: err => console.log(err)
    })
  }
}
