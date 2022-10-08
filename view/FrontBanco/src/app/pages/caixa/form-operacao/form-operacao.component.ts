import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IConta } from 'src/app/interfaces/conta';
import { ISaqueDeposito } from 'src/app/interfaces/saqueDeposito';
import { ITransferencia } from 'src/app/interfaces/transferencia';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-operacao',
  templateUrl: './form-operacao.component.html',
  styleUrls: ['./form-operacao.component.css'],
})
export class FormOperacaoComponent implements OnInit {
  @Input() tipo: string = '';
  @Input() trocaOperacao: boolean = true;

  formSaqueDeposito: FormGroup = new FormGroup({
    agencia: new FormControl('', Validators.required),
    numeroConta: new FormControl('', Validators.required),
    valor: new FormControl('', Validators.required),
  });

  formTransferencia: FormGroup = new FormGroup({
    agenciaDestino: new FormControl('', Validators.required),
    agenciaOrigem: new FormControl('', Validators.required),
    numeroContaDestino: new FormControl('', Validators.required),
    numeroContaOrigem: new FormControl('', Validators.required),
    valor: new FormControl('', Validators.required),
  });

  constructor(
    private contaService: ContasService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (Number(id)) {
      this.contaService.buscarPorId(Number(id)).subscribe(
        (result) => {
          this.preencherFormSaqueDeposito(result);
          this.preencherFormTransferencia(result);
        },
        (error) => {
          console.error(error);
        }
      );
    }
  }

  preencherFormTransferencia(conta: IConta) {
    this.formTransferencia = new FormGroup({
      agenciaOrigem: new FormControl(conta.agencia, Validators.required),
      agenciaDestino: new FormControl('', Validators.required),
      numeroContaOrigem: new FormControl(conta.numero, Validators.required),
      numeroContaDestino: new FormControl('', Validators.required),
      valor: new FormControl('', Validators.required),
    });
  }

  preencherFormSaqueDeposito(conta: IConta) {
    this.formSaqueDeposito = new FormGroup({
      agencia: new FormControl(conta.agencia, Validators.required),
      numeroConta: new FormControl(conta.numero, Validators.required),
      valor: new FormControl('', Validators.required),
    });
  }

  depositarSacar() {
    if (this.tipo === 'deposito') {
      const deposito: ISaqueDeposito = this.formSaqueDeposito.value;
      this.contaService.depositar(deposito).subscribe(
        (result) => {
          Swal.fire('Depósito realizado com sucesso!', 'Sucesso', 'success');
          this.router.navigate(['/contas']);
        },
        (error) => {
          console.error(error);
        }
      );
    }
    if (this.tipo === 'saque') {
      const saque: ISaqueDeposito = this.formSaqueDeposito.value;
      this.contaService.sacar(saque).subscribe(
        (result) => {
          console.log(result);
          Swal.fire('Saque realizado com sucesso!', 'Sucesso', 'success');
          this.router.navigate(['/contas']);
        },
        (error) => {
          Swal.fire('Erro na operação, verifique se há saldo suficiente!', 'Falha', 'error');
        }
      );
    }
  }

  transferir() {
    const transferencia: ITransferencia = this.formTransferencia.value;
      this.contaService.transferir(transferencia).subscribe(
        (result) => {
          Swal.fire('Transferência realizada com sucesso!', 'Sucesso', 'success');
          this.router.navigate(['/contas']);
        },
        (error) => {
          Swal.fire('Erro na operação, verifique se existe saldo ou os dados estão digitada corretamente', 'Falha', 'error');
        }
      );
  }
}