import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IOperacao } from 'src/app/interfaces/operacoes';
import { AlertasService } from 'src/app/services/alertas.service';
import { ContasService } from 'src/app/services/contas.service';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css', '../../../app.component.css'],
})
export class ExtratoComponent implements OnInit {
  constructor(
    private contasService: ContasService,
    private route: ActivatedRoute,
    private router: Router,
    private alertaService: AlertasService
  ) {}

  newExtrato = new FormGroup({
    dataInicial: new FormControl('', Validators.required),
    dataFinal: new FormControl('', Validators.required),
  });

  agencia = '';
  numero = '';
  hasSubmitted = false;
  isLoading = false;

  operacoes: IOperacao[] = [];

  ngOnInit(): void {
    this.agencia = this.route.snapshot.paramMap.get('agencia') || '';
    this.numero = this.route.snapshot.paramMap.get('numero') || '';
  }

  transformDate(data: string) {
    const transformedDate = new Date(data);
    return `${String(transformedDate.getDate()).padStart(2, '0')}/${String(
      transformedDate.getMonth() + 1
    ).padStart(
      2,
      '0'
    )}/${transformedDate.getFullYear()} às ${transformedDate.getHours()}:${String(
      transformedDate.getMinutes()
    ).padStart(2, '0')}`;
  }

  onSubmit() {
    this.hasSubmitted = true;
    this.isLoading = true;
    this.operacoes = [];
    this.contasService
      .getExtrato(
        this.agencia,
        this.numero,
        this.newExtrato.value.dataInicial || '',
        this.newExtrato.value.dataFinal || ''
      )
      .subscribe(
        (operacoes) => {
          this.operacoes = operacoes;
          this.isLoading = false;
        },
        () => {
          this.alertaService.alertaFalha(
            'Não foi possível fazer esta busca no momento, tente novamente mais tarde.'
          );
          this.hasSubmitted = false;
          this.isLoading = false;
        }
      );
  }
}
