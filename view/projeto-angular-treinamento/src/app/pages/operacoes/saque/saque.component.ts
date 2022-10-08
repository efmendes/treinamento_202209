import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { valueRegex } from 'src/app/constants/regexExpressions';
import { ISaqueDeposito } from 'src/app/interfaces/operacoes';
import { AlertasService } from 'src/app/services/alertas.service';
import { OperacoesService } from 'src/app/services/operacoes.service';

@Component({
  selector: 'app-saque',
  templateUrl: './saque.component.html',
  styleUrls: ['./saque.component.css', '../../../app.component.css'],
})
export class SaqueComponent implements OnInit {
  constructor(
    private operacoesService: OperacoesService,
    private router: Router,
    private alertaService: AlertasService
  ) {}

  newSaque = new FormGroup({
    agencia: new FormControl('', Validators.required),
    numeroConta: new FormControl('', Validators.required),
    valor: new FormControl('', [
      Validators.required,
      Validators.min(1),
      Validators.pattern(valueRegex),
    ]),
  });

  ngOnInit(): void {}

  onSubmit() {
    const saque: ISaqueDeposito = {
      ...this.newSaque.value,
      valor: parseFloat(this.newSaque.value.valor || '0'),
    } as ISaqueDeposito;

    this.alertaService.alertaLoading('Estamos realizando seu saque...');

    this.operacoesService.sacar(saque).subscribe(
      () => {
        this.alertaService.alertaSucess('Saque realizado com sucesso', () =>
          this.router.navigateByUrl('/')
        );
      },
      ({ error }) => {
        if (error.codigo === 'msg.erro.conta.invalida') {
          this.alertaService.alertaFalha(
            'As informações da conta estão incorretas, verifique os campos e tente novamente'
          );
        } else if (error.codigo === 'msg.erro.saldo.inexistente') {
          this.alertaService.alertaFalha(
            'Esta conta não tem este valor disponível para saque'
          );
        } else {
          this.alertaService.alertaFalha(
            'Não foi possível realizar este saque no momento'
          );
        }
      }
    );
  }
}
