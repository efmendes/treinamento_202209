import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-caixa-eletronico',
  templateUrl: './caixa-eletronico.component.html',
  styleUrls: ['./caixa-eletronico.component.css']
})
export class CaixaEletronicoComponent implements OnInit {

  operacao = "deposito";
  deposito = "deposito";
  saque = "saque";
  transferencia = "transferencia";

  trocaOperacao: boolean = true;

  constructor() { }

  ngOnInit(): void { }

  alterarOperacao(flag: boolean) {
    this.trocaOperacao = flag;
  }

}