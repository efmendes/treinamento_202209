import { IConta } from 'src/app/interfaces/conta';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Saque } from './../../../interfaces/saque';
import { Deposito } from './../../../interfaces/deposito';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertasService } from './../../../services/alertas.service';
import { ContasService } from 'src/app/services/contas.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-saque',
  templateUrl: './saque.component.html',
  styleUrls: ['./saque.component.css']
})
export class SaqueComponent implements OnInit {

  saque!: Saque;

  idConta = 0;
  agenciaNumero = '';
  contaNumero = '';
  clienteNome = '';

  saqueForm = new FormGroup ({
    agencia: new FormControl('', Validators.required),
    numeroConta: new FormControl('', Validators.required),
    valor: new FormControl(Number(''), [Validators.required, Validators.min(1)]),
  });

  constructor(private contasService: ContasService, private alertaService: AlertasService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {this.idConta = Number(this.route.snapshot.paramMap.get('id'));
  this.contasService.buscarContaPorId(this.idConta).subscribe((conta: IConta) => {
    this.agenciaNumero = conta.agencia;
    this.contaNumero = conta.numero;
    this.clienteNome = conta.cliente.nome;
    this.saqueForm.setValue({
      agencia: this.agenciaNumero,
      numeroConta: this.contaNumero,
      valor: 0
    });
  });
  }

  sacar() {
      const saque = this.saqueForm.value as Saque;
      this.contasService.sacar(saque).subscribe(() => {
        this.alertaService.alertaTransacaoSucesso('Saque realizado com sucesso');
        this.router.navigateByUrl('/contas');
      }, (error) => {
        this.alertaService.alertaErro('Verifique as informações e tente novamente.');
      });
      console.log(this.saque);
    }
  }
