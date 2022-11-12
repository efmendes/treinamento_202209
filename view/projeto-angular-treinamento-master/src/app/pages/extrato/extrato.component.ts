import { IConta } from 'src/app/interfaces/conta';
import { ContasService } from 'src/app/services/contas.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ExtratoService } from 'src/app/services/extrato.service';
import { Extrato } from 'src/app/interfaces/extrato';


@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css']
})
export class ExtratoComponent implements OnInit {

  extrato: Extrato[] = [];
  idConta = 0;

  agenciaNumero = '';
  contaNumero = '';
  clienteNome = '';

  extratoForm = new FormGroup ({
    dataInicio: new FormControl('', Validators.required),
    dataFim: new FormControl('', Validators.required)
  })

  constructor(private extratoService: ExtratoService, private contasService: ContasService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.idConta = Number(this.route.snapshot.paramMap.get('id'));
    this.contasService.buscarContaPorId(this.idConta).subscribe((conta: IConta) => {
      this.agenciaNumero = conta.agencia;
      this.contaNumero = conta.numero;
      this.clienteNome = conta.cliente.nome;
    });
  }

  consultarExtrato () {
    const extrato  = this.extratoForm.value as Extrato;
    this.extratoService.buscarExtratoPorData(this.agenciaNumero, this.contaNumero, extrato.dataInicio, extrato.dataFim).subscribe((extrato: Extrato[]) => {
      this.extrato = extrato;
      console.log(this.extrato);
    });
  }

}
