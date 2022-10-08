import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { valueRegex } from 'src/app/constants/regexExpressions';
import { ISaqueDeposito } from 'src/app/interfaces/operacoes';
import { AlertasService } from 'src/app/services/alertas.service';
import { OperacoesService } from 'src/app/services/operacoes.service';

@Component({
  selector: 'app-deposito',
  templateUrl: './deposito.component.html',
  styleUrls: ['./deposito.component.css', '../../../app.component.css'],
})
export class DepositoComponent implements OnInit {
  constructor(
    private operacoesService: OperacoesService,
    private router: Router,
    private alertaService: AlertasService
  ) {}

  newDeposito = new FormGroup({
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
    const deposito: ISaqueDeposito = {
      ...this.newDeposito.value,
      valor: parseFloat(this.newDeposito.value.valor || '0'),
    } as ISaqueDeposito;

    this.alertaService.alertaLoading('Estamos realizando seu depósito...');

    this.operacoesService.depositar(deposito).subscribe(
      () => {
        this.alertaService.alertaSucess('Depósito realizado com sucesso', () =>
          this.router.navigateByUrl('/')
        );
      },
      ({ error }) => {
        if (error.codigo === 'msg.erro.conta.invalida') {
          this.alertaService.alertaFalha(
            'As informações da conta estão incorretas, verifique os campos e tente novamente'
          );
        } else {
          this.alertaService.alertaFalha(
            'Não foi possível realizar este deposito no momento'
          );
        }
      }
    );
  }
}
