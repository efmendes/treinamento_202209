import { IConta } from './../../../interfaces/conta';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContasService } from 'src/app/services/contas.service';
import { Transfer } from 'src/app/interfaces/transfer';
import { AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-form-transfer',
  templateUrl: './form-transfer.component.html',
  styleUrls: ['./form-transfer.component.css']
})
export class FormTransferComponent implements OnInit {

  constructor(
    private contaService: ContasService,
    private location: Location,
    private route: ActivatedRoute,
    private formBuilder: NonNullableFormBuilder,
    private alert: AlertService
    ) {}

    idConta = 0;
    transferForm = this.formBuilder.group({
      agenciaOrigem: ['', Validators.required],
      numeroContaOrigem: ['', Validators.required],
      agenciaDestino: ['', Validators.required],
      numeroContaDestino: ['', Validators.required],
      valor: [0 , Validators.required]
    })


    ngOnInit(): void {
      this.idConta = Number(this.route.snapshot.paramMap.get('id'));
      this.contaService.buscarContaPorId(this.idConta).subscribe((conta: IConta) =>
        this.transferForm.setValue({
          agenciaOrigem: conta.agencia,
          numeroContaOrigem: conta.numero,
          agenciaDestino: '',
          numeroContaDestino: '',
          valor: 0
        })
      )

    }


    transfer() {
      const transferencia = this.transferForm.value as Transfer;

      this.contaService.transferencia(transferencia).subscribe(() => {
        this.alert.alertaSucesso('Transferência efetuada com sucesso!')
      }, (error: Error) => {
        this.alert.alertaErro('Contas erradas ou saldo insuficiente!')
      });
    }


    onBack(){
      this.location.back();
    }
}
