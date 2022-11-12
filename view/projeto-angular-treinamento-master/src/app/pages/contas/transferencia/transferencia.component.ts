import { AlertasService } from 'src/app/services/alertas.service';
import { Transferencia } from './../../../interfaces/transferencia';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ContasService } from './../../../services/contas.service';
import { IConta } from './../../../interfaces/conta';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css']
})
export class TransferenciaComponent implements OnInit {

  trasferencia!: Transferencia;

  idConta = 0;
  agenciaNumero = '';
  contaNumero = '';
  clienteNome = '';

  transferenciaForm = new FormGroup ({
    agenciaDestino: new FormControl('', Validators.required),
    agenciaOrigem: new FormControl('', Validators.required),
    numeroContaDestino: new FormControl('', Validators.required),
    numeroContaOrigem: new FormControl('', Validators.required),
    valor: new FormControl(Number(''), [Validators.required, Validators.min(1)])
  })

  constructor(private contasService: ContasService, private alertaService: AlertasService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.idConta = Number(this.route.snapshot.paramMap.get('id'));
    this.contasService.buscarContaPorId(this.idConta).subscribe((conta: IConta) => {
      this.agenciaNumero = conta.agencia;
      this.contaNumero = conta.numero;
      this.clienteNome = conta.cliente.nome;
      this.transferenciaForm.setValue({
        agenciaDestino: '',
        agenciaOrigem: this.agenciaNumero,
        numeroContaDestino: '',
        numeroContaOrigem: this.contaNumero,
        valor: 0
      });
    });
  }

  transferir() {
    const transferencia = this.transferenciaForm.value as Transferencia;
    this.contasService.transferencia(transferencia).subscribe(() => {
      this.alertaService.alertaTransacaoSucesso('Transferência realizada com sucesso');
      this.router.navigateByUrl('/contas');
    }, (error) => {
      this.alertaService.alertaErro('Verifique as informações e tente novamente.');
    });
    console.log(transferencia);
  }

}
