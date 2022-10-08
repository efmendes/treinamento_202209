import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { valueRegex } from 'src/app/constants/regexExpressions';
import { ICliente } from 'src/app/interfaces/cliente';
import { IConta } from 'src/app/interfaces/conta';
import { ISaqueDeposito, ITransferencia } from 'src/app/interfaces/operacoes';
import { AlertasService } from 'src/app/services/alertas.service';
import { ClientesService } from 'src/app/services/clientes.service';
import { ContasService } from 'src/app/services/contas.service';
import { OperacoesService } from 'src/app/services/operacoes.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css', '../../../app.component.css'],
})
export class TransferenciaComponent implements OnInit {
  constructor(
    private contasService: ContasService,
    private operacoesService: OperacoesService,
    private router: Router,
    private alertaService: AlertasService
  ) {}

  newTransferencia = new FormGroup({
    agenciaOrigem: new FormControl('', Validators.required),
    numeroContaOrigem: new FormControl('', Validators.required),
    agenciaDestino: new FormControl('', Validators.required),
    numeroContaDestino: new FormControl('', Validators.required),
    valor: new FormControl('', [
      Validators.required,
      Validators.min(1),
      Validators.pattern(valueRegex),
    ]),
  });

  contas: IConta[] = [];

  ngOnInit(): void {
    this.buscarContas();
  }

  buscarContas() {
    this.contasService.listarContas().subscribe(
      (contas: IConta[]) => {
        this.contas = contas;
      },
      (err) =>
        this.alertaService.alertaFalha(
          'Não é possível realizar uma transferência neste momento',
          () => location.replace('/contas')
        )
    );
  }

  handleContaOrigem(agencia: string, numeroConta: string) {
    this.newTransferencia.controls['agenciaOrigem'].setValue(agencia);
    this.newTransferencia.controls['numeroContaOrigem'].setValue(numeroConta);
  }

  handleContaDestino(agencia: string, numeroConta: string) {
    this.newTransferencia.controls['agenciaDestino'].setValue(agencia);
    this.newTransferencia.controls['numeroContaDestino'].setValue(numeroConta);
  }

  onSubmit() {
    const transferencia: ITransferencia = {
      ...this.newTransferencia.value,
      valor: parseFloat(this.newTransferencia.value.valor || '0'),
    } as ITransferencia;

    this.alertaService.alertaLoading('Estamos realizando sua transferência...');

    this.operacoesService.transferir(transferencia).subscribe(
      () => {
        this.alertaService.alertaSucess(
          'Transferência realizada com sucesso',
          () => this.router.navigateByUrl('/')
        );
      },
      ({ error }) => {
        if (error.codigo === 'msg.erro.conta.invalida') {
          this.alertaService.alertaFalha(
            'As informações da conta estão incorretas, verifique os campos e tente novamente'
          );
        } else if (error.codigo === 'msg.erro.saldo.inexistente') {
          this.alertaService.alertaFalha(
            'A conta de origem não tem este valor disponível para transferência'
          );
        } else {
          this.alertaService.alertaFalha(
            'Não foi possível realizar esta transferência no momento'
          );
        }
      }
    );
  }
}
