import { IConta } from 'src/app/interfaces/conta';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Deposito } from './../../../interfaces/deposito';
import { AlertasService } from './../../../services/alertas.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ContasService } from 'src/app/services/contas.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-deposito',
  templateUrl: './deposito.component.html',
  styleUrls: ['./deposito.component.css']
})
export class DepositoComponent implements OnInit {

  deposito!: Deposito;
  
  idConta = 0;
  agenciaNumero = '';
  contaNumero = '';
  clienteNome = '';

  depositoForm = new FormGroup ({
    agencia: new FormControl('', Validators.required),
    numeroConta: new FormControl('', Validators.required),
    valor: new FormControl(Number(''), [Validators.required, Validators.min(1)]),
  });

  constructor(private contasService: ContasService, private alertaService: AlertasService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.idConta = Number(this.route.snapshot.paramMap.get('id'));
    this.contasService.buscarContaPorId(this.idConta).subscribe((conta: IConta) => {
      this.agenciaNumero = conta.agencia;
      this.contaNumero = conta.numero;
      this.clienteNome = conta.cliente.nome;
      this.depositoForm.setValue({
        agencia: this.agenciaNumero,
        numeroConta: this.contaNumero,
        valor: 0
      });
    });
  }

  depositar() {
      const deposito = this.depositoForm.value as Deposito;
      this.contasService.depositar(deposito).subscribe(() => {
        this.alertaService.alertaTransacaoSucesso('Deposito realizado com sucesso');
        this.router.navigateByUrl('/contas');
      }, (error) => {
        this.alertaService.alertaErro('Verifique as informações e tente novamente.');
        console.log(this.deposito);
      });
    }
  }
