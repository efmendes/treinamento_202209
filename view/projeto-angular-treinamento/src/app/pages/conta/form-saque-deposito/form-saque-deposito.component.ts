import { ContasService } from './../../../services/contas.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Operacoes } from 'src/app/interfaces/operacoes';
import { IConta } from 'src/app/interfaces/conta';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-form-saque-deposito',
  templateUrl: './form-saque-deposito.component.html',
  styleUrls: ['./form-saque-deposito.component.css']
})
export class FormSaqueDepositoComponent implements OnInit {

  idConta = 0;
  operacaoForm = this.formBuilder.group({
    agencia: ['', Validators.required],
    numeroConta: ['', Validators.required],
    valor: [0 , Validators.required],
    operacao: ['', Validators.required]
  })


  saque: boolean = false;
  deposito: boolean = false;

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute,
    private contaService: ContasService,
    private alert: AlertService
    ) { }

  ngOnInit(): void {
    this.idConta = Number(this.route.snapshot.paramMap.get('id'));
    this.contaService.buscarContaPorId(this.idConta).subscribe((conta: IConta) =>
      this.operacaoForm.setValue({
        agencia: conta.agencia,
        numeroConta: conta.numero,
        valor: 0,
        operacao: ''
      })
    )

  }


  operacao() {
    const operacao = this.operacaoForm.value as Operacoes;
    let operaType: string = String(this.operacaoForm.value.operacao);

    if(operaType == 'saque'){
      this.contaService.saque(operacao).subscribe(() => {
        this.alert.alertaSucesso('Saque efetuado com sucesso!')
      }, (error: Error) => {
        this.alert.alertaErro('Conta errada ou saldo insuficiente!')
      });

    } else{
      this.contaService.deposito(operacao).subscribe(() => {
        this.alert.alertaSucesso('Deposito efetuado com sucesso!')
      }, (error: Error) => {
        this.alert.alertaErro('Erro ao efetuar depósito!')
      });
    }

  }


  onBack(){
    this.location.back();
  }
}
